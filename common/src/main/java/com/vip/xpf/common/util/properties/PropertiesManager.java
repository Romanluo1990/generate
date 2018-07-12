package com.vip.xpf.common.util.properties;

import com.vip.cfgcenter.internal.ClientPropertyPostProcessor;
import com.vip.cfgcenter.internal.PropertyPostProcessor;
import com.vip.cfgcenter.support.PropertyPlaceholderConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;

/**
 *
 * 属性管理者
 * @Auther: romanluo
 * @Date: 2018/7/11
 * @Description:
 */
@Slf4j
public class PropertiesManager {

	private PropertiesManager() {
	}

	private static final Lock INIT_LOCK = new ReentrantLock();
	public static final String VIP_APP_PROPERTIES = "vip-app.properties";

	private static Map<String, String> propertiesMap;

	public static Map<String, String> getProperties() {
		return new HashMap<>(getPropertiesMap());
	}

	public static String getString(String key) {
		return getPropertiesMap().get(key);
	}

	public static Integer getInteger(String key) {
		try {
			return Integer.parseInt(getString(key));
		} catch (Exception e) {
			log.warn("{}属性获取异常", key, e);
		}
		return null;
	}

	public static Long getLong(String key) {
		try {
			return Long.parseLong(getString(key));
		} catch (Exception e) {
			log.warn("{}属性获取异常", key, e);
		}
		return null;
	}

	public static Float getFloat(String key) {
		try {
			return Float.parseFloat(getString(key));
		} catch (Exception e) {
			log.warn("{}属性获取异常", key, e);
		}
		return null;
	}

	public static Boolean getBoolean(String key) {
		return Boolean.parseBoolean(getString(key));
	}

	private static Map<String, String> getPropertiesMap() {
		if (propertiesMap == null) {
			INIT_LOCK.lock();
			if (propertiesMap == null) {
				propertiesMap = new HashMap<>();
				Properties properties = new Properties();
				InputStream inputStream = PropertiesManager.class.getClassLoader()
						.getResourceAsStream(VIP_APP_PROPERTIES);
				if (inputStream == null) {
					throw new IllegalStateException("缺少:" + VIP_APP_PROPERTIES + "配置文件");
				}
				try {
					properties.load(inputStream);
				} catch (IOException e) {
					throw new IllegalStateException("本地配置加载失败", e);
				}
				PropertyPlaceholderConfig propertyHolder = getPropertyPlaceholderConfig();
				properties.keySet().parallelStream().map(Objects::toString)
						.forEach(setProperties(propertiesMap, properties, propertyHolder));
			}
			INIT_LOCK.unlock();
		}
		return propertiesMap;
	}

	private static PropertyPlaceholderConfig getPropertyPlaceholderConfig() {
		try {
			PropertyPlaceholderConfig propertyHolder = new PropertyPlaceholderConfig();
			PropertyPostProcessor processor = new ClientPropertyPostProcessor(propertyHolder);
			processor.registerPropertyReloader();
			return propertyHolder;
		} catch (Exception e) {
			log.warn("配置中心加载失败");
		}
		return null;
	}

	private static Consumer<String> setProperties(Map<String, String> propertiesMap, Properties properties,
			PropertyPlaceholderConfig propertyHolder) {
		return key -> {
			//1.加载配置文件
			String configFileValue = Objects.toString(properties.get(key));
			if (StringUtils.isNotEmpty(configFileValue)) {
				propertiesMap.put(key, configFileValue);
			}
			//2.加载环境变量
			String sysValue = System.setProperty(key, System.getenv(key));
			if (StringUtils.isNotEmpty(sysValue)) {
				propertiesMap.put(key, sysValue);
			}
			//3.加载配置中心
			String remoteData = null;
			if (propertyHolder != null) {
				remoteData = propertyHolder.getProperty(key);
				propertyHolder.addListener(key, (oldValue, newValue) -> propertiesMap.put(key, newValue));
			}
			if (StringUtils.isNotEmpty(remoteData)) {
				propertiesMap.put(key, remoteData);
			}
		};
	}

}

package com.vip.utils.generate;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.stream.Collectors;

@Data
public class GeneratorConfig {

	private String jdbcConnectionDriver;

	private String jdbcConnectionUrl;

	private String jdbcConnectionUser;

	private String jdbcConnectionPwd;

	private String author;

	private String tableName;

	private String tableNamePrefix;

	private String projectName;

	private String basePackage;

	public GeneratorConfig(String configFile) {
		Properties properties = new Properties();
		try {
			File file = new File(Objects.toString(configFile,System.getProperty("user.dir") + "/generatorConfig.properties"));
			if(file.exists())
				properties.load(new FileInputStream(file));
			else
				properties.load(ClassLoader.getSystemResourceAsStream("generatorConfig.properties"));
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
		Map<String, Object> propertieMap = properties.entrySet().stream()
													 .collect(Collectors
															 .toMap(GeneratorConfig::getEntryKey, Map.Entry::getValue));
		BeanUtils.map(propertieMap, this);
		author = System.getProperty("user.name");
	}

	private static String getEntryKey(Map.Entry<Object, Object> entry) {
		return Objects.toString(entry.getKey());
	}

}

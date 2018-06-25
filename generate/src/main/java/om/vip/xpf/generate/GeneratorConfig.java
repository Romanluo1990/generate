package om.vip.xpf.generate;

import com.vip.xpf.common.util.bean.BeanUtils;
import lombok.Data;

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

	private String modelDir;

	private String modelPackage;

	private String mapperDir;

	private String mapperPackage;

	private String xmlDir;

	private String xmlPackage;

	private String daoDir;

	private String daoPackage;

	private String serviceDir;

	private String servicePackage;

	private String voDir;

	private String voPackage;

	private String formDir;

	private String formPackage;

	private String controllerDir;

	private String controllerPackage;

	public GeneratorConfig() {
		Properties properties = new Properties();
		try {
			properties.load(ClassLoader.getSystemResourceAsStream("generatorConfig.properties"));
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
		Map<String, Object> propertieMap = properties.entrySet().stream()
				.collect(Collectors.toMap(GeneratorConfig::getEntryKey, Map.Entry::getValue));
		BeanUtils.copyProperties(propertieMap, this);
		author = System.getProperty("user.name");
	}

	private static String getEntryKey(Map.Entry<Object, Object> entry) {
		return Objects.toString(entry.getKey());
	}
}

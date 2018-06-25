package om.vip.xpf.generate;

import com.vip.xpf.common.util.bean.BeanUtils;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@Slf4j
public class CodeGenerateUtils {

	private final GeneratorConfig generatorConfig;

	private final PackageBean packageBean;

	public CodeGenerateUtils(GeneratorConfig generatorConfig) {
		this.generatorConfig = generatorConfig;
		packageBean = BeanUtils.map(generatorConfig, PackageBean.class);
	}

	public static void main(String[] args) {
		CodeGenerateUtils codeGenerateUtils = new CodeGenerateUtils(new GeneratorConfig());
		codeGenerateUtils.generateFromDb();
	}

	private void generateFromDb() {
		String url = generatorConfig.getJdbcConnectionUrl();
		String user = generatorConfig.getJdbcConnectionUser();
		String pwd = generatorConfig.getJdbcConnectionPwd();
		Properties props = new Properties();
		props.setProperty("user", user);
		props.setProperty("password", pwd);
		props.setProperty("remarks", "true"); //设置可以获取remarks信息
		props.setProperty("useInformationSchema", "true");//设置可以获取tables remarks信息
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(url, props);
			DatabaseMetaData metaData = connection.getMetaData();

			generateModel(metaData);
			generateMapper(metaData);
			generateXml(metaData);
			generateDao(metaData);
			generateService(metaData);
			generateVo(metaData);
			generateForm(metaData);
			generateController(metaData);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					log.warn("数据库关闭失败", e);
				}
			}
		}
	}

	private void generateController(DatabaseMetaData metaData) throws IOException, TemplateException {
		String tableName = generatorConfig.getTableName();
		String codelDir = generatorConfig.getControllerDir();
		String author = generatorConfig.getAuthor();
		ControllerCodeGenerator.build(metaData, packageBean, tableName, codelDir, author).generate();
	}

	private void generateForm(DatabaseMetaData metaData) throws IOException, TemplateException {
		String tableName = generatorConfig.getTableName();
		String codelDir = generatorConfig.getFormDir();
		String author = generatorConfig.getAuthor();
		FormCodeGenerator.build(metaData, packageBean, tableName, codelDir, author).generate();
	}

	private void generateVo(DatabaseMetaData metaData) throws IOException, TemplateException {
		String tableName = generatorConfig.getTableName();
		String codelDir = generatorConfig.getVoDir();
		String author = generatorConfig.getAuthor();
		VoCodeGenerator.build(metaData, packageBean, tableName, codelDir, author).generate();
	}

	private void generateService(DatabaseMetaData metaData) throws IOException, TemplateException {
		String tableName = generatorConfig.getTableName();
		String codelDir = generatorConfig.getServiceDir();
		String author = generatorConfig.getAuthor();
		ServiceCodeGenerator.build(metaData, packageBean, tableName, codelDir, author).generate();
	}

	private void generateDao(DatabaseMetaData metaData) throws IOException, TemplateException {
		String tableName = generatorConfig.getTableName();
		String codelDir = generatorConfig.getDaoDir();
		String author = generatorConfig.getAuthor();
		DaoCodeGenerator.build(metaData, packageBean, tableName, codelDir, author).generate();
	}

	private void generateXml(DatabaseMetaData metaData) throws IOException, TemplateException {
		String tableName = generatorConfig.getTableName();
		String codelDir = generatorConfig.getXmlDir();
		String author = generatorConfig.getAuthor();
		XmlCodeGenerator.build(metaData, packageBean, tableName, codelDir, author).generate();
	}

	private void generateMapper(DatabaseMetaData metaData) throws IOException, TemplateException {
		String tableName = generatorConfig.getTableName();
		String codelDir = generatorConfig.getMapperDir();
		String author = generatorConfig.getAuthor();
		MapperCodeGenerator.build(metaData, packageBean, tableName, codelDir, author).generate();
	}

	private void generateModel(DatabaseMetaData metaData) throws IOException, TemplateException {
		String tableName = generatorConfig.getTableName();
		String codelDir = generatorConfig.getModelDir();
		String author = generatorConfig.getAuthor();
		ModelCodeGenerator.build(metaData, packageBean, tableName, codelDir, author).generate();
	}

}

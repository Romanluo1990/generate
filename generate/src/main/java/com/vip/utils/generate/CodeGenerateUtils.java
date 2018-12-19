package com.vip.utils.generate;

import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.File;
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
		String configFile = null;
		String templatesPath = null;
		for (int i = 0; i < args.length; i++) {
			if("-c".equals(args[i]) || "--config".equals(args[i])){
				configFile = args[i+1];
			}
			if("-t".equals(args[i]) || "--templates".equals(args[i])){
				templatesPath = args[i+1];
			}
		}
		FreeMarkerTemplateUtils.setTemplatePath(templatesPath);
		CodeGenerateUtils codeGenerateUtils = new CodeGenerateUtils(new GeneratorConfig(configFile));
		codeGenerateUtils.generateFromDb();
	}

	private void generateFromDb() {
		try {
			FileUtils.forceDelete(new File(generatorConfig.getProjectName()));
		} catch (Exception e) {
			log.info(generatorConfig.getProjectName() + "不存在");
		}
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
			String tableNames = generatorConfig.getTableName();
			for (String tableName : tableNames.split(",")) {
				generateModel(metaData, tableName);
				generateDao(metaData, tableName);
				generateMapper(metaData, tableName);
				generateXml(metaData, tableName);
				//			generateService(metaData,tableName);
				//			generateController(metaData,tableName);
				//			generateVo(metaData,tableName);
				//			generateForm(metaData,tableName);
			}

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

	private void generateController(DatabaseMetaData metaData, String tableName) throws IOException, TemplateException {
		String tableNamePrefix = generatorConfig.getTableNamePrefix();
		String codelDir = generatorConfig.getProjectName() + File.separator + "java";
		String author = generatorConfig.getAuthor();
		ControllerCodeGenerator.build(metaData, packageBean, tableNamePrefix, tableName, codelDir, author).generate();
	}

	private void generateForm(DatabaseMetaData metaData, String tableName) throws IOException, TemplateException {
		String tableNamePrefix = generatorConfig.getTableNamePrefix();
		String codelDir = generatorConfig.getProjectName() + File.separator + "java";
		String author = generatorConfig.getAuthor();
		FormCodeGenerator.build(metaData, packageBean, tableNamePrefix, tableName, codelDir, author).generate();
	}

	private void generateVo(DatabaseMetaData metaData, String tableName) throws IOException, TemplateException {
		String tableNamePrefix = generatorConfig.getTableNamePrefix();
		String codelDir = generatorConfig.getProjectName() + File.separator + "java";
		String author = generatorConfig.getAuthor();
		VoCodeGenerator.build(metaData, packageBean, tableNamePrefix, tableName, codelDir, author).generate();
	}

	private void generateService(DatabaseMetaData metaData, String tableName) throws IOException, TemplateException {
		String tableNamePrefix = generatorConfig.getTableNamePrefix();
		String codelDir = generatorConfig.getProjectName() + File.separator + "java";
		String author = generatorConfig.getAuthor();
		ServiceCodeGenerator.build(metaData, packageBean, tableNamePrefix, tableName, codelDir, author).generate();
	}

	private void generateDao(DatabaseMetaData metaData, String tableName) throws IOException, TemplateException {
		String tableNamePrefix = generatorConfig.getTableNamePrefix();
		String codelDir = generatorConfig.getProjectName() + File.separator + "java";
		String author = generatorConfig.getAuthor();
		DaoCodeGenerator.build(metaData, packageBean, tableNamePrefix, tableName, codelDir, author).generate();
	}

	private void generateXml(DatabaseMetaData metaData, String tableName) throws IOException, TemplateException {
		String tableNamePrefix = generatorConfig.getTableNamePrefix();
		String codelDir = generatorConfig.getProjectName() + File.separator + "resources";
		String author = generatorConfig.getAuthor();
		XmlCodeGenerator.build(metaData, packageBean, tableNamePrefix, tableName, codelDir, author).generate();
	}

	private void generateMapper(DatabaseMetaData metaData, String tableName) throws IOException, TemplateException {
		String tableNamePrefix = generatorConfig.getTableNamePrefix();
		String codelDir = generatorConfig.getProjectName() + File.separator + "java";
		String author = generatorConfig.getAuthor();
		MapperCodeGenerator.build(metaData, packageBean, tableNamePrefix, tableName, codelDir, author).generate();
	}

	private void generateModel(DatabaseMetaData metaData, String tableName) throws IOException, TemplateException {
		String tableNamePrefix = generatorConfig.getTableNamePrefix();
		String codelDir = generatorConfig.getProjectName() + File.separator + "java";
		String author = generatorConfig.getAuthor();
		ModelCodeGenerator.build(metaData, packageBean, tableNamePrefix, tableName, codelDir, author).generate();
	}

}

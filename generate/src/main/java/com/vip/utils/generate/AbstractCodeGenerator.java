package com.vip.utils.generate;

import com.google.common.base.CaseFormat;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.regex.Matcher;

public abstract class AbstractCodeGenerator {

	protected final DatabaseMetaData databaseMetaData;

	protected final String tableName;

	private final String tableNamePrefix;

	protected final PackageBean packageBean;

	protected final String codeDir;

	protected final String author;

	public AbstractCodeGenerator(DatabaseMetaData databaseMetaData, PackageBean packageBean, String tableNamePrefix,
			String tableName,
			String codeDir, String author) {
		this.tableNamePrefix = tableNamePrefix;
		this.tableName = tableName;
		this.codeDir = codeDir;
		this.packageBean = packageBean;
		this.author = author;
		this.databaseMetaData = databaseMetaData;
	}

	public void generate() throws IOException, TemplateException {
		List<Consumer<Map<String, Object>>> freeMarkerDataCompleters = getfreeMarkerDataCompleters();
		freeMarkerDataCompleters.add(tableInfoCompleter());
		Consumer<Map<String, Object>> consumer = freeMarkerDataCompleters.parallelStream()
																		 .reduce(putCommonData(),
																				 (pre, next) -> pre.andThen(next));
		Map<String, Object> freeMarkerData = new HashMap<>();
		consumer.accept(freeMarkerData);
		generateFile(freeMarkerData);
	}

	private Consumer<Map<String, Object>> tableInfoCompleter() {
		return freeMarkerData -> {
			try {
				ResultSet resultSet = databaseMetaData.getTables(null, "%", tableName, new String[]{"TABLE"});
				resultSet.next();
				TableInfo tableInfo = new TableInfo();
				tableInfo.setTableName(resultSet.getString("TABLE_NAME"));
				tableInfo.setComment(resultSet.getString("REMARKS"));
				tableInfo.setClassName(getClassName());
				freeMarkerData.put("tableInfo", tableInfo);
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		};
	}

	/**
	 * 设置通用数据
	 * @param codePackage
	 * @param author
	 * @return
	 */
	private Consumer<Map<String, Object>> putCommonData() {
		return freeMarkerData -> {
			freeMarkerData.put("package", getBasePackage());
			freeMarkerData.put("author", author);
			freeMarkerData.put("date", DateFormatUtils.format(new Date(), "yyyy/MM/dd"));
		};
	}

	/**
	 * 生成代码文件
	 * @throws IOException
	 * @throws TemplateException
	 */
	private void generateFile(Map<String, Object> freeMarkerData) throws IOException, TemplateException {
		Template template = FreeMarkerTemplateUtils.getTemplate(getTemplateName());
		String parentDir = codeDir + File.separator + getBasePackage().replaceAll("\\.", Matcher.quoteReplacement(File.separator));
		File parentFile = new File(parentDir);
		if (!parentFile.exists()) {
			parentFile.mkdirs();
		}
		FileWriter fileWriter = new FileWriter(new File(parentDir, getClassName() + getFileSuffix()));
		template.process(freeMarkerData, fileWriter);
	}

	protected String getClassName() {
		return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tableName
				.replaceFirst(tableNamePrefix, ""));
	}

	/**
	 * 模版数据构建者
	 * @return
	 */
	protected abstract List<Consumer<Map<String, Object>>> getfreeMarkerDataCompleters();

	/**
	 * 文件后缀名
	 * @return
	 */
	protected abstract String getFileSuffix();

	/**
	 * 模版文件名称
	 * @return
	 */
	protected abstract String getTemplateName();

	/**
	 * 该类基础包名
	 * @return
	 */
	protected abstract String getBasePackage();

}

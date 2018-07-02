package com.vip.xpf.generate;

import com.google.common.base.CaseFormat;

import java.math.BigDecimal;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.function.Consumer;

public class ModelCodeGenerator extends AbstractCodeGenerator {

	ModelCodeGenerator(DatabaseMetaData databaseMetaData, PackageBean packageBean, String tableName, String codeDir,
			String author) {
		super(databaseMetaData, packageBean, tableName, codeDir, author);
	}

	@Override
	protected List<Consumer<Map<String, Object>>> getfreeMarkerDataCompleters() {
		List<Consumer<Map<String, Object>>> completers = new LinkedList<>();
		List<String> imports = new LinkedList<>();
		completers.add(columnInfoCompleter(imports).andThen(importsCompleter(imports)));
		return completers;
	}

	@Override
	protected String getFileSuffix() {
		return ".java";
	}

	@Override
	protected String getTemplateName() {
		return "Model.ftl";
	}

	@Override
	protected String getBasePackage() {
		return packageBean.getModelPackage();
	}


	private Consumer<Map<String, Object>> importsCompleter(List<String> imports) {
		return freeMarkerData -> {
			Set<String> allImports = (Set<String>) freeMarkerData.get("imports");
			if (allImports == null) {
				allImports = new TreeSet<>();
				freeMarkerData.put("imports", allImports);
			}
			allImports.addAll(imports);
		};
	}

	private Consumer<Map<String, Object>> columnInfoCompleter(List<String> imports) {
		return freeMarkerData -> {
			try {
				ResultSet primaryKeyResultSet = databaseMetaData.getPrimaryKeys(null, null, tableName);
				Set<String> primaryKeyNames = new HashSet<>();
				while (primaryKeyResultSet.next()) {
					primaryKeyNames.add(primaryKeyResultSet.getString("COLUMN_NAME"));
				}
				List<ColumnInfo> columnInfos = new LinkedList<>();
				ResultSet columnResultSet = databaseMetaData.getColumns(null, "%", tableName, "%");
				while (columnResultSet.next()) {
					ColumnInfo columnInfo = new ColumnInfo();
					columnInfo.setColumnName(columnResultSet.getString("COLUMN_NAME"));
					columnInfo.setPropertyName(
							CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, columnInfo.getColumnName()));
					Class javaType = toJavaType(columnResultSet.getString("TYPE_NAME"),
							columnResultSet.getInt("COLUMN_SIZE"));
					if (!javaType.getName().startsWith("java.lang")) {
						imports.add(javaType.getName());
					}
					columnInfo.setPropertyType(javaType.getSimpleName());
					columnInfo.setComment(columnResultSet.getString("REMARKS"));
					columnInfo.setPrimaryKey(primaryKeyNames.contains(columnInfo.getColumnName()));
					columnInfo.setDefaultValue(columnResultSet.getString("COLUMN_DEF"));
					columnInfos.add(columnInfo);
				}
				freeMarkerData.put("columnInfos", columnInfos);
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		};
	}

	private Class toJavaType(String columnType, int columnSize) {
		Class javaType;
		switch (columnType) {
			case "SMALLINT":
			case "MEDIUMINT":
			case "INT":
			case "INTEGER":
				javaType = Integer.class;
				break;
			case "BIGINT":
				javaType = Long.class;
				break;
			case "DECIMAL":
				javaType = BigDecimal.class;
				break;
			case "FLOAT":
				javaType = Float.class;
				break;
			case "DOUBLE":
				javaType = Double.class;
				break;
			case "BIT":
			case "BOOLEAN":
				javaType = Boolean.class;
				break;
			case "TINYINT":
				if (columnSize == 1) {
					javaType = Boolean.class;
				} else {
					javaType = Integer.class;
				}
				break;
			case "DATE":
			case "TIME":
			case "DATETIME":
			case "TIMESTAMP":
				javaType = Date.class;
				break;
			case "VARCHAR":
			case "CHAR":
			case "TEXT":
				javaType = String.class;
				break;
			case "BLOB":
				javaType = Byte[].class;
				break;
			default:
				javaType = String.class;
		}
		return javaType;
	}

	public static ModelCodeGenerator build(DatabaseMetaData metaData, PackageBean packageBean, String tableName,
			String modelDir, String author) {
		return new ModelCodeGenerator(metaData, packageBean, tableName, modelDir, author);
	}
}

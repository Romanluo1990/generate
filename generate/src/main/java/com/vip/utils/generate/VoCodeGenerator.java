package com.vip.utils.generate;

import java.sql.DatabaseMetaData;

public class VoCodeGenerator extends ModelCodeGenerator {

	private VoCodeGenerator(DatabaseMetaData databaseMetaData, PackageBean packageBean, String tableNamePrefix,
			String tableName,
			String codeDir, String author) {
		super(databaseMetaData, packageBean, tableNamePrefix, tableName, codeDir, author);
	}

	@Override
	protected String getFileSuffix() {
		return "Vo.java";
	}

	@Override
	protected String getTemplateName() {
		return "Vo.ftl";
	}

	@Override
	protected String getBasePackage() {
		return packageBean.getVoPackage();
	}

	public static VoCodeGenerator build(DatabaseMetaData metaData, PackageBean packageBean, String tableNamePrefix,
			String tableName,
			String modelDir, String author) {
		return new VoCodeGenerator(metaData, packageBean, tableNamePrefix, tableName, modelDir, author);
	}
}

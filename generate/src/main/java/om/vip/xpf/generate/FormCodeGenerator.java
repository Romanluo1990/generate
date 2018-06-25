package om.vip.xpf.generate;

import java.sql.DatabaseMetaData;

public class FormCodeGenerator extends ModelCodeGenerator {

	private FormCodeGenerator(DatabaseMetaData databaseMetaData, PackageBean packageBean, String tableName,
			String codeDir, String author) {
		super(databaseMetaData, packageBean, tableName, codeDir, author);
	}

	@Override
	protected String getFileSuffix() {
		return "Form.java";
	}

	@Override
	protected String getTemplateName() {
		return "Form.ftl";
	}

	@Override
	protected String getBasePackage() {
		return packageBean.getFormPackage();
	}

	public static FormCodeGenerator build(DatabaseMetaData metaData, PackageBean packageBean, String tableName,
			String modelDir, String author) {
		return new FormCodeGenerator(metaData, packageBean, tableName, modelDir, author);
	}
}

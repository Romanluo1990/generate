package com.vip.xpf.generate;

import freemarker.template.TemplateException;

import java.io.IOException;
import java.sql.DatabaseMetaData;
import java.util.*;
import java.util.function.Consumer;

public class DaoCodeGenerator extends AbstractCodeGenerator {

	private final DaoImplCodeGenerator daoImplCodeGenerator;

	private DaoCodeGenerator(DatabaseMetaData databaseMetaData, PackageBean packageBean, String tableName,
			String codeDir, String author) {
		super(databaseMetaData, packageBean, tableName, codeDir, author);
		daoImplCodeGenerator = DaoImplCodeGenerator.build(databaseMetaData, packageBean, tableName, codeDir, author);
	}

	@Override
	protected List<Consumer<Map<String, Object>>> getfreeMarkerDataCompleters() {
		List<Consumer<Map<String, Object>>> completers = new LinkedList<>();
		List<String> imports = new LinkedList<>();
		imports.add(packageBean.getModelPackage() + "." + getClassName());
		completers.add(importsCompleter(imports));
		return completers;
	}

	@Override
	protected String getFileSuffix() {
		return "Dao.java";
	}

	@Override
	protected String getTemplateName() {
		return "Dao.ftl";
	}

	@Override
	protected String getBasePackage() {
		return packageBean.getDaoPackage();
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

	@Override
	public void generate() throws IOException, TemplateException {
		super.generate();
		daoImplCodeGenerator.generate();
	}

	public static DaoCodeGenerator build(DatabaseMetaData metaData, PackageBean packageBean, String tableName,
			String modelDir, String author) {
		return new DaoCodeGenerator(metaData, packageBean, tableName, modelDir, author);
	}
}

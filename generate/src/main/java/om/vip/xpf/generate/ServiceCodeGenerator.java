package om.vip.xpf.generate;

import java.sql.DatabaseMetaData;
import java.util.*;
import java.util.function.Consumer;

public class ServiceCodeGenerator extends AbstractCodeGenerator {

	private ServiceCodeGenerator(DatabaseMetaData databaseMetaData, PackageBean packageBean, String tableName,
			String codeDir, String author) {
		super(databaseMetaData, packageBean, tableName, codeDir, author);
	}

	@Override
	protected List<Consumer<Map<String, Object>>> getfreeMarkerDataCompleters() {
		List<Consumer<Map<String, Object>>> completers = new LinkedList<>();
		List<String> imports = new LinkedList<>();
		imports.add(packageBean.getDaoPackage() + "." + getClassName() + "Dao");
		completers.add(importsCompleter(imports));
		return completers;
	}

	@Override
	protected String getFileSuffix() {
		return "Service.java";
	}

	@Override
	protected String getTemplateName() {
		return "Service.ftl";
	}

	@Override
	protected String getBasePackage() {
		return packageBean.getServicePackage();
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

	public static ServiceCodeGenerator build(DatabaseMetaData metaData, PackageBean packageBean, String tableName,
			String modelDir, String author) {
		return new ServiceCodeGenerator(metaData, packageBean, tableName, modelDir, author);
	}
}

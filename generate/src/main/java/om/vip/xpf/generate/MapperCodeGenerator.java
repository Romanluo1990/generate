package om.vip.xpf.generate;

import java.sql.DatabaseMetaData;
import java.util.*;
import java.util.function.Consumer;

public class MapperCodeGenerator extends AbstractCodeGenerator {

	private MapperCodeGenerator(DatabaseMetaData databaseMetaData, PackageBean packageBean, String tableName,
			String codeDir, String author) {
		super(databaseMetaData, packageBean, tableName, codeDir, author);
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
		return "Mapper.java";
	}

	@Override
	protected String getTemplateName() {
		return "Mapper.ftl";
	}

	@Override
	protected String getBasePackage() {
		return packageBean.getMapperPackage();
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

	public static MapperCodeGenerator build(DatabaseMetaData metaData, PackageBean packageBean, String tableName,
			String modelDir, String author) {
		return new MapperCodeGenerator(metaData, packageBean, tableName, modelDir, author);
	}
}

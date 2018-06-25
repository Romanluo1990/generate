package om.vip.xpf.generate;

import java.sql.DatabaseMetaData;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class XmlCodeGenerator extends AbstractCodeGenerator {

	private XmlCodeGenerator(DatabaseMetaData databaseMetaData, PackageBean packageBean, String tableName,
			String codeDir, String author) {
		super(databaseMetaData, packageBean, tableName, codeDir, author);
	}

	@Override
	protected List<Consumer<Map<String, Object>>> getfreeMarkerDataCompleters() {
		List<Consumer<Map<String, Object>>> completers = new LinkedList<>();
		completers.add(mapperInfoCompleter());
		return completers;
	}

	private Consumer<Map<String, Object>> mapperInfoCompleter() {
		return freeMarkerData -> freeMarkerData.put("mapperPackage", packageBean.getMapperPackage());
	}

	@Override
	protected String getFileSuffix() {
		return ".xml";
	}

	@Override
	protected String getTemplateName() {
		return "Xml.ftl";
	}

	@Override
	protected String getBasePackage() {
		return packageBean.getXmlPackage();
	}

	public static XmlCodeGenerator build(DatabaseMetaData metaData, PackageBean packageBean, String tableName,
			String modelDir, String author) {
		return new XmlCodeGenerator(metaData, packageBean, tableName, modelDir, author);
	}
}

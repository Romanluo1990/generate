package com.vip.utils.generate;

import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.FileTemplateLoader;
import freemarker.cache.NullCacheStorage;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;

@Slf4j
public class FreeMarkerTemplateUtils {
	private FreeMarkerTemplateUtils() {
	}

	private static final Configuration CONFIGURATION =
			new Configuration();

	static {
		// 这里比较重要，用来指定加载模板所在的路径
		File file = new File(System.getProperty("user.dir") + "/templates");
		try {
			if(!file.exists())
				FreeMarkerTemplateUtils.CONFIGURATION.setTemplateLoader(new ClassTemplateLoader(FreeMarkerTemplateUtils.class, "/templates"));
			else
				FreeMarkerTemplateUtils.CONFIGURATION.setTemplateLoader(new FileTemplateLoader(file));
		} catch (IOException e) {
			throw new IllegalStateException(file.getAbsolutePath() + "不存在");
		}
		FreeMarkerTemplateUtils.CONFIGURATION.setDefaultEncoding("UTF-8");
		FreeMarkerTemplateUtils.CONFIGURATION.setTemplateExceptionHandler(
				TemplateExceptionHandler.RETHROW_HANDLER);
		FreeMarkerTemplateUtils.CONFIGURATION.setCacheStorage(NullCacheStorage.INSTANCE);
	}

	public static void setTemplatePath(String path){
		if(path == null)
			return;
		File file = new File(path);
		if(file.exists()){
			try {
				FreeMarkerTemplateUtils.CONFIGURATION.setTemplateLoader(new FileTemplateLoader(file));
			} catch (IOException e) {
				log.warn("{}不存在", path);
			}
		}
	}

	public static Template getTemplate(String templateName) throws IOException {
		try {
			return FreeMarkerTemplateUtils.CONFIGURATION.getTemplate(templateName);
		} catch (IOException e) {
			throw e;
		}
	}

	public static void clearCache() {
		FreeMarkerTemplateUtils.CONFIGURATION.clearTemplateCache();
	}
}

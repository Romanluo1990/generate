package com.vip.xpf.generate;

import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.NullCacheStorage;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

import java.io.IOException;

public class FreeMarkerTemplateUtils {
  private FreeMarkerTemplateUtils() {}

  private static final Configuration CONFIGURATION =
      new Configuration();

  static {
    // 这里比较重要，用来指定加载模板所在的路径
    FreeMarkerTemplateUtils.CONFIGURATION.setTemplateLoader(
        new ClassTemplateLoader(FreeMarkerTemplateUtils.class, "/templates"));
    FreeMarkerTemplateUtils.CONFIGURATION.setDefaultEncoding("UTF-8");
    FreeMarkerTemplateUtils.CONFIGURATION.setTemplateExceptionHandler(
        TemplateExceptionHandler.RETHROW_HANDLER);
    FreeMarkerTemplateUtils.CONFIGURATION.setCacheStorage(NullCacheStorage.INSTANCE);
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

package com.vip.xpf.api.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.vip.xpf.api.common.converter.DateConverter;
import com.vip.xpf.api.common.resolver.SelectConditionsResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

@Configuration
public class WebMvcConfig {

  @Bean
  public WebMvcConfigurer webMvcConfigurer() {
    ObjectMapper objectMapper = createMapper();
    return new WebMvcConfigurer() {
      @Override
      public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new MappingJackson2HttpMessageConverter(objectMapper));
      }

      @Override
      public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new SelectConditionsResolver(objectMapper));
      }

      @Override
      public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new DateConverter());
      }

      /** @param registry */
      @Override
      public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
            .addResourceHandler("swagger-ui.html")
            .addResourceLocations("classpath:/META-INF/resources/");
      }

      /** 配置servlet处理 */
      @Override
      public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
      }
    };
  }

  private ObjectMapper createMapper() {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    objectMapper
        .getSerializerProvider()
        .setNullValueSerializer(
            new JsonSerializer<Object>() {
              @Override
              public void serialize(Object value, JsonGenerator jgen, SerializerProvider provider)
                  throws IOException {
                jgen.writeString("");
              }
            });
    return objectMapper;
  }
}

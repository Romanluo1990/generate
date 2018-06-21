package com.vip.xpf.config;

import com.vip.xpf.common.interceptor.VersionInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class LogicWebMvcConfig {

  @Bean
  public WebMvcConfigurer adminWWebMvcConfigurer() {

    return new WebMvcConfigurer() {
      @Override
      public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new VersionInterceptor()).addPathPatterns("/**");
      }
    };
  }
}

package com.vip.xpf;

import com.didispace.swagger.EnableSwagger2Doc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.ServletContext;

@Slf4j
@EnableWebMvc
@EnableAsync
@EnableSwagger2Doc
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(getClass());
	}

	@Override
	protected WebApplicationContext createRootApplicationContext(ServletContext servletContext) {
		System.setProperty("spring.jndi.ignore", "true");
		return super.createRootApplicationContext(servletContext);
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}

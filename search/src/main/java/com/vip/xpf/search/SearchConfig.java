package com.vip.xpf.search;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.aop.Advice;
import org.springframework.context.annotation.Bean;

/**
 *
 * @Auther: romanluo
 * @Date: 2018/7/3
 * @Description:
 */
@Slf4j
public class SearchConfig {

	public static final String SEARCHER_ClASS = "com.vip.xpf.search.searcher.AbstractSearcher";
	public static final String SEARCHER_PACKAGE = "com.vip.xpf.search.searcher";

	@Bean
	public BeanFactorySearchAdvisor searchAdvisor(Advice searchInterceptor) {
		BeanFactorySearchAdvisor advisor = new BeanFactorySearchAdvisor();
		advisor.setAdvice(searchInterceptor);
		return advisor;
	}

	@Bean
	private Advice searchInterceptor() {
		return new SearchInterceptor();
	}

}

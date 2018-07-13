package com.vip.xpf.search;

import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractBeanFactoryPointcutAdvisor;

/**
 *
 * @Auther: romanluo
 * @Date: 2018/7/12
 * @Description:
 */
public class BeanFactorySearchAdvisor extends AbstractBeanFactoryPointcutAdvisor {

	@Override
	public Pointcut getPointcut() {
		return new SearchPointcut();
	}

}

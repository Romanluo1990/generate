package com.vip.xpf.search;

import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.lang.reflect.Method;

/**
 *
 * @Auther: romanluo
 * @Date: 2018/7/12
 * @Description:
 */
public class SearchPointcut extends StaticMethodMatcherPointcut implements Serializable {

	@Override
	public boolean matches(Method method, @Nullable Class<?> targetClass) {
		return method.getAnnotation(Searcher.class) != null;
	}
}

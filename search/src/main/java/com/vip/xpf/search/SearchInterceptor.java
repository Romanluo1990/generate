package com.vip.xpf.search;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.context.ApplicationContext;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Optional;

/**
 *
 * @Auther: romanluo
 * @Date: 2018/7/12
 * @Description:
 */
@Slf4j
public class SearchInterceptor implements MethodInterceptor {

	@Resource
	private ApplicationContext applicationContext;

	private final HashMap<Method, Method> searcherCache = new HashMap();

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		Method srcMethod = invocation.getMethod();
		Searcher searcher = srcMethod.getAnnotation(Searcher.class);
		Class clazz = searcher.searcher();
		Method[] methods = clazz.getMethods();
		Method advisorMethod = getAdvisorMethod(srcMethod, methods);
		if (advisorMethod != null) {
			try {
				Object searchHodler = applicationContext.getBean(clazz);
				return advisorMethod.invoke(searchHodler, invocation.getArguments());
			} catch (Exception e) {
				log.error("搜索引擎查询失败", e);
				return invocation.proceed();
			}
		} else {
			return invocation.proceed();
		}
	}

	private Method getAdvisorMethod(Method srcMethod, Method[] methods) {
		if (!searcherCache.containsKey(srcMethod)) {
			Optional<Method> methodOptional = Arrays.stream(methods).
					filter(method -> method.getName().equals(srcMethod.getName())).
					filter(method -> method.getParameterCount() == srcMethod.getParameterCount()).
					filter(method -> {
						boolean flag = true;
						Class[] srcParameterTypes = srcMethod.getParameterTypes();
						Class[] advisorParameterTypes = srcMethod.getParameterTypes();
						for (int i = 0; i < srcParameterTypes.length; i++) {
							if (!srcParameterTypes[i].equals(advisorParameterTypes[i])) {
								flag = false;
								break;
							}
						}
						return flag;
					}).findAny();
			if (methodOptional.isPresent()) {
				searcherCache.putIfAbsent(srcMethod, methodOptional.get());
			} else {
				searcherCache.putIfAbsent(srcMethod, null);
			}
		}
		return searcherCache.get(srcMethod);
	}

}

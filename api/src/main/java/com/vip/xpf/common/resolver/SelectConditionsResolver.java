package com.vip.xpf.common.resolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vip.xpf.dao.common.sql.SelectCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

/** SelectCondition查询参数解析 */
public class SelectConditionsResolver implements HandlerMethodArgumentResolver {

	private final ObjectMapper objectMapper;

	public SelectConditionsResolver() {
		this(new ObjectMapper());
	}

	public SelectConditionsResolver(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		if (!parameter.getParameterName().equals("conditions")) {
			return false;
		}
		Type[] types = ((ParameterizedType) parameter.getGenericParameterType()).getActualTypeArguments();
		return types.length == 1 && SelectCondition.class.equals(types[0]);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		String conditionsJson = webRequest.getParameter("conditions");
		if (StringUtils.isEmpty(conditionsJson)) {
			return Collections.emptyList();
		}
		return objectMapper.readValue(conditionsJson,
				objectMapper.getTypeFactory().constructParametricType(List.class, SelectCondition.class));
	}
}

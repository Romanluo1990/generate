package com.vip.xpf.common.util.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vip.xpf.common.exception.XpfRunTimeException;

import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * Created by roman.luo on 2017/8/3.
 */
public final class JsonUtils {

	private static class JacksonHolder {
		private static final ObjectMapper INSTANCE = new ObjectMapper();

		static {
			INSTANCE.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			INSTANCE.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
			INSTANCE.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		}
	}

	public static ObjectMapper getInstance() {
		return JacksonHolder.INSTANCE;
	}

	private JsonUtils() {
	}


	public static <O> String json(O o) {
		if (o == null) {
			return null;
		}
		try {
			return getInstance().writeValueAsString(o);
		} catch (JsonProcessingException e) {
			throw new XpfRunTimeException("json序列化失败", e);
		}
	}

	public static <T> T parse(String json, Class<T> clazz) {
		if (json == null) {
			return null;
		}
		try {
			return getInstance().readValue(json, clazz);
		} catch (IOException e) {
			throw new XpfRunTimeException("json反列化失败", e);
		}
	}

	public static <T> T parse(String json, JavaType valueType) {
		if (json == null) {
			return null;
		}
		try {
			return getInstance().readValue(json, valueType);
		} catch (IOException e) {
			throw new XpfRunTimeException("json反列化失败", e);
		}
	}

	public static <T> T parse(String json, TypeReference valueTypeRef) {
		if (json == null) {
			return null;
		}
		try {
			return getInstance().readValue(json, valueTypeRef);
		} catch (IOException e) {
			throw new XpfRunTimeException("json反列化失败", e);
		}
	}

	public static JavaType constructParametricType(Class<?> parametrized, Class... parameterClasses) {
		return getInstance().getTypeFactory().constructParametricType(parametrized, parameterClasses);
	}
}

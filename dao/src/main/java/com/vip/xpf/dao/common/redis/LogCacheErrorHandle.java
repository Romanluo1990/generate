package com.vip.xpf.dao.common.redis;

import com.vip.xpf.common.util.json.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.lang.Nullable;

@Slf4j
public class LogCacheErrorHandle implements CacheErrorHandler {

	@Override
	public void handleCacheGetError(RuntimeException exception, Cache cache, Object key) {
		log.error("key[{}]CacheGet失败", key, exception);
	}

	@Override
	public void handleCachePutError(RuntimeException exception, Cache cache, Object key, @Nullable Object value) {
		log.error("key[{}],value[{}]CachePut失败", key, JsonUtils.json(value), exception);
	}

	@Override
	public void handleCacheEvictError(RuntimeException exception, Cache cache, Object key) {
		log.error("key[{}]CacheEvict失败", key, exception);
	}

	@Override
	public void handleCacheClearError(RuntimeException exception, Cache cache) {
		log.error("CacheClear失败", exception);
	}
}

package com.vip.xpf.dao.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vip.xpf.dao.common.redis.LogCacheErrorHandle;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.lang.Nullable;

@Configuration
@EnableCaching(mode = AdviceMode.ASPECTJ)
public class RedisConfig extends CachingConfigurerSupport {

	@Bean
	public RedisCacheConfiguration redisCacheConfiguration(CacheProperties cacheProperties) {
		CacheProperties.Redis redisProperties = cacheProperties.getRedis();
		org.springframework.data.redis.cache.RedisCacheConfiguration config = org.springframework.data.redis.cache.RedisCacheConfiguration
				.defaultCacheConfig();
		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = getJsonRedisSerializer();
		config = config.serializeValuesWith(
				RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer));
		if (redisProperties.getTimeToLive() != null) {
			config = config.entryTtl(redisProperties.getTimeToLive());
		}
		if (redisProperties.getKeyPrefix() != null) {
			config = config.prefixKeysWith(redisProperties.getKeyPrefix());
		}
		if (!redisProperties.isCacheNullValues()) {
			config = config.disableCachingNullValues();
		}
		if (!redisProperties.isUseKeyPrefix()) {
			config = config.disableKeyPrefix();
		}
		return config;
	}

	private Jackson2JsonRedisSerializer getJsonRedisSerializer() {
		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(om);
		return jackson2JsonRedisSerializer;
	}

	@Nullable
	@Override
	public CacheErrorHandler errorHandler() {
		return new LogCacheErrorHandle();
	}

}

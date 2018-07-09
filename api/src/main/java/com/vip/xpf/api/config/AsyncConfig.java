package com.vip.xpf.api.config;

import org.springframework.context.annotation.AdviceMode;
import org.springframework.lang.Nullable;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 *
 * @Auther: romanluo
 * @Date: 2018/7/9
 * @Description:
 */
@EnableAsync(mode = AdviceMode.ASPECTJ)
public class AsyncConfig implements AsyncConfigurer {

	@Nullable
	@Override
	public Executor getAsyncExecutor() {
		ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
		threadPoolTaskExecutor.setCorePoolSize(100);
		threadPoolTaskExecutor.initialize();
		return threadPoolTaskExecutor;
	}

}

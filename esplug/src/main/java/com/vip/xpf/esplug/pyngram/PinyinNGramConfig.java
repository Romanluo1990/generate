package com.vip.xpf.esplug.pyngram;

import org.elasticsearch.common.settings.Settings;

/**
 *
 * @Description:
 * @Author: romanluo
 * @Date: 2018/7/7
 */
public class PinyinNGramConfig {

	public static final int DEFAULT_MIN_GRAM_SIZE = 1;

	public static final int DEFAULT_MAX_GRAM_SIZE = 5;

	private final int minGram;
	private final int maxGram;

	public PinyinNGramConfig() {
		minGram = DEFAULT_MIN_GRAM_SIZE;
		maxGram = DEFAULT_MAX_GRAM_SIZE;
	}

	public PinyinNGramConfig(Settings settings) {
		minGram = settings.getAsInt("min_gram", DEFAULT_MIN_GRAM_SIZE);
		maxGram = settings.getAsInt("max_gram", DEFAULT_MAX_GRAM_SIZE);
	}

	public int getMinGram() {
		return minGram;
	}

	public int getMaxGram() {
		return maxGram;
	}
}

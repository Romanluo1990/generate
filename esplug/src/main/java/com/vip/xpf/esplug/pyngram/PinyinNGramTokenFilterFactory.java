package com.vip.xpf.esplug.pyngram;

import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.IndexSettings;
import org.elasticsearch.index.analysis.AbstractTokenFilterFactory;

/**
 *
 * @Description:
 * @Author: romanluo
 * @Date: 2018/7/7
 */
public class PinyinNGramTokenFilterFactory extends AbstractTokenFilterFactory {

	private final PinyinNGramConfig config;

	/** Creates a new EdgeNGramFilterFactory */
	public PinyinNGramTokenFilterFactory(IndexSettings indexSettings, Environment env, String name, Settings settings) {
		super(indexSettings, name, settings);
		config = new PinyinNGramConfig(settings);
	}

	@Override
	public TokenFilter create(TokenStream input) {
		return new PinyinNGramTokenFilter(input, config);
	}
}


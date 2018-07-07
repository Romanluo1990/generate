package com.vip.xpf.esplug.pyngram;


import org.apache.lucene.analysis.Analyzer;
import org.elasticsearch.index.analysis.AnalyzerProvider;
import org.elasticsearch.index.analysis.TokenFilterFactory;
import org.elasticsearch.index.analysis.TokenizerFactory;
import org.elasticsearch.indices.analysis.AnalysisModule;
import org.elasticsearch.plugins.AnalysisPlugin;
import org.elasticsearch.plugins.Plugin;

import java.util.Collections;
import java.util.Map;

/**
 *
 * @Description:
 * @Author: romanluo
 * @Date: 2018/7/7
 */
public class PinyinNGramPlugin extends Plugin implements AnalysisPlugin {

	@Override
	public Map<String, AnalysisModule.AnalysisProvider<TokenizerFactory>> getTokenizers() {
		return Collections.emptyMap();
	}

	@Override
	public Map<String, AnalysisModule.AnalysisProvider<TokenFilterFactory>> getTokenFilters() {
		return Collections.singletonMap("pinyin_ngram", PinyinNGramTokenFilterFactory::new);
	}

	@Override
	public Map<String, AnalysisModule.AnalysisProvider<AnalyzerProvider<? extends Analyzer>>> getAnalyzers() {
		return Collections.emptyMap();
	}
}

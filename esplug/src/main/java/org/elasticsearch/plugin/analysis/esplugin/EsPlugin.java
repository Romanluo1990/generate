package org.elasticsearch.plugin.analysis.esplugin;

import com.vip.xpf.esplug.pyngram.PinyinNGramPlugin;
import org.apache.lucene.analysis.Analyzer;
import org.elasticsearch.index.analysis.AnalyzerProvider;
import org.elasticsearch.index.analysis.TokenFilterFactory;
import org.elasticsearch.index.analysis.TokenizerFactory;
import org.elasticsearch.indices.analysis.AnalysisModule;
import org.elasticsearch.plugin.analysis.ik.AnalysisIkPlugin;
import org.elasticsearch.plugin.analysis.pinyin.AnalysisPinyinPlugin;
import org.elasticsearch.plugins.AnalysisPlugin;
import org.elasticsearch.plugins.Plugin;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @Auther: romanluo
 * @Date: 2018/7/7
 * @Description:
 */
public class EsPlugin extends Plugin implements AnalysisPlugin {

	private final List<AnalysisPlugin> plugins = Arrays
			.asList(new PinyinNGramPlugin(), new AnalysisPinyinPlugin(), new AnalysisIkPlugin());

	@Override
	public Map<String, AnalysisModule.AnalysisProvider<TokenizerFactory>> getTokenizers() {
		Map<String, AnalysisModule.AnalysisProvider<TokenizerFactory>> extra = new HashMap<>();
		plugins.stream().map(AnalysisPlugin::getTokenizers).forEach(extra::putAll);
		return extra;
	}

	@Override
	public Map<String, AnalysisModule.AnalysisProvider<TokenFilterFactory>> getTokenFilters() {
		Map<String, AnalysisModule.AnalysisProvider<TokenFilterFactory>> extra = new HashMap<>();
		plugins.stream().map(AnalysisPlugin::getTokenFilters).forEach(extra::putAll);
		return extra;
	}

	@Override
	public Map<String, AnalysisModule.AnalysisProvider<AnalyzerProvider<? extends Analyzer>>> getAnalyzers() {
		Map<String, AnalysisModule.AnalysisProvider<AnalyzerProvider<? extends Analyzer>>> extra = new HashMap<>();
		plugins.stream().map(AnalysisPlugin::getAnalyzers).forEach(extra::putAll);
		return extra;
	}
}

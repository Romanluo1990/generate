package com.vip.xpf.esplug.pyngram;

import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.*;

import java.io.IOException;

/**
 *
 * @Description:
 * @Author: romanluo
 * @Date: 2018/7/7
 */
public final class PinyinNGramTokenFilter extends TokenFilter {


	private final PinyinNGramConfig pinyinNGramConfig;

	private int endSize;
	private char[] curTermBuffer;
	private int curTermLength;
	private int curGramSize;

	private final CharTermAttribute termAtt = addAttribute(CharTermAttribute.class);
	private final OffsetAttribute offsetAtt = addAttribute(OffsetAttribute.class);
	private final PositionIncrementAttribute posIncrAtt = addAttribute(PositionIncrementAttribute.class);
	private final PositionLengthAttribute posLenAtt = addAttribute(PositionLengthAttribute.class);
	private final TypeAttribute typeAtt = addAttribute(TypeAttribute.class); // 类型属性

	protected PinyinNGramTokenFilter(TokenStream input, PinyinNGramConfig pinyinNGramConfig) {
		super(input);
		this.pinyinNGramConfig = pinyinNGramConfig;
	}

	@Override
	public final boolean incrementToken() throws IOException {
		while (true) {
			if (curTermBuffer == null) {
				if (!input.incrementToken()) {
					return false;
				} else {
					curTermBuffer = termAtt.buffer().clone();
					curTermLength = termAtt.length();
					curGramSize = pinyinNGramConfig.getMinGram();
					endSize = Math.min(pinyinNGramConfig.getMaxGram(), curTermLength);
				}
			}
			for (char c : curTermBuffer) {
				if (isChinese(c)) {
					curTermBuffer = null;
					return true;
				}
			}
			if (curGramSize <= endSize) {
				termAtt.copyBuffer(curTermBuffer, 0, curGramSize);
				curGramSize++;
				return true;
			}

			curTermBuffer = null;
		}
	}

	/**
	 * 判断字符是否是中文
	 *
	 * @param a
	 * @return
	 */
	public static boolean isChinese(char a) {
		int v = a;
		return (v >= 19968) && (v <= 171941);
	}

	@Override
	public void reset() throws IOException {
		super.reset();
		curTermBuffer = null;
	}
}

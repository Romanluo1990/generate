package com.vip.xpf.search.indexmodel;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 *
 * 档期索引
 * @Auther: romanluo
 * @Date: 2018/6/28
 * @Description:
 */
@Data
@Document(indexName = "xpf", type = "salePlan")
public class SalePlanIndex extends IndexModel {

	/**
	 *档期名称
	 */
	@Field(type = FieldType.Text, searchAnalyzer = "chinese_analyzer_query", analyzer = "chinese_analyzer_index")
	private String name;

	@Field(type = FieldType.Text, searchAnalyzer = "chinese_analyzer_query", analyzer = "chinese_analyzer_index")
	private String product;

	@Field(type = FieldType.Text, searchAnalyzer = "chinese_analyzer_query", analyzer = "chinese_analyzer_index")
	private String brand;

	@Field(type = FieldType.Text, searchAnalyzer = "whitespace", analyzer = "whitespace")
	private String category;
}

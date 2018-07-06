package com.vip.xpf.search.indexmodel;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 *
 * @Auther: romanluo
 * @Date: 2018/6/28
 * @Description:
 */
@Data
@Document(indexName = "xpf", type = "account")
public class AccountIndex extends IndexModel {

	@Field(type = FieldType.Text, searchAnalyzer = "chinese_analyzer_query", analyzer = "chinese_analyzer_index")
	private String nickName;
}

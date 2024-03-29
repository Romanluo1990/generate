package com.vip.xpf.search.repository;

import com.vip.xpf.search.indexmodel.AccountIndex;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 *
 * @Auther: romanluo
 * @Date: 2018/6/28
 * @Description:
 */
public interface AccountSearchRepository extends ElasticsearchRepository<AccountIndex, Long> {


}

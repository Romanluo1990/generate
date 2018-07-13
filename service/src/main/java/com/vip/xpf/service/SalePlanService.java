package com.vip.xpf.service;

import com.github.pagehelper.PageInfo;
import com.vip.xpf.dao.SalePlanDao;
import com.vip.xpf.dao.common.sql.PageSelect;
import com.vip.xpf.dao.common.sql.SelectCondition;
import com.vip.xpf.model.SalePlan;
import com.vip.xpf.search.Searcher;
import com.vip.xpf.search.searcher.SalePlanSearcher;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @desc：档期表Service
 * @author romanluo
 * @date 2018/07/09
 */
@Service
public class SalePlanService extends BaseService<SalePlanDao> {

	@Searcher(searcher = SalePlanSearcher.class)
	public PageInfo<SalePlan> search(PageSelect pageSelect, List<SelectCondition> conditions) {
		return dao.pageByConditions(pageSelect, conditions);
	}

}
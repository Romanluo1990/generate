package com.vip.xpf.dao.impl;

import com.github.pagehelper.PageInfo;
import com.vip.xpf.dao.PageDao;
import com.vip.xpf.dao.common.sql.ConditionsQueryMapper;
import com.vip.xpf.dao.common.sql.PageSelect;
import com.vip.xpf.dao.common.sql.SelectCondition;
import com.vip.xpf.model.Account;
import com.vip.xpf.model.Identity;

import java.util.Collections;
import java.util.List;

public class PageDaoImpl<M extends ConditionsQueryMapper<E>, E extends Identity> extends BaseDaoImpl<M, E>
		implements PageDao<E> {

	@Override
	public PageInfo<Account> page(PageSelect pageSelect) {
		return pageByConditions(pageSelect, Collections.emptyList());
	}

	@Override
	public PageInfo<Account> pageByConditions(PageSelect pageSelect, List<SelectCondition> conditions) {
		return new PageInfo<>(mapper.listByConditions(conditions));
	}

	@Override
	public long countByConditions(List<SelectCondition> conditions) {
		return mapper.countByConditions(conditions);
	}
}

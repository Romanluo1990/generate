package com.vip.xpf.dao;

import com.github.pagehelper.PageInfo;
import com.vip.xpf.dao.common.sql.PageSelect;
import com.vip.xpf.dao.common.sql.SelectCondition;
import com.vip.xpf.model.Identity;

import java.util.List;

public interface PageDao<E extends Identity> extends BaseDao<E> {

	PageInfo<E> page(PageSelect pageSelect);

	PageInfo<E> pageByConditions(PageSelect pageSelect, List<SelectCondition> conditions);

	long countByConditions(List<SelectCondition> conditions);
}

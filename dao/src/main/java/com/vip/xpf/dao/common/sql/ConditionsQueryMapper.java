package com.vip.xpf.dao.common.sql;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ConditionsQueryMapper<E> extends Mapper<E> {

	@SelectProvider(type = ConditionsSelectProvider.class, method = "dynamicSQL")
	<E> List<E> listByConditions(@Param("conditions") List<SelectCondition> conditions);

	@SelectProvider(type = ConditionsSelectProvider.class, method = "dynamicSQL")
	long countByConditions(List<SelectCondition> conditions);
}

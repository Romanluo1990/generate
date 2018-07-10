package com.vip.xpf.dao;

import com.vip.xpf.model.SalePlanProduct;

import java.util.List;

/**
 * @desc：选品表Dao
 * @author romanluo
 * @date 2018/07/09
 */
public interface SalePlanProductDao extends BaseDao<SalePlanProduct> {

	List<SalePlanProduct> listByPlanId(long planId);
}
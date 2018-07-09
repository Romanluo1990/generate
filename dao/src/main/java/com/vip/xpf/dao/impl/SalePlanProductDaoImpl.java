package com.vip.xpf.dao.impl;

import com.vip.xpf.dao.SalePlanProductDao;
import com.vip.xpf.dao.common.sql.ModifyEventType;
import com.vip.xpf.dao.impl.event.ModifySalePlanProductEvent;
import com.vip.xpf.dao.mapper.SalePlanProductMapper;
import com.vip.xpf.model.SalePlanProduct;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @desc：选品表Dao
 * @author romanluo
 * @date 2018/07/09
 */
@Repository
public class SalePlanProductDaoImpl extends BaseDaoImpl<SalePlanProductMapper, SalePlanProduct>
		implements SalePlanProductDao {

	@Resource
	private ApplicationContext applicationContext;

	@Override
	public boolean save(SalePlanProduct salePlanProduct) {
		boolean result = super.save(salePlanProduct);
		if (result) {
			ModifySalePlanProductEvent salePlanProductEvent = new ModifySalePlanProductEvent(this,
					getById(salePlanProduct.getId()), ModifyEventType.ADD);
			applicationContext.publishEvent(salePlanProductEvent);
		}
		return result;
	}

	@Override
	public boolean deleteById(long id) {
		boolean result = super.deleteById(id);
		if (result) {
			ModifySalePlanProductEvent salePlanProductEvent = new ModifySalePlanProductEvent(this, getById(id),
					ModifyEventType.DELETE);
			applicationContext.publishEvent(salePlanProductEvent);
		}
		return result;
	}

	@Override
	public boolean updateById(SalePlanProduct salePlanProduct) {
		boolean result = super.updateById(salePlanProduct);
		if (result) {
			ModifySalePlanProductEvent salePlanProductEvent = new ModifySalePlanProductEvent(this,
					getById(salePlanProduct.getId()), ModifyEventType.UPDATE);
			applicationContext.publishEvent(salePlanProductEvent);
		}
		return result;
	}

	@Override
	public boolean updateByIdSelective(SalePlanProduct salePlanProduct) {
		boolean result = super.updateByIdSelective(salePlanProduct);
		if (result) {
			ModifySalePlanProductEvent salePlanProductEvent = new ModifySalePlanProductEvent(this,
					getById(salePlanProduct.getId()), ModifyEventType.UPDATE);
			applicationContext.publishEvent(salePlanProductEvent);
		}
		return result;
	}

}
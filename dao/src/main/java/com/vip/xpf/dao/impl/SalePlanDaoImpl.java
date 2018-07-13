package com.vip.xpf.dao.impl;

import com.vip.xpf.dao.SalePlanDao;
import com.vip.xpf.dao.common.sql.ModifyEventType;
import com.vip.xpf.dao.impl.event.ModifySalePlanEvent;
import com.vip.xpf.dao.mapper.SalePlanMapper;
import com.vip.xpf.model.SalePlan;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @desc：档期表Dao
 * @author romanluo
 * @date 2018/07/09
 */
@Repository
public class SalePlanDaoImpl extends PageDaoImpl<SalePlanMapper, SalePlan> implements SalePlanDao {

	@Resource
	private ApplicationContext applicationContext;

	@Override
	public boolean save(SalePlan salePlan) {
		boolean result = super.save(salePlan);
		if (result) {
			ModifySalePlanEvent salePlanEvent = new ModifySalePlanEvent(this, getById(salePlan.getId()),
					ModifyEventType.ADD);
			applicationContext.publishEvent(salePlanEvent);
		}
		return result;
	}

	@Override
	public boolean deleteById(long id) {
		boolean result = super.deleteById(id);
		if (result) {
			ModifySalePlanEvent salePlanEvent = new ModifySalePlanEvent(this, getById(id), ModifyEventType.DELETE);
			applicationContext.publishEvent(salePlanEvent);
		}
		return result;
	}

	@Override
	public boolean updateById(SalePlan salePlan) {
		boolean result = super.updateById(salePlan);
		if (result) {
			ModifySalePlanEvent salePlanEvent = new ModifySalePlanEvent(this, getById(salePlan.getId()),
					ModifyEventType.UPDATE);
			applicationContext.publishEvent(salePlanEvent);
		}
		return result;
	}

	@Override
	public boolean updateByIdSelective(SalePlan salePlan) {
		boolean result = super.updateByIdSelective(salePlan);
		if (result) {
			ModifySalePlanEvent salePlanEvent = new ModifySalePlanEvent(this, getById(salePlan.getId()),
					ModifyEventType.UPDATE);
			applicationContext.publishEvent(salePlanEvent);
		}
		return result;
	}

}
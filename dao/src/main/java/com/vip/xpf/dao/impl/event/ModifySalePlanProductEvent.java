package com.vip.xpf.dao.impl.event;

import com.vip.xpf.dao.common.sql.ModifyEntityEvent;
import com.vip.xpf.dao.common.sql.ModifyEventType;
import com.vip.xpf.model.SalePlanProduct;

/**
 *
 * 档期销售产品更新事件
 * @Auther: romanluo
 * @Date: 2018/6/28
 * @Description:
 */
public class ModifySalePlanProductEvent extends ModifyEntityEvent<SalePlanProduct> {

	public ModifySalePlanProductEvent(Object source, SalePlanProduct entity, ModifyEventType modifyEventType) {
		super(source, entity, modifyEventType);
	}
}

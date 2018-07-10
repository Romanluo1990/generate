package com.vip.xpf.dao.impl.event;

import com.vip.xpf.dao.common.sql.ModifyEntityEvent;
import com.vip.xpf.dao.common.sql.ModifyEventType;
import com.vip.xpf.model.Identity;

/**
 *
 * 档期更新事件
 * @Auther: romanluo
 * @Date: 2018/6/28
 * @Description:
 */
public class ModifySalePlanEvent extends ModifyEntityEvent {

	public ModifySalePlanEvent(Object source, Identity entity, ModifyEventType modifyEventType) {
		super(source, entity, modifyEventType);
	}
}

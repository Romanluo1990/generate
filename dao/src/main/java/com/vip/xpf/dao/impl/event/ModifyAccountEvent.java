package com.vip.xpf.dao.impl.event;

import com.vip.xpf.dao.common.sql.ModifyEntityEvent;
import com.vip.xpf.dao.common.sql.ModifyEventType;
import com.vip.xpf.model.Account;

/**
 *
 * @Auther: romanluo
 * @Date: 2018/6/28
 * @Description:
 */
public class ModifyAccountEvent extends ModifyEntityEvent<Account> {

	public ModifyAccountEvent(Object source, Account entity, ModifyEventType modifyEventType) {
		super(source, entity, modifyEventType);
	}
}

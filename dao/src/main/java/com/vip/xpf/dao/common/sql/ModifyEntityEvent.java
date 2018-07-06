package com.vip.xpf.dao.common.sql;

import com.vip.xpf.model.Identity;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 *
 * @Auther: romanluo
 * @Date: 2018/6/28
 * @Description: 更新事件
 */
@Getter
public class ModifyEntityEvent<E extends Identity> extends ApplicationEvent {

	private final E entity;

	private final ModifyEventType modifyEventType;

	public ModifyEntityEvent(Object source, E entity, ModifyEventType modifyEventType) {
		super(source);
		this.entity = entity;
		this.modifyEventType = modifyEventType;
	}
}

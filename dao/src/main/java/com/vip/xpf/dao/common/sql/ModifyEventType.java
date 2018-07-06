package com.vip.xpf.dao.common.sql;

/**
 *
 * @Auther: romanluo
 * @Date: 2018/6/28
 * @Description: entity操作类型
 */

/**
 *
 * @Auther: romanluo
 * @Date: 2018/6/28
 * @Description: entity操作类型
 */

public enum ModifyEventType {

	ADD("添加"), DELETE("删除"), UPDATE("修改");

	private final String desc;

	ModifyEventType(String desc) {
		this.desc = desc;
	}

}

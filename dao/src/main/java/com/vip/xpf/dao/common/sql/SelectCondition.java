package com.vip.xpf.dao.common.sql;

public class SelectCondition {
	private String fieldName;

	private String operator;

	private String value;

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getOperator() {
		return Symbol.valueOf(operator).getOperator();
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

}

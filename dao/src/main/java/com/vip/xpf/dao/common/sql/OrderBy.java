package com.vip.xpf.dao.common.sql;

public class OrderBy {

	private static final String DESC = "desc";

	private static final String ASC = "asc";

	private String name;

	private boolean type; //true  asc ; false desc

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean getType() {
		return type;
	}

	public void setType(boolean type) {
		this.type = type;
	}

	public void setType(String type) {
		this.type = DESC.equals(type.toLowerCase()) ? false : true;
	}

	public String getTypeString() {
		return type ? ASC : DESC;
	}
}
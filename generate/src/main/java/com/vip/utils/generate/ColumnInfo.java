package com.vip.utils.generate;

import lombok.Data;

@Data
public class ColumnInfo {

	private boolean primaryKey;

	private String columnName;

	private String propertyName;

	private String propertyType;

	private String defaultValue;

	private String comment;
}

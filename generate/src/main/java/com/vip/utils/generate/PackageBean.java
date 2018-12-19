package com.vip.utils.generate;

import lombok.Data;

@Data
public class PackageBean {

	private String basePackage;

	public String getModelPackage() {
		return basePackage + ".model";
	}

	public String getMapperPackage() {
		return basePackage + ".dao.repository";
	}

	public String getXmlPackage() {
		return "mapper";
	}

	public String getDaoPackage() {
		return basePackage + ".dao";
	}

	public String getServicePackage() {
		return basePackage + ".service";
	}

	public String getVoPackage() {
		return basePackage + ".controller.vo";
	}

	public String getFormPackage() {
		return basePackage + ".controller.form";
	}

	public String getControllerPackage() {
		return basePackage + ".controller";
	}

}

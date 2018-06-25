package com.vip.xpf.controller.form;

import lombok.Data;

@Data
public class AccountForm {

	private Long id;

	private String openId;

	private String nickName;

	private String image;

	private String phone;

	private Integer type;

	private Integer status;

	private String statusMessage;

	private String userName;
}

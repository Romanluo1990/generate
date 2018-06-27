package com.vip.xpf.logic.controller.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AccountVo {

	private Long id;

	private String openId;

	@ApiModelProperty(name = "昵称", example = "张三")
	private String nickName;

	private String image;

	private String phone;

	private Integer type;

	private String userName;
}

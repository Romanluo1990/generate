package com.vip.xpf.logic.controller.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @desc：品牌表Form模型
 * @author romanluo
 * @date 2018/07/09
 */
@Data
public class BrandForm {

	@ApiModelProperty(name = " 自增ID", example = "")
	private Long id;

	@ApiModelProperty(name = "品牌编码", example = "")
	private String brandSn;

	@ApiModelProperty(name = "品牌中文名称", example = "")
	private String brandCnName;

	@ApiModelProperty(name = "品牌英文名称", example = "")
	private String brandEnName;

	@ApiModelProperty(name = "品牌logo", example = "")
	private String brandLogo;

	@ApiModelProperty(name = "品牌描述", example = "")
	private String description;

	@ApiModelProperty(name = "（默认0：未删除，1：已删除）", example = "0")
	private Boolean isDeleted;

	@ApiModelProperty(name = "创建时间", example = "0000-00-00 00:00:00")
	private Date createTime;

	@ApiModelProperty(name = "更新时间", example = "CURRENT_TIMESTAMP")
	private Date updateTime;


}
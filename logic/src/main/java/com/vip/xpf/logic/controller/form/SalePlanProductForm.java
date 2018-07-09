package com.vip.xpf.logic.controller.form;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @desc：选品表Form模型
 * @author romanluo
 * @date 2018/07/09
 */
@Data
public class SalePlanProductForm{

    @ApiModelProperty(name = "自增ID", example = "")
    private Long id;

    @ApiModelProperty(name = "档期ID", example = "")
    private Long planId;

    @ApiModelProperty(name = "spu_id", example = "")
    private String spuId;

    @ApiModelProperty(name = "最高价", example = "")
    private Long maxPrice;

    @ApiModelProperty(name = "最低价", example = "")
    private Long minPrice;

    @ApiModelProperty(name = "创建时间", example = "CURRENT_TIMESTAMP")
    private Date createTime;


}
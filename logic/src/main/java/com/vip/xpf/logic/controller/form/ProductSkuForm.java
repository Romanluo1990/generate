package com.vip.xpf.logic.controller.form;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @desc：商品skuForm模型
 * @author romanluo
 * @date 2018/07/09
 */
@Data
public class ProductSkuForm{

    @ApiModelProperty(name = "自增ID", example = "")
    private Long id;

    @ApiModelProperty(name = "产品ID", example = "")
    private String productId;

    @ApiModelProperty(name = "产品规格属性", example = "{}")
    private String standardProps;

    @ApiModelProperty(name = "自定义属性值(json)", example = "{}")
    private String extProps;

    @ApiModelProperty(name = "权重", example = "0")
    private Integer weight;

    @ApiModelProperty(name = "分销价", example = "0")
    private Long distributionPrice;

    @ApiModelProperty(name = "建议零售价", example = "0")
    private Long suggestRetailPrice;

    @ApiModelProperty(name = "市场价", example = "0")
    private Long marketPrice;

    @ApiModelProperty(name = "长图json", example = "")
    private String longImages;

    @ApiModelProperty(name = "方图json", example = "")
    private String squareImages;

    @ApiModelProperty(name = "库存数量", example = "")
    private Integer amount;

    @ApiModelProperty(name = "默认 0，1 已删 除", example = "0")
    private Boolean isDeleted;

    @ApiModelProperty(name = "修改时间", example = "CURRENT_TIMESTAMP")
    private Date updateTime;

    @ApiModelProperty(name = "创建时间", example = "0000-00-00 00:00:00")
    private Date createTime;


}
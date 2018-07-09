package com.vip.xpf.logic.controller.form;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @desc：商品表Form模型
 * @author romanluo
 * @date 2018/07/09
 */
@Data
public class ProductSpuForm{

    @ApiModelProperty(name = "自增ID", example = "")
    private Long id;

    @ApiModelProperty(name = "商品SN", example = "")
    private String productSn;

    @ApiModelProperty(name = "商品名称", example = "")
    private String title;

    @ApiModelProperty(name = "短视频", example = "")
    private String shortVideo;

    @ApiModelProperty(name = "长视频", example = "")
    private String longVideo;

    @ApiModelProperty(name = "核心描述", example = "")
    private String subTitle;

    @ApiModelProperty(name = "商品分类ID", example = "0")
    private Integer categoryId;

    @ApiModelProperty(name = "品牌ID", example = "")
    private String brandId;

    @ApiModelProperty(name = "自定义属性(json：["name":"field1","value":"value1"])", example = "{}")
    private String extProps;

    @ApiModelProperty(name = "商品备注", example = "")
    private String remark;

    @ApiModelProperty(name = "（默认0：未删除，1：已删除）", example = "0")
    private Boolean isDeleted;

    @ApiModelProperty(name = "创建时间", example = "0000-00-00 00:00:00")
    private Date createTime;

    @ApiModelProperty(name = "修改时间", example = "CURRENT_TIMESTAMP")
    private Date updateTime;


}
package com.vip.xpf.logic.controller.form;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @desc：品类表Form模型
 * @author romanluo
 * @date 2018/07/09
 */
@Data
public class CategoryForm{

    @ApiModelProperty(name = "自增ID", example = "")
    private Long id;

    @ApiModelProperty(name = "品类父ID(一级品类：-1)", example = "0")
    private Long parentId;

    @ApiModelProperty(name = "品类编码（10|10|10）", example = "")
    private String cateSn;

    @ApiModelProperty(name = "品类ID", example = "0")
    private Integer cateId;

    @ApiModelProperty(name = "品类名称", example = "")
    private String cateName;

    @ApiModelProperty(name = "品类层级（1：一级品类，2：二级品类 3：三级品类）", example = "0")
    private Integer cateLevel;

    @ApiModelProperty(name = "（默认0：未删除，1：已删除）", example = "0")
    private Boolean isDeleted;

    @ApiModelProperty(name = "创建时间", example = "0000-00-00 00:00:00")
    private Date createTime;

    @ApiModelProperty(name = "修改时间", example = "CURRENT_TIMESTAMP")
    private Date updateTime;


}
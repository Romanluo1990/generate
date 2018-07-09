package com.vip.xpf.logic.controller.vo;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @desc：档期表VO模型
 * @author romanluo
 * @date 2018/07/09
 */
@Data
public class SalePlanVo{

    @ApiModelProperty(name = "自增ID", example = "")
    private Long id;

    @ApiModelProperty(name = "档期SN", example = "")
    private String actSn;

    @ApiModelProperty(name = "档期名称", example = "")
    private String name;

    @ApiModelProperty(name = "档期图片", example = "")
    private String logo;

    @ApiModelProperty(name = "档期描述", example = "")
    private String desc;

    @ApiModelProperty(name = "档期热卖标签状态 （默认：0:非热卖，1：热卖）", example = "0")
    private Integer hotStatus;

    @ApiModelProperty(name = "品牌SN（品牌标签状态为1时有效）", example = "")
    private String brandSn;

    @ApiModelProperty(name = "档期品牌标签状态（默认：1：单品牌，2:多品牌）", example = "0")
    private Integer brandStatus;

    @ApiModelProperty(name = "档期上下线状态（默认0：下线，1：上线）", example = "0")
    private Integer onlineStatus;

    @ApiModelProperty(name = "档期预热开始时间", example = "0000-00-00 00:00:00")
    private Date preSaleStart;

    @ApiModelProperty(name = "档期预热结束时间", example = "0000-00-00 00:00:00")
    private Date preSaleEnd;

    @ApiModelProperty(name = "档期售卖开始时间", example = "0000-00-00 00:00:00")
    private Date sellStart;

    @ApiModelProperty(name = "档期售卖结束时间", example = "0000-00-00 00:00:00")
    private Date sellEnd;

    @ApiModelProperty(name = "档期排序号", example = "0")
    private Integer sort;

    @ApiModelProperty(name = "自定义标签数组", example = "")
    private String tagArray;

    @ApiModelProperty(name = "自定义属性key,value", example = "{}")
    private String extProps;

    @ApiModelProperty(name = "是否已删除(0:否,1:是)", example = "0")
    private Boolean isDeleted;

    @ApiModelProperty(name = "创建人", example = "0")
    private Integer createBy;

    @ApiModelProperty(name = "创建时间", example = "0000-00-00 00:00:00")
    private Date createTime;

    @ApiModelProperty(name = "修改人", example = "0")
    private Integer updateBy;

    @ApiModelProperty(name = "修改时间", example = "CURRENT_TIMESTAMP")
    private Date updateTime;


}
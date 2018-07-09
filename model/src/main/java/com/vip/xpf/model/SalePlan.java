package com.vip.xpf.model;

import java.util.Date;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @desc：档期表模型
 * @author romanluo
 * @date 2018/07/09
 */
@Data
@Table(name="sale_plan")
public class SalePlan implements Identity {

    /**
    *自增ID
    */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
    *档期SN
    */
    @Column(name = "`act_sn`")
    private String actSn;

    /**
    *档期名称
    */
    @Column(name = "`name`")
    private String name;

    /**
    *档期图片
    */
    @Column(name = "`logo`")
    private String logo;

    /**
    *档期描述
    */
    @Column(name = "`desc`")
    private String desc;

    /**
    *档期热卖标签状态 （默认：0:非热卖，1：热卖）
    */
    @Column(name = "`hot_status`")
    private Integer hotStatus;

    /**
    *品牌SN（品牌标签状态为1时有效）
    */
    @Column(name = "`brand_sn`")
    private String brandSn;

    /**
    *档期品牌标签状态（默认：1：单品牌，2:多品牌）
    */
    @Column(name = "`brand_status`")
    private Integer brandStatus;

    /**
    *档期上下线状态（默认0：下线，1：上线）
    */
    @Column(name = "`online_status`")
    private Integer onlineStatus;

    /**
    *档期预热开始时间
    */
    @Column(name = "`pre_sale_start`")
    private Date preSaleStart;

    /**
    *档期预热结束时间
    */
    @Column(name = "`pre_sale_end`")
    private Date preSaleEnd;

    /**
    *档期售卖开始时间
    */
    @Column(name = "`sell_start`")
    private Date sellStart;

    /**
    *档期售卖结束时间
    */
    @Column(name = "`sell_end`")
    private Date sellEnd;

    /**
    *档期排序号
    */
    @Column(name = "`sort`")
    private Integer sort;

    /**
    *自定义标签数组
    */
    @Column(name = "`tag_array`")
    private String tagArray;

    /**
    *自定义属性key,value
    */
    @Column(name = "`ext_props`")
    private String extProps;

    /**
    *是否已删除(0:否,1:是)
    */
    @Column(name = "`is_deleted`")
    private Boolean isDeleted;

    /**
    *创建人
    */
    @Column(name = "`create_by`")
    private Integer createBy;

    /**
    *创建时间
    */
    @Column(name = "`create_time`")
    private Date createTime;

    /**
    *修改人
    */
    @Column(name = "`update_by`")
    private Integer updateBy;

    /**
    *修改时间
    */
    @Column(name = "`update_time`")
    private Date updateTime;


}
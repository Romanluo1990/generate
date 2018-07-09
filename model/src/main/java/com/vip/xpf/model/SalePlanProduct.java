package com.vip.xpf.model;

import java.util.Date;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @desc：选品表模型
 * @author romanluo
 * @date 2018/07/09
 */
@Data
@Table(name="sale_plan_product")
public class SalePlanProduct implements Identity {

    /**
    *自增ID
    */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
    *档期ID
    */
    @Column(name = "`plan_id`")
    private Long planId;

    /**
    *spu_id
    */
    @Column(name = "`spu_id`")
    private String spuId;

    /**
    *最高价
    */
    @Column(name = "`max_price`")
    private Long maxPrice;

    /**
    *最低价
    */
    @Column(name = "`min_price`")
    private Long minPrice;

    /**
    *创建时间
    */
    @Column(name = "`create_time`")
    private Date createTime;


}
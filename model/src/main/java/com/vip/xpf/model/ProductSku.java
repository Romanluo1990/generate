package com.vip.xpf.model;

import java.util.Date;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @desc：商品sku模型
 * @author romanluo
 * @date 2018/07/09
 */
@Data
@Table(name="product_sku")
public class ProductSku implements Identity {

    /**
    *自增ID
    */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
    *产品ID
    */
    @Column(name = "`product_id`")
    private String productId;

    /**
    *产品规格属性
    */
    @Column(name = "`standard_props`")
    private String standardProps;

    /**
    *自定义属性值(json)
    */
    @Column(name = "`ext_props`")
    private String extProps;

    /**
    *权重
    */
    @Column(name = "`weight`")
    private Integer weight;

    /**
    *分销价
    */
    @Column(name = "`distribution_price`")
    private Long distributionPrice;

    /**
    *建议零售价
    */
    @Column(name = "`suggest_retail_price`")
    private Long suggestRetailPrice;

    /**
    *市场价
    */
    @Column(name = "`market_price`")
    private Long marketPrice;

    /**
    *长图json
    */
    @Column(name = "`long_images`")
    private String longImages;

    /**
    *方图json
    */
    @Column(name = "`square_images`")
    private String squareImages;

    /**
    *库存数量
    */
    @Column(name = "`amount`")
    private Integer amount;

    /**
    *默认 0，1 已删 除
    */
    @Column(name = "`is_deleted`")
    private Boolean isDeleted;

    /**
    *修改时间
    */
    @Column(name = "`update_time`")
    private Date updateTime;

    /**
    *创建时间
    */
    @Column(name = "`create_time`")
    private Date createTime;


}
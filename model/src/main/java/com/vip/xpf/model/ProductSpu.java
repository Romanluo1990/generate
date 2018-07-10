package com.vip.xpf.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @desc：商品表模型
 * @author romanluo
 * @date 2018/07/09
 */
@Data
@Table(name = "product_spu")
public class ProductSpu implements Identity {

	/**
	 *自增ID
	 */
	@Id
	@GeneratedValue(generator = "JDBC")
	private Long id;

	/**
	 *商品SN
	 */
	@Column(name = "`product_sn`")
	private String productSn;

	/**
	 *商品名称
	 */
	@Column(name = "`title`")
	private String title;

	/**
	 *短视频
	 */
	@Column(name = "`short_video`")
	private String shortVideo;

	/**
	 *长视频
	 */
	@Column(name = "`long_video`")
	private String longVideo;

	/**
	 *核心描述
	 */
	@Column(name = "`sub_title`")
	private String subTitle;

	/**
	 *商品分类ID
	 */
	@Column(name = "`category_id`")
	private Long categoryId;

	/**
	 *品牌ID
	 */
	@Column(name = "`brand_id`")
	private Long brandId;

	/**
	 *自定义属性(json：["name":"field1","value":"value1"])
	 */
	@Column(name = "`ext_props`")
	private String extProps;

	/**
	 *商品备注
	 */
	@Column(name = "`remark`")
	private String remark;

	/**
	 *（默认0：未删除，1：已删除）
	 */
	@Column(name = "`is_deleted`")
	private Boolean isDeleted;

	/**
	 *创建时间
	 */
	@Column(name = "`create_time`")
	private Date createTime;

	/**
	 *修改时间
	 */
	@Column(name = "`update_time`")
	private Date updateTime;


}
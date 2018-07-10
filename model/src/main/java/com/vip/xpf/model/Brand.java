package com.vip.xpf.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @desc：品牌表模型
 * @author romanluo
 * @date 2018/07/09
 */
@Data
@Table(name = "brand")
public class Brand implements Identity {

	/**
	 * 自增ID
	 */
	@Id
	@GeneratedValue(generator = "JDBC")
	private Long id;

	/**
	 *品牌编码
	 */
	@Column(name = "`brand_sn`")
	private String brandSn;

	/**
	 *品牌中文名称
	 */
	@Column(name = "`brand_cn_name`")
	private String brandCnName;

	/**
	 *品牌英文名称
	 */
	@Column(name = "`brand_en_name`")
	private String brandEnName;

	/**
	 *品牌logo
	 */
	@Column(name = "`brand_logo`")
	private String brandLogo;

	/**
	 *品牌描述
	 */
	@Column(name = "`description`")
	private String description;

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
	 *更新时间
	 */
	@Column(name = "`update_time`")
	private Date updateTime;


}
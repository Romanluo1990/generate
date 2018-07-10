package com.vip.xpf.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @desc：品类表模型
 * @author romanluo
 * @date 2018/07/09
 */
@Data
@Table(name = "category")
public class Category implements Identity {

	/**
	 *自增ID
	 */
	@Id
	@GeneratedValue(generator = "JDBC")
	private Long id;

	/**
	 *品类父ID(一级品类：-1)
	 */
	@Column(name = "`parent_id`")
	private Long parentId;

	/**
	 *品类编码（10|10|10）
	 */
	@Column(name = "`cate_sn`")
	private String cateSn;

	/**
	 *品类名称
	 */
	@Column(name = "`cate_name`")
	private String cateName;

	/**
	 *品类层级（1：一级品类，2：二级品类 3：三级品类）
	 */
	@Column(name = "`cate_level`")
	private Integer cateLevel;

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
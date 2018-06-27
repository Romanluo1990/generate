package com.vip.xpf.model;

import java.util.Date;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @desc：用户模型
 * @author romanluo
 * @date 2018/06/25
 */
@Data
@Table(name="t_user_info")
public class TUserInfo implements Identity {

    /**
    *主键
    */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
    *
    */
    @Column(name = "`mobile`")
    private String mobile;

    /**
    *
    */
    @Column(name = "`password`")
    private String password;

    /**
    *创建时间
    */
    @Column(name = "`create_time`")
    private Date createTime;


}
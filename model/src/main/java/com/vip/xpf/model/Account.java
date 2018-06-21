package com.vip.xpf.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Data
public class Account implements Identity{

    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    @Column(name = "open_id")
    private String openId;

    @Column(name = "nick_name")
    private String nickName;

    private String image;

    @Column(name = "phone")
    private String phone;

    @Column(name = "type")
    private Integer type;

    /**
     * 状态（0:待审核 1:审核通过 2:审核不通过）
     */
    private Integer status;

    /**
     * 审核信息
     */
    private String statusMessage;

    @Column(name = "user_name")
    private String userName;

    private String password;

    private String salt;

    @Column(name = "create_time")
    private Date createTime;

    private String creator;

    @Column(name = "update_time")
    private Date updateTime;

    private String updator;

}
package com.vip.xpf.controller.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vip.xpf.model.Account;
import io.swagger.annotations.ApiModelProperty;

public class AccountVo {

    @JsonIgnore
    private Account account = new Account();

    public AccountVo(Account account) {
        this.account = account;
    }


    public Long getId() {
        return account.getId();
    }

    public String getOpenId() {
        return account.getOpenId();
    }

    @ApiModelProperty(name="昵称",example = "张三")
    public String getNickName() {
        return account.getNickName();
    }

    public String getImage() {
        return account.getImage();
    }

    public String getPhone() {
        return account.getPhone();
    }

    public Integer getType() {
        return account.getType();
    }

    public Integer getStatus() {
        return account.getStatus();
    }

    public String getStatusMessage() {
        return account.getStatusMessage();
    }

    public String getUserName() {
        return account.getUserName();
    }
}

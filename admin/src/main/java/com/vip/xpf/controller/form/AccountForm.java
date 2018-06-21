package com.vip.xpf.controller.form;

import com.vip.xpf.model.Account;
import io.swagger.annotations.ApiModelProperty;

public class AccountForm {

    @ApiModelProperty(hidden = true)
    private Account account = new Account();

    public void setId(Long id) {
        account.setId(id);
    }

    public void setOpenId(String openId) {
        account.setOpenId(openId);
    }

    @ApiModelProperty(name="昵称",example = "张三")
    public void setNickName(String nickName) {
        account.setNickName(nickName);
    }

    public void setImage(String image) {
        account.setImage(image);
    }

    public void setPhone(String phone) {
        account.setPhone(phone);
    }

    public void setType(Integer type) {
        account.setType(type);
    }

    public void setStatus(Integer status) {
        account.setStatus(status);
    }

    public void setStatusMessage(String statusMessage) {
        account.setStatusMessage(statusMessage);
    }

    public void setUserName(String userName) {
        account.setUserName(userName);
    }

    public void setPassword(String password) {
        account.setPassword(password);
    }

    public Account getAccount() {
        return account;
    }
}

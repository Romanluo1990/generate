package com.vip.xpf.dao;

import com.github.pagehelper.PageInfo;
import com.vip.xpf.dao.common.PageSelect;
import com.vip.xpf.dao.common.SelectCondition;
import com.vip.xpf.model.Account;

import java.util.List;

public interface AccountDao extends PageDao<Account>{

    List<Account> listByName(String name);
}

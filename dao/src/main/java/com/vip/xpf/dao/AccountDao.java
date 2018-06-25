package com.vip.xpf.dao;

import com.vip.xpf.model.Account;

import java.util.List;

public interface AccountDao extends PageDao<Account> {

	List<Account> listByName(String name);
}

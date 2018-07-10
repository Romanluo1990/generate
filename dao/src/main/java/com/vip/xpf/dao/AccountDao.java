package com.vip.xpf.dao;

import com.vip.xpf.model.Account;

import java.util.List;

public interface AccountDao extends PageDao<Account> {

	List<Account> listByName(String name);

	List<Account> listById(long id, int size);

	List<Account> listByIds(List<Long> ids);
}

package com.vip.xpf.dao.impl;

import com.vip.xpf.dao.AccountDao;
import com.vip.xpf.dao.mapper.AccountMapper;
import com.vip.xpf.model.Account;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Repository
public class AccountDaoImpl extends PageDaoImpl<AccountMapper, Account> implements AccountDao {

	@Override
	public List<Account> listByName(String name) {
		Example example = new Example(Account.class);
		example.createCriteria().andLike("nickName", name);
		return mapper.selectByExample(example);
	}

}

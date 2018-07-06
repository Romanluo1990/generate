package com.vip.xpf.service;

import com.github.pagehelper.PageInfo;
import com.vip.xpf.dao.AccountDao;
import com.vip.xpf.dao.common.sql.PageSelect;
import com.vip.xpf.dao.common.sql.SelectCondition;
import com.vip.xpf.model.Account;
import com.vip.xpf.search.searcher.AccountSearcher;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AccountService extends BaseService<AccountDao> {

	@Resource
	private AccountSearcher accountSearcher;

	public PageInfo<Account> page(PageSelect pageSelect) {
		return dao.page(pageSelect);
	}

	public PageInfo<Account> pageByConditions(PageSelect pageSelect, List<SelectCondition> conditions) {
		return dao.pageByConditions(pageSelect, conditions);
	}

	public PageInfo<Account> searche(PageSelect pageSelect, List<SelectCondition> conditions) {
		return accountSearcher.search(pageSelect, conditions);
	}
}

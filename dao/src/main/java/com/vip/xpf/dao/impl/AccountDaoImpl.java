package com.vip.xpf.dao.impl;

import com.github.pagehelper.PageHelper;
import com.vip.xpf.dao.AccountDao;
import com.vip.xpf.dao.common.sql.ModifyEventType;
import com.vip.xpf.dao.impl.event.ModifyAccountEvent;
import com.vip.xpf.dao.mapper.AccountMapper;
import com.vip.xpf.model.Account;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class AccountDaoImpl extends PageDaoImpl<AccountMapper, Account> implements AccountDao {

	@Resource
	private ApplicationContext applicationContext;

	@Override
	public List<Account> listByName(String name) {
		Example example = new Example(Account.class);
		example.createCriteria().andLike("nickName", name);
		return mapper.selectByExample(example);
	}

	@Override
	public List<Account> listById(long id, int size) {
		Example example = new Example(Account.class);
		example.createCriteria().andGreaterThan("id", id);
		PageHelper.offsetPage(0, size, false);
		return mapper.selectByExample(example);
	}

	@Cacheable(value = "Account", key = "'id_'+#id")
	@Override
	public Account getById(long id) {
		return super.getById(id);
	}

	@Override
	public boolean save(Account account) {
		boolean success = super.save(account);
		if (success) {
			applicationContext
					.publishEvent(new ModifyAccountEvent(this, getById(account.getId()), ModifyEventType.ADD));
		}
		return success;
	}

	@Override
	public boolean deleteById(long id) {
		boolean success = super.deleteById(id);
		if (success) {
			applicationContext.publishEvent(new ModifyAccountEvent(this, getById(id), ModifyEventType.DELETE));
		}
		return success;
	}

	@Override
	public boolean updateById(Account account) {
		boolean success = super.updateById(account);
		if (success) {
			applicationContext
					.publishEvent(new ModifyAccountEvent(this, getById(account.getId()), ModifyEventType.UPDATE));
		}
		return success;
	}

	@Override
	public boolean updateByIdSelective(Account account) {
		boolean success = super.updateByIdSelective(account);
		if (success) {
			applicationContext
					.publishEvent(new ModifyAccountEvent(this, getById(account.getId()), ModifyEventType.UPDATE));
		}
		return success;
	}
}

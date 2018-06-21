package com.vip.xpf.service;

import com.github.pagehelper.PageInfo;
import com.vip.xpf.dao.AccountDao;
import com.vip.xpf.dao.common.PageSelect;
import com.vip.xpf.dao.common.SelectCondition;
import org.springframework.stereotype.Service;
import com.vip.xpf.model.Account;

import java.util.List;

@Service
public class AccountService extends BaseService<AccountDao>{

    public PageInfo<Account> page(PageSelect pageSelect) {
        return dao.page(pageSelect);
    }

    public PageInfo<Account> pageByConditions(PageSelect pageSelect, List<SelectCondition> conditions) {
        return dao.pageByConditions(pageSelect,conditions);
    }
}

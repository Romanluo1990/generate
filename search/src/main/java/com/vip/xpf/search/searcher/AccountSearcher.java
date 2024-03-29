package com.vip.xpf.search.searcher;

import com.vip.xpf.common.util.bean.BeanUtils;
import com.vip.xpf.dao.AccountDao;
import com.vip.xpf.dao.impl.event.ModifyAccountEvent;
import com.vip.xpf.model.Account;
import com.vip.xpf.search.indexmodel.AccountIndex;
import com.vip.xpf.search.repository.AccountSearchRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 *
 * @Auther: romanluo
 * @Date: 2018/6/28
 * @Description:
 */
@Repository
public class AccountSearcher extends AbstractSearcher<AccountSearchRepository, AccountIndex, AccountDao, Account>
		implements ApplicationListener<ModifyAccountEvent> {

	@Resource
	private AccountDao accountDao;

	@Override
	protected AccountIndex toIndexData(Account sourceModel) {
		return BeanUtils.map(sourceModel, AccountIndex.class);
	}

	@Override
	public void onApplicationEvent(ModifyAccountEvent event) {
		switch (event.getModifyEventType()) {
			case DELETE:
				deleteById(event.getEntity().getId());
				break;
			case ADD:
			case UPDATE:
			default:
				save(event.getEntity());
				break;
		}
	}
}

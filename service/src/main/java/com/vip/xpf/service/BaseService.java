package com.vip.xpf.service;

import com.vip.xpf.dao.BaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import com.vip.xpf.model.Identity;

public class BaseService<D extends BaseDao> {

    @Autowired
    protected D dao;

    public <E extends Identity> boolean save(E e) {
        return dao.save(e);
    }

    public boolean deleteById(long id) {
        return dao.deleteById(id);
    }

    public <E extends Identity> boolean updateById(E e) {
        return dao.updateById(e);
    }

    public <E extends Identity> boolean updateByIdSelective(E e) {
        return dao.updateByIdSelective(e);
    }

    public <E extends Identity> boolean saveOrUpdate(E e) {
        return dao.saveOrUpdate(e);
    }

    public <E extends Identity> E getById(long id) {
        return (E) dao.getById(id);
    }
}

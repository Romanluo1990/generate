package com.vip.xpf.dao;

import com.vip.xpf.dao.common.Dao;
import com.vip.xpf.model.Identity;

public interface BaseDao<E extends Identity> extends Dao {

    /**
     * 保存
     * @param e
     * @return
     */
    boolean save(E e);

    /**
     * 按id删除
     * @param id
     * @return
     */
    boolean deleteById(long id);

    /**
     * 按id更新全字段
     * @param e
     * @return
     */
    boolean updateById(E e);

    /**
     * 按id更新非空字段
     * @param e
     * @return
     */
    boolean updateByIdSelective(E e);

    /**
     * 保存或更新，按id判断
     * @param e
     * @return
     */
    boolean saveOrUpdate(E e);

    /**
     * 按id查数据
     * @param id
     * @return
     */
    E getById(long id);

    long count();

}

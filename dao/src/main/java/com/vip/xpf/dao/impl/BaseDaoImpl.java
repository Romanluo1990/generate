package com.vip.xpf.dao.impl;

import com.github.pagehelper.PageHelper;
import com.vip.xpf.dao.BaseDao;
import com.vip.xpf.dao.common.sql.ConditionsQueryMapper;
import com.vip.xpf.model.Identity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Slf4j
public class BaseDaoImpl<M extends ConditionsQueryMapper<E>, E extends Identity> implements BaseDao<E> {

	private Class classCache;

	@Autowired
	protected M mapper;

	@Override
	public boolean save(E e) {
		e.setId(null);
		e.setCreateTime(new Date());
		return mapper.insertSelective(e) == 1;
	}

	@Override
	public boolean deleteById(long id) {
		E e = createEntity();
		e.setId(id);
		e.setIsDeleted(false);
		return mapper.updateByPrimaryKeySelective(e) == 1;
	}

	@Override
	public boolean updateById(E e) {
		if (e.getId() == null) {
			throw new IllegalArgumentException("updateById method id cannot be null");
		}
		return mapper.updateByPrimaryKey(e) == 1;
	}

	@Override
	public boolean updateByIdSelective(E e) {
		if (e.getId() == null) {
			throw new IllegalArgumentException("updateById method id cannot be null");
		}
		return mapper.updateByPrimaryKeySelective(e) == 1;
	}

	@Override
	public E getById(long id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public List<E> listByIds(List<Long> ids) {
		if (ids == null || ids.isEmpty()) {
			return Collections.emptyList();
		}
		Example example = new Example(getEntityClass());
		example.createCriteria().andIn("id", ids);
		return mapper.selectByExample(example);
	}

	@Override
	public List<E> listById(long fromId, int limit) {
		Example example = new Example(getEntityClass());
		if (fromId > 0) {
			example.createCriteria().andLessThan("id", fromId);
		}
		PageHelper.offsetPage(0, limit, false);
		PageHelper.orderBy("id desc");
		return mapper.selectByExample(example);
	}

	@Override
	public long count() {
		return mapper.selectCount(createEntity());
	}

	@Override
	public boolean saveOrUpdate(E e) {
		if (e.getId() == null) {
			return save(e);
		} else {
			return updateByIdSelective(e);
		}
	}

	protected E createEntity() {
		Class clazz = getEntityClass();
		E e;
		try {
			e = (E) (clazz.getConstructor().newInstance());
		} catch (Exception ex) {
			log.error("没有无参构造", ex);
			throw new IllegalStateException(ex);
		}
		return e;
	}

	protected Class<E> getEntityClass() {
		if (classCache == null) {
			ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
			classCache = (Class) parameterizedType.getActualTypeArguments()[1];
		}
		return classCache;
	}

}

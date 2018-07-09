package com.vip.xpf.dao.impl;

import com.vip.xpf.dao.CategoryDao;
import com.vip.xpf.dao.mapper.CategoryMapper;
import com.vip.xpf.model.Category;
import org.springframework.stereotype.Repository;

/**
 * @desc：品类表Dao
 * @author romanluo
 * @date 2018/07/09
 */
@Repository
public class CategoryDaoImpl extends BaseDaoImpl<CategoryMapper, Category> implements CategoryDao {

}
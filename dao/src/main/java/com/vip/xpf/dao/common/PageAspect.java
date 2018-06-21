package com.vip.xpf.dao.common;

import com.github.pagehelper.PageHelper;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 分页设置
 */
@Aspect
@Component
public class PageAspect {

    @Before("this(com.vip.xpf.dao.Dao) && args(pageSelect,..)")
    public void setPage(PageSelect pageSelect){
        PageHelper.orderBy(pageSelect.getOrderByString());
        Boolean isCount = pageSelect.getCount();
        if(Objects.isNull(isCount))
            PageHelper.startPage(pageSelect.getPageNo(), pageSelect.getPageSize());
        else
            PageHelper.startPage(pageSelect.getPageNo(), pageSelect.getPageSize(),isCount);
    }

}

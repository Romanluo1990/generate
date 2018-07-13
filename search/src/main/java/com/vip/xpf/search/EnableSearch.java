package com.vip.xpf.search;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 *
 * @Auther: romanluo
 * @Date: 2018/7/12
 * @Description:
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(SearchConfig.class)
public @interface EnableSearch {
}

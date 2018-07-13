package com.vip.xpf.search;

import java.lang.annotation.*;

/**
 *
 * @Auther: romanluo
 * @Date: 2018/7/12
 * @Description:
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Searcher {

	Class searcher();
}

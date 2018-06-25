package com.vip.xpf.common.util;

import com.github.pagehelper.PageInfo;
import com.vip.xpf.common.util.bean.BeanUtils;

import java.util.function.Function;
import java.util.stream.Collectors;

public class PageUtils {

	private PageUtils() {
	}

	public static <S, D> PageInfo<D> convert(PageInfo<S> orig, Function<S, D> function) {
		PageInfo<D> dest = new PageInfo<>();
		dest.setPageNum(orig.getPageNum());
		dest.setPageSize(orig.getPageSize());
		dest.setSize(orig.getSize());
		dest.setOrderBy(orig.getOrderBy());
		dest.setStartRow(orig.getStartRow());
		dest.setEndRow(orig.getEndRow());
		dest.setTotal(orig.getTotal());
		dest.setPages(orig.getPages());
		dest.setFirstPage(orig.getFirstPage());
		dest.setPrePage(orig.getFirstPage());
		dest.setNextPage(orig.getNextPage());
		dest.setLastPage(orig.getLastPage());
		dest.setHasPreviousPage(orig.isHasPreviousPage());
		dest.setHasNextPage(orig.isHasNextPage());
		dest.setNavigatePages(orig.getNavigatePages());
		dest.setNavigatepageNums(orig.getNavigatepageNums());
		dest.setList(orig.getList().parallelStream().map(function).collect(Collectors.toList()));
		return dest;
	}

	public static <S, D> PageInfo<D> convert(PageInfo<S> orig, Class<D> destClazz) {
		return convert(orig, data -> BeanUtils.map(data, destClazz));
	}
}

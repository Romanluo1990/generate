package com.vip.utils.generate;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

/**
 *
 * @Auther: romanluo
 * @Date: 2018/12/18
 */
public class BeanUtils {

	private static final MapperFacade mapperFacade;

	static {
		MapperFactory mapperFactory = new DefaultMapperFactory.Builder()
				.build();
		mapperFacade = mapperFactory.getMapperFacade();
	}

	public static <S, D> D map(S sourceObject, Class<D> destinationClass) {
		return mapperFacade.map(sourceObject, destinationClass);
	}

	public static <S, D> void map(S sourceObject, D destinationObject) {
		mapperFacade.map(sourceObject, destinationObject);
	}
}

package com.vip.xpf.common.util.bean.orika.annotation;

import com.vip.xpf.common.util.bean.orika.VoidConverter;
import ma.glasnost.orika.Converter;

import java.lang.annotation.*;

/** FieldMap注解 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface FieldMap {

  Class<?> origClass();

  String origField() default "";

  Class<? extends Converter<?, ?>> converter() default VoidConverter.class;

  boolean mapNulls() default true;

  boolean exclude() default false;

  boolean byDefault() default false;
}

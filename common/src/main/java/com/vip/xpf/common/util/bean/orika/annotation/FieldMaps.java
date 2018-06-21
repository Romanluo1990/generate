package com.vip.xpf.common.util.bean.orika.annotation;

import java.lang.annotation.*;

/** 多FieldMap */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface FieldMaps {

  FieldMap[] value() default {};
}

package com.marvinswolrd.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Description:.
 *
 * @author zhiqiang.ge
 * @version V1.0
 * @since 2015/8/23 14:08
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface DConfig {
    String value();
}

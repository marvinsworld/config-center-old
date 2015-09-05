package com.marvinswolrd.dconfig.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Description:配置注解.
 *
 * @author marvinsworld
 * @version V1.0
 * @since 2015/8/23 14:08
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.METHOD, java.lang.annotation.ElementType.FIELD})
public @interface DConfig {
    String value();
}

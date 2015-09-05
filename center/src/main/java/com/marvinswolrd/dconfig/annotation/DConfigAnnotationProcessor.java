package com.marvinswolrd.dconfig.annotation;

import com.google.common.base.Strings;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Description:.
 *
 * @author zhiqiang.ge
 * @version V1.0
 * @since 2015/8/23 15:57
 */
public class DConfigAnnotationProcessor implements BeanPostProcessor {

    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        parseMethods(bean, bean.getClass().getDeclaredMethods());
        parseFields(bean, bean.getClass().getDeclaredFields());
        return bean;
    }

    /**
     * 解析方法
     */
    private void parseMethods(Object bean, Method[] methods) {

    }

    /**
     * 解析字段
     */
    private void parseFields(Object bean, Field[] fields) {
        for (Field field : fields) {
            DConfig annotation = (DConfig) AnnotationUtils.getAnnotation(field, DConfig.class);
            if (annotation != null) {
                String value = annotation.value();
                if (Strings.isNullOrEmpty(value)) {
                    throw new RuntimeException("DConfig must have a value!");
                }


            }
        }
    }


    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}

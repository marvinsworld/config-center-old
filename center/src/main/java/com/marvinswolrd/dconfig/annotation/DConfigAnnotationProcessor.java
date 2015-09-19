package com.marvinswolrd.dconfig.annotation;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ReflectionUtils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * Description:.
 *
 * @author zhiqiang.ge
 * @version V1.0
 * @since 2015/8/23 15:57
 */
public class DConfigAnnotationProcessor extends AutowiredAnnotationBeanPostProcessor// implements BeanPostProcessor
{


    private final String[] files;
    private long timeout;
    private boolean ignoreResourceNotFound;
    private Properties p;

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    public void setIgnoreResourceNotFound(boolean ignoreResourceNotFound) {
        this.ignoreResourceNotFound = ignoreResourceNotFound;
    }

    public String[] getFiles() {
        return files;
    }

    public long getTimeout() {
        return timeout;
    }

    public boolean isIgnoreResourceNotFound() {
        return ignoreResourceNotFound;
    }

    public DConfigAnnotationProcessor(String... files) {
        Preconditions.checkArgument((files != null) && (files.length > 0), "DConfig files property must be not null!");
        this.files = files;

        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("test.properties");
        p = new Properties();
        try{
            p.load(inputStream);
        } catch (IOException e1){
            e1.printStackTrace();
        }
        System.out.println(p.getProperty("aaa"));
    }


    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        //super.postProcessBeforeInitialization(bean,beanName);
        parseMethods(bean, bean.getClass().getDeclaredMethods());
        parseFields(bean, bean.getClass().getDeclaredFields());

        super.processInjection(bean);
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
                }else{

                    try {
                        ReflectionUtils.makeAccessible(field);
                        field.set(bean, p.getProperty(value));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }


            }
        }
    }


    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}

package com.marvinswolrd.dconfig.annotation;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ReflectionUtils;

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
public class DConfigAnnotationProcessor extends ApplicationObjectSupport implements BeanPostProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(DConfigAnnotationProcessor.class);

    private String[] files;
    private String namespace;
    private long timeout;
    private boolean ignoreResourceNotFound;
    private Properties properties;

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

    public DConfigAnnotationProcessor(String[] files, String namespace) {
        Preconditions.checkArgument((files != null) && (files.length > 0), "DConfig files property must be not null!");
        this.files = files;
        this.namespace = namespace;

        properties = new Properties();
        for (String file : files) {
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(file);
            LOGGER.debug("DConfig loading file {}...", file);
            try {
                properties.load(inputStream);
            } catch (Exception e) {
                LOGGER.error("DConfig loading file error,please check the path of file {}!", file, e);
            }
        }
    }

    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        //super.postProcessBeforeInitialization(bean,beanName);
        parseMethods(bean, bean.getClass().getDeclaredMethods());
        parseFields(bean, bean.getClass().getDeclaredFields());
        //super.processInjection(bean);

//                NodeListener nodeListener = (NodeListener) SpringContextUtil.getBean("NodeListener");
//        System.out.println("----------"+nodeListener);
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
        System.out.println(getApplicationContext());
        System.out.println("111");
        //RegisterCenter configCenter = new RegisterCenter();

//        CuratorFramework client = configCenter.createClient();
//        client.start();

        for (Field field : fields) {
            DConfig annotation = (DConfig) AnnotationUtils.getAnnotation(field, DConfig.class);
            if (annotation != null) {
                String key = annotation.value();

//                try {
//                    String zkValue = new String(client.getData().forPath("/" + key));
//                    System.out.println(zkValue);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }

                if (Strings.isNullOrEmpty(key)) {
                    LOGGER.error("DConfig annotation key must have a name!");
                    //throw new RuntimeException("DConfig must have a value!");
                } else {
                    ReflectionUtils.makeAccessible(field);
                    String value = properties.getProperty(key);
                    try {
                        if (!Strings.isNullOrEmpty(value)) {
                            field.set(bean, value);
                        }
                    } catch (Exception e) {
                        LOGGER.error("DConfig parse property key error,please check the key {}!", key, e);
                    }
                }
            }
        }
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    private ApplicationContext context;

}

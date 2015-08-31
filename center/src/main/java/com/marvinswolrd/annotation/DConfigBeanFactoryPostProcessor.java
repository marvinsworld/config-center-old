package com.marvinswolrd.annotation;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Description:
 *
 * @author Marvinsworld
 * @since 2015/8/31 23:00
 */
public class DConfigBeanFactoryPostProcessor implements BeanFactoryPostProcessor, BeanPostProcessor {

    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        //beanFactory.get
    }

    public Object postProcessBeforeInitialization(Object bean, String beanName)
            throws BeansException {

        Method[] methods = bean.getClass().getMethods();
        for (Method method : methods) {
            String name = method.getName();
            if (name.length() > 3 && name.startsWith("set")
                    && method.getParameterTypes().length == 1
                    && Modifier.isPublic(method.getModifiers())
                    && ! Modifier.isStatic(method.getModifiers())) {
                try {
                    DConfig reference = method.getAnnotation(DConfig.class);
                    if (reference != null) {
                        Object value = "11111";
                        if (value != null) {
                            method.invoke(bean, new Object[] {  });
                        }
                    }
                } catch (Throwable e) {
                    //logger.error("Failed to init remote service reference at method " + name + " in class " + bean.getClass().getName() + ", cause: " + e.getMessage(), e);
                }
            }
        }
        Field[] fields = bean.getClass().getDeclaredFields();
        for (Field field : fields) {
            try {
                if (! field.isAccessible()) {
                    field.setAccessible(true);
                }
                DConfig reference = field.getAnnotation(DConfig.class);
                if (reference != null) {
                    Object value = "11111";
                    if (value != null) {
                        field.set(bean, value);
                    }
                }
            } catch (Throwable e) {
                //logger.error("Failed to init remote service reference at filed " + field.getName() + " in class " + bean.getClass().getName() + ", cause: " + e.getMessage(), e);
            }
        }
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }


}

package com.marvinswolrd.demo;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class AnnotationBeanPostProcessor extends PropertyPlaceholderConfigurer implements BeanPostProcessor, InitializingBean {
     private java.util.Properties pros;   

    public Object postProcessAfterInitialization(Object bean, String beanName)
            throws BeansException {
        // TODO Auto-generated method stub
        return bean;
    }

    public Object postProcessBeforeInitialization(Object bean, String beanName)
            throws BeansException {




        //if(bean.getClass().getAnnotation(Config.class)!=null){
            Field[] fields = bean.getClass().getDeclaredFields();

            for (Field field : fields) {
                Config p = field.getAnnotation(Config.class);
                if (p != null) {
                    Object value = pros.getProperty(p.value());

                    try {
                        ReflectionUtils.makeAccessible(field);
                        field.set(bean, value);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    //System.out.println("111"+para);
                }
            }
        //}



        if(bean.getClass().getAnnotation(Config.class)!=null){
            Method[] methods = bean.getClass().getDeclaredMethods();
            for (Method method : methods) {
                Config p = method.getAnnotation(Config.class);
                if(p!=null){
//                  这里进行参数类型转换
                    Object para=pros.getProperty(p.value());
                    if((method.getParameterTypes()[0]).getName().equals("java.lang.Integer")){
                        para= new Integer(para.toString());
                    }
                    ReflectionUtils.invokeMethod(method, bean, new Object[]{para});
                }
            }   
        }
        return bean;
    }

    public void afterPropertiesSet() throws Exception {
        pros = mergeProperties();   
        
    }
}
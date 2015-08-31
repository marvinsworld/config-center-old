package com.marvinswolrd.annotation;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;

public class MyFooishHandler implements ApplicationContextAware, InitializingBean {
  private ApplicationContext applicationContext;
 

  public void afterPropertiesSet() throws Exception {
 
    final Map<String, Object> myFoos = applicationContext.getBeansWithAnnotation(DConfig.class);
 
    for (final Object myFoo : myFoos.values()) {
      final Class<? extends Object> fooClass = myFoo.getClass();
      final DConfig annotation = fooClass.getAnnotation(DConfig.class);
      System.out.println("Found foo class: " + fooClass + ", with tags: " + annotation.value());
    }
  }

  public void setApplicationContext(final ApplicationContext applicationContext)
      throws BeansException {
    this.applicationContext = applicationContext;
  }
}
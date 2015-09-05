//package com.marvinswolrd.annotation.qunar;
//
//import com.google.common.base.Strings;
//import java.lang.reflect.Field;
//import java.lang.reflect.Method;
//import java.util.Map;
//import java.util.Map.Entry;
//import java.util.Properties;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.config.BeanPostProcessor;
//import org.springframework.core.annotation.AnnotationUtils;
//import qunar.tc.qconfig.client.Configuration.ConfigListener;
//import qunar.tc.qconfig.client.Feature;
//import qunar.tc.qconfig.client.MapConfig;
//import qunar.tc.qconfig.client.TypedConfig;
//
//class QConfigAnnotationProcessor
//  implements BeanPostProcessor
//{
//  private static final Logger logger = LoggerFactory.getLogger(QConfigAnnotationProcessor.class);
//
//  public Object postProcessBeforeInitialization(Object bean, String beanName)
//    throws BeansException
//  {
//    parseMethods(bean, bean.getClass().getDeclaredMethods());
//    parseFields(bean, bean.getClass().getDeclaredFields());
//    return bean;
//  }
//
//  public Object postProcessAfterInitialization(Object bean, String beanName)
//    throws BeansException
//  {
//    return bean;
//  }
//
//  private void parseMethods(final Object bean, Method[] methods)
//  {
//    for (final Method method : methods)
//    {
//      QConfig annotation = (QConfig)AnnotationUtils.findAnnotation(method, QConfig.class);
//      if (annotation != null)
//      {
//        String file = annotation.value();
//        if (Strings.isNullOrEmpty(file)) {
//          throw new RuntimeException("������������������");
//        }
//        Class<?>[] parameterTypes = method.getParameterTypes();
//        if (parameterTypes.length != 1) {
//          throw new RuntimeException("��������������������������������, method: " + method);
//        }
//        Map.Entry<String, Feature> entry = Util.parse(file);
//        Class<?> parameterType = parameterTypes[0];
//        if (parameterType.equals(Map.class))
//        {
//          MapConfig config = MapConfig.get((String)entry.getKey(), (Feature)entry.getValue());
//          config.addListener(new Configuration.ConfigListener()
//          {
//            public void onLoad(Map<String, String> conf)
//            {
//              QConfigAnnotationProcessor.this.invoke(method, bean, conf);
//            }
//          });
//        }
//        else if (parameterType.equals(String.class))
//        {
//          TypedConfig<String> config = TypedConfig.get((String)entry.getKey(), (Feature)entry.getValue(), TypedConfig.STRING_PARSER);
//          config.addListener(new Configuration.ConfigListener()
//          {
//            public void onLoad(String conf)
//            {
//              QConfigAnnotationProcessor.this.invoke(method, bean, conf);
//            }
//          });
//        }
//        else
//        {
//          throw new RuntimeException("����������������Map, String");
//        }
//      }
//    }
//  }
//
//  private void parseFields(final Object bean, Field[] fields)
//  {
//    for (final Field field : fields)
//    {
//      QConfig annotation = (QConfig)AnnotationUtils.getAnnotation(field, QConfig.class);
//      if (annotation != null)
//      {
//        String file = annotation.value();
//        if (Strings.isNullOrEmpty(file)) {
//          throw new RuntimeException("��������������");
//        }
//        Map.Entry<String, Feature> entry = Util.parse(file);
//        Class<?> fieldType = field.getType();
//        if (fieldType.equals(Properties.class))
//        {
//          MapConfig config = MapConfig.get((String)entry.getKey(), (Feature)entry.getValue());
//          setField(field, bean, config.asProperties());
//        }
//        else if (fieldType.equals(Map.class))
//        {
//          MapConfig config = MapConfig.get((String)entry.getKey(), (Feature)entry.getValue());
//          setField(field, bean, config.asMap());
//        }
//        else if (fieldType.equals(String.class))
//        {
//          TypedConfig<String> config = TypedConfig.get((String)entry.getKey(), (Feature)entry.getValue(), TypedConfig.STRING_PARSER);
//          config.addListener(new Configuration.ConfigListener()
//          {
//            public void onLoad(String conf)
//            {
//              QConfigAnnotationProcessor.this.setField(field, bean, conf);
//            }
//          });
//        }
//        else
//        {
//          throw new RuntimeException("������Map, Properties, String��������������");
//        }
//      }
//    }
//  }
//
//  private void invoke(Method method, Object bean, Object param)
//  {
//    try
//    {
//      method.setAccessible(true);
//      method.invoke(bean, new Object[] { param });
//    }
//    catch (Exception e)
//    {
//      logger.error("��������������������������", e);
//    }
//  }
//
//  private void setField(Field field, Object bean, Object param)
//  {
//    try
//    {
//      field.setAccessible(true);
//      field.set(bean, param);
//    }
//    catch (IllegalAccessException e)
//    {
//      logger.error("����������������", e);
//    }
//  }
//}

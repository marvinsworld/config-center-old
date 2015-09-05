package com.marvinswolrd.annotation;

import com.marvinswolrd.dconfig.annotation.DConfig;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Properties;

/**
 * Description:
 *
 * @author Marvinsworld
 * @since 2015/8/31 23:00
 */
public class DConfigBeanFactoryPostProcessor extends PropertyPlaceholderConfigurer
        implements BeanPostProcessor {


    //private java.util.Properties mergedProps;


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

                    //processProperties(this.beanFactory, Properties props);
                    String aaa = props.getProperty(reference.value());
                    System.out.println("----------------------"+aaa);
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


//    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
//        try {
//            mergedProps = mergeProperties();
//
//            // Convert the merged properties, if necessary.
//            convertProperties(mergedProps);
//
//            // Let the subclass process the properties.
//            processProperties(beanFactory, mergedProps);
//        }
//        catch (IOException ex) {
//            throw new BeanInitializationException("Could not load properties", ex);
//        }
//    }


    private Properties props;

    @Override
    protected void processProperties(
            ConfigurableListableBeanFactory beanFactoryToProcess,
            Properties props) throws BeansException {
        super.processProperties(beanFactoryToProcess, props);
        this.props=props;
    }

    public Object getProperty(String key) {
        return props.getProperty(key);
    }


}

package com.marvinswolrd.annotation;

import org.springframework.stereotype.Service;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Description:.
 *
 * @author zhiqiang.ge
 * @version V1.0
 * @since 2015/8/23 14:10
 */
@Service
public class TestCase {

    @DConfig(value = "aaa")
    private String db;

    //@Value("${bbb}")
//    private String db2;

    public void setDb(String db) {
        this.db = db;
    }

    public String getDb() {
        return db;
    }

//    public String getDb2() {
//        return db2;
//    }
//
//    public void setDb2(String db2) {
//        this.db2 = db2;
//    }

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, IntrospectionException {
        TestCase use = new TestCase();
        //Default注解的处理过程
        //这里使用反射机制完成默认值的设置
        Field[] fileds = use.getClass().getDeclaredFields();

        for(Field filed : fileds){
            DConfig annotation = filed.getAnnotation(DConfig.class);
            if(annotation != null){
                PropertyDescriptor pd = new PropertyDescriptor(filed.getName(), TestCase.class);
                Method setterName = pd.getWriteMethod();

                if(setterName!=null){
                    String value = annotation.value();
                    filed.setAccessible(true);
                    setterName.invoke(use, value);
                }
            }
        }

        System.out.printf(use.getDb());;
    }
}

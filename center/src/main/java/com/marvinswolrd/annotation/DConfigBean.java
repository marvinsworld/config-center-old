package com.marvinswolrd.annotation;

import com.marvinswolrd.dconfig.annotation.DConfig;
import org.springframework.stereotype.Service;

/**
 * Description:.
 *
 * @author zhiqiang.ge
 * @version V1.0
 * @since 2015/8/23 14:10
 */
@Service
public class DConfigBean {

    @DConfig("bbb")
    private String db;

    @DConfig("${aaa}")
    private String db1;

//    @Value("${aaa}")
//    private String db2;

    public void setDb(String db) {
        this.db = db;
    }

    public String getDb() {
        return db;
    }

    public String getDb1() {
        return db1;
    }

    public void setDb1(String db1) {
        this.db1 = db1;
    }

//    public String getDb2() {
//        return db2;
//    }
//
//    public void setDb2(String db2) {
//        this.db2 = db2;
//    }

    //    public String getDb2() {
//        return db2;
//    }
//
//    public void setDb2(String db2) {
//        this.db2 = db2;
//    }
//
//    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, IntrospectionException {
//        TestCase use = new TestCase();
//
//        Field[] fileds = use.getClass().getDeclaredFields();
//
//        for(Field filed : fileds){
//            DConfig annotation = filed.getAnnotation(DConfig.class);
//            if(annotation != null){
//                PropertyDescriptor pd = new PropertyDescriptor(filed.getName(), TestCase.class);
//                Method setterName = pd.getWriteMethod();
//
//                if(setterName!=null){
//                    String value = annotation.value();
//                    filed.setAccessible(true);
//                    setterName.invoke(use, value);
//                }
//            }
//        }
//
//        System.out.printf(use.getDb());;
//    }
}

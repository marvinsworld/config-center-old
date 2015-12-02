package com.marvinsworld;

import com.marvinswolrd.dconfig.demo.DConfigBean;
import com.marvinswolrd.dconfig.demo.DConfigBean2;
import com.marvinswolrd.spring.SpringBeanUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Description:.
 *
 * @author zhiqiang.ge
 * @version V1.0
 * @since 2015/8/23 15:10
 */
@ContextConfiguration({"classpath:spring-config.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class DConfigTest {

    @Resource
    private DConfigBean dConfigBean;

    @Resource
    private DConfigBean2 dConfigBean2;

//    @Resource
//    private Springfactory beanFactoryHelper;
//
//    @Resource
//    private SpringContextUtil springContextUtil;

    @Test
    public void test() {

        DConfigBean ben = (DConfigBean) SpringBeanUtils.getBean("DConfigBean");

        System.out.println("db1=" + dConfigBean.getDb1());
        System.out.println("db2=" + dConfigBean.getDb2());
        System.out.println("db3=" + dConfigBean.getDb3());

        System.out.println("db1=" + dConfigBean2.getDb1());
        System.out.println("db2=" + dConfigBean2.getDb2());
        System.out.println("db3=" + dConfigBean2.getDb3());
    }
}

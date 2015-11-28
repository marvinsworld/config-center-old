package com.marvinsworld;

import com.marvinswolrd.dconfig.demo.DConfigBean;
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

    @Test
    public void test() {
        System.out.println("db1=" + dConfigBean.getDb1());
        System.out.println("db2=" + dConfigBean.getDb2());
        System.out.println("db3=" + dConfigBean.getDb3());
    }
}

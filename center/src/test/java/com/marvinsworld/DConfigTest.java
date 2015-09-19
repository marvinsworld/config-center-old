package com.marvinsworld;

import com.marvinswolrd.annotation.DConfigBean;
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
    public void test(){
        System.out.print(dConfigBean);
    }
}

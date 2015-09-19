package com.marvinsworld;

import com.marvinswolrd.demo.ConfigBean;
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
public class ConfigTest {

    @Resource
    private ConfigBean configBean;

    @Test
    public void test(){
        System.out.print(configBean);
    }
}

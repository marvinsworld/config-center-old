package com.marvinswolrd.center.reg;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.stereotype.Service;

/**
 * Description:
 *
 * @author Marvinsworld
 * @since 2015/8/20 6:48
 */
@Service
public class RegisterCenter {


    public CuratorFramework createClient() {

        CuratorFramework client = CuratorFrameworkFactory.builder().connectString("192.168.8.3:2181").sessionTimeoutMs(5000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .namespace("centerSpace0")
                .build();

//        RetryPolicy policy = new ExponentialBackoffRetry(1000, 3);
//        CuratorFramework client = CuratorFrameworkFactory.newClient("192.168.8.3:2181", policy);
        return client;
    }

    public void init(){
        System.out.println("-------------");
    }

    /**
     * 注册节点
     */
    public void regNode(){

    }

    /**
     * 创建连接
     */
    public void createConnection(){

    }

}

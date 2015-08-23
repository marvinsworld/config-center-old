package com.marvinswolrd.center.reg;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

/**
 * Description:
 *
 * @author Marvinsworld
 * @since 2015/8/20 6:48
 */
public class RegisterCenter {


    public CuratorFramework createClient() {
        RetryPolicy policy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient("192.168.8.3:2181", policy);
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



    public static void main(String[] args) throws Exception {
        RegisterCenter configCenter = new RegisterCenter();

        CuratorFramework client = configCenter.createClient();
        client.start();

        client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath("/create", "222".getBytes());

//        client.newNamespaceAwareEnsurePath("/create");
//
//
//        client.setData().forPath("/create", "222".getBytes());
        System.out.println(new String(client.getData().forPath("/create")));
        System.out.println("111");
    }
}

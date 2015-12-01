package com.marvinswolrd.center.reg;

import org.apache.curator.framework.CuratorFramework;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Description:.
 *
 * @author zhiqiang.ge
 * @version V1.0
 * @since 2015/11/29 10:50
 */
@ContextConfiguration({"classpath:spring-config.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class RegisterCenterTest {

    @Test
    public void testConnection() throws Exception {
        RegisterCenter configCenter = new RegisterCenter();

        CuratorFramework client = configCenter.createClient();
        client.start();

        //client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath("/config01", "01".getBytes());

        //client.newNamespaceAwareEnsurePath("/create");

        //client.setData().forPath("/create", "222".getBytes());
        //System.out.println(new String(client.getData().forPath("/config.center.db2")));
        //System.out.println("111");

        System.out.println("getNamespace=" + client.getNamespace());

        getChildrenPath(client, "/");


//        List<String> paths=client.getChildren().forPath("");
//        for(String p:paths){
//            System.out.println("---"+p);
//        }
//
//
//        System.out.println("-----------");
//        List<String> paths1=client.getChildren().forPath("/config02");
//        for(String p:paths1){
//            System.out.println("---"+p);
//        }

        //System.out.println("1111");

    }

    public static void getChildrenPath(CuratorFramework client, String path) throws Exception {
        List<String> paths = client.getChildren().forPath(path);


        for (String p : paths) {
            System.out.println(p);
            if(path.equals("/")){
                path="";
            }

            getChildrenPath(client, path +"/"+ p);
        }
    }

//    public void getListChildren(String path)throws Exception{
//
//        List<String> paths=zkclient.getChildren().forPath(path);
//        for(String p:paths){
//            System.out.println(p);
//        }
//
//    }

}

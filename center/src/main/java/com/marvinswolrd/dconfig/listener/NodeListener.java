package com.marvinswolrd.dconfig.listener;

import com.marvinswolrd.center.reg.RegisterCenter;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.springframework.stereotype.Service;

/**
 * Description:.
 *
 * @author zhiqiang.ge
 * @version V1.0
 * @since 2015/12/1 8:37
 */
@Service
public class NodeListener {
    static CuratorFramework client = RegisterCenter.createClient();

    public NodeListener(){
        try {
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void init() throws Exception {
        client.start();
        final NodeCache cache = new NodeCache(client,"/centerSpace0/config0",false);

        cache.start(true);
        cache.getListenable().addListener(new NodeCacheListener() {
            public void nodeChanged() throws Exception {
                System.out.printf("node change="+new String(cache.getCurrentData().getData()));
            }
        });
    }




    public static void main(String[] args) throws Exception {
        CuratorFramework client = RegisterCenter.createClient();
        client.start();

        final NodeCache cache = new NodeCache(client,"/centerSpace0/config0",false);

        cache.start(true);
        cache.getListenable().addListener(new NodeCacheListener() {
            public void nodeChanged() throws Exception {
                System.out.printf("node change="+new String(cache.getCurrentData().getData()));
            }
        });

        Thread.sleep(5000);


    }
}

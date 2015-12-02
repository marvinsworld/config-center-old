package com.marvinswolrd.dconfig.listener;

import com.marvinswolrd.center.reg.RegisterCenter;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;

/**
 * Description:.
 *
 * @author zhiqiang.ge
 * @version V1.0
 * @since 2015/12/1 8:37
 */
public class NodeListener {

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
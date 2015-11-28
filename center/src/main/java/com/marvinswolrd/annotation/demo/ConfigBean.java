package com.marvinswolrd.annotation.demo;

import org.springframework.stereotype.Service;

/**
 * Description:.
 *
 * @author zhiqiang.ge
 * @version V1.0
 * @since 2015/8/23 14:10
 */
@Service
public class ConfigBean {

    @Config("aaa")
    private String db;

    @Config("${aaa}")
    private String db1;



    public void setDb(String db) {
        this.db = db;
    }

    public String getDb() {
        return db;
    }

    public String getDb1() {
        return db1;
    }

    @Config("${aaa}")
    public void setDb1(String db1) {
        this.db1 = db1;
    }
}

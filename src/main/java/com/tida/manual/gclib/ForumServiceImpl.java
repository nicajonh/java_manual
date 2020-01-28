package com.tida.manual.gclib;

import com.tida.manual.aop.BeProxiedClass;

import static java.lang.Thread.*;

/**
 * Created by nicajonh on 2019/3/11.
 * Description ${TEXT}
 */
public class ForumServiceImpl implements ForumService{
    public void removeTopic(int topic) throws InterruptedException {
        System.out.println("模拟删除主题"+topic);
        (new BeProxiedClass().new ThirdMethodClass()).thirdBeProxied();
        try{
            currentThread().sleep(20);
        }catch(Exception e){
            throw new RuntimeException(e);
        }

    }
    public void removeForum(int forumId){
        System.out.println("模拟删除论坛"+forumId);
        try{
            currentThread().sleep(20);
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}

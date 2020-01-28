package com.tida.manual.gclib;

import com.tida.manual.aop.MyAdice;

/**
 * Created by nicajonh on 2019/3/11.
 * Description ${TEXT}
 */
public class CglibTest {
    public static void main(String[] args) throws InterruptedException {
        Cglibproxy proxy= new Cglibproxy();
        ForumServiceImpl forumServiceImpl = (ForumServiceImpl)proxy.getProxy(ForumServiceImpl.class);
        forumServiceImpl.removeForum(456);
        forumServiceImpl.removeTopic(987);
    }
}

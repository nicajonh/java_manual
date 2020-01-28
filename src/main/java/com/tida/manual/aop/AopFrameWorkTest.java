package com.tida.manual.aop;

import com.tida.manual.common.ProxyService;

import java.io.InputStream;
import java.util.Collection;

/**
 * Created by nica007 on 2018/1/26.
 * Description ${TEXT}
 */
public class AopFrameWorkTest {
    public static void main(String args[]) throws InterruptedException {
        InputStream inputStream=AopFrameWorkTest.class.getResourceAsStream("config.properties");
        Object proxy=new BeanFactory(inputStream).getBean("xxx");
        ((ProxyService) proxy).beProxied();
        System.out.println(proxy.getClass().getName());
        //((Collection)proxy).clear();
    }
}

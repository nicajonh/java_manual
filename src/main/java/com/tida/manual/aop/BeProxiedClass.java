package com.tida.manual.aop;

import com.tida.manual.common.ProxyService;

/**
 * Created by nicajonh on 2019/3/11.
 * Description ${TEXT}
 */
public class BeProxiedClass implements ProxyService {
    @Override
    public void beProxied() throws InterruptedException {
        System.out.println("I am proxied....!");
        (new BeProxiedClass().new ThirdMethodClass()).thirdBeProxied();
        Thread.currentThread().sleep(10);
    }
    public class ThirdMethodClass{
        public void thirdBeProxied() throws InterruptedException {
            System.out.println("第三方被调用了!!!!");
            Thread.currentThread().sleep(15);
        }
    }

}

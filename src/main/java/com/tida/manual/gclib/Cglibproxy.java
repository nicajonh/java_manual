package com.tida.manual.gclib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by nicajonh on 2019/3/11.
 * Description ${TEXT}
 */
public class Cglibproxy implements MethodInterceptor {

    private Enhancer enhancer = new Enhancer();
    public Object getProxy(Class clazz){
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        return enhancer.create();
    }
    @Override
    public Object intercept(Object proxy, Method method, Object[] args,
                            MethodProxy methodProxy) throws Throwable {
        PerformanceMonitor.begin(proxy.getClass().getName() + "." + method.getName());
        //		Object result = arg3.invoke(arg0, arg2);报错
        Object obj = methodProxy.invokeSuper(proxy, args);
        PerformanceMonitor.end();
        return obj;
    }
}



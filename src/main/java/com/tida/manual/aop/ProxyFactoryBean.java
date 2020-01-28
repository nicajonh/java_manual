package com.tida.manual.aop;

import com.tida.manual.common.Advice;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by nica007 on 2018/1/26.
 * Description ${TEXT}
 */
public class ProxyFactoryBean {
    private Object target;
    private Advice advice;

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public Advice getAdvice() {
        return advice;
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

    public Object getProxy(){

        Object proxy= Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        advice.beforeMethod(method);
                        Object retVal=method.invoke(target,args);
                        advice.afterMethod(method);
                        return retVal;
                    }
                });
        return proxy;
    }



}

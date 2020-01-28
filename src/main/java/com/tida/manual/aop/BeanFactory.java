package com.tida.manual.aop;

import com.tida.manual.common.Advice;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by nica007 on 2018/1/26.
 * Description ${TEXT}
 */
public class BeanFactory {
    Properties properties=new Properties();
    public BeanFactory(InputStream inputStream){
        try{
            properties.load(inputStream);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public Object getBean(String name){
        String className=properties.getProperty(name);
        Object bean=null;
        try {
            Class clazz=Class.forName(className);
            bean=clazz.newInstance();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        if (bean instanceof ProxyFactoryBean){
            ProxyFactoryBean proxyFactoryBean=(ProxyFactoryBean)bean;
            Advice advice=null;
            Object target=null;
            try {
                advice = (Advice)Class.forName(properties.getProperty(name+".advice")).newInstance();
                target = Class.forName(properties.getProperty(name+".target")).newInstance();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            proxyFactoryBean.setAdvice(advice);
            proxyFactoryBean.setTarget(target);
            Object proxy=((ProxyFactoryBean)bean).getProxy();
            return proxy;
        }
        return bean;
    }
}

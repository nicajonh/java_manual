package com.tida.manual.aop;

import com.tida.manual.common.Advice;

import java.lang.reflect.Method;

/**
 * Created by nica007 on 2018/1/25.
 * Description ${TEXT}
 */
public class MyAdice implements Advice {
    private long beginTime=0;
    private long endTime=0;

    public long getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(long beginTime) {
        this.beginTime = beginTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    @Override
    public void beforeMethod(Method method){
        this.beginTime=System.currentTimeMillis();
        System.out.println(method.getName()+" method before at time:"+beginTime);
    }
    @Override
    public void afterMethod(Method method){
        this.endTime=System.currentTimeMillis();
        System.out.println(method.getName()+" method cost total time:"+(endTime-beginTime));
    }
}

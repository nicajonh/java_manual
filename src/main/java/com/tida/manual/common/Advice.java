package com.tida.manual.common;

import java.lang.reflect.Method;

/**
 * Created by nica007 on 2018/1/25.
 * Description ${TEXT}
 */
public interface Advice {
    void beforeMethod(Method method);
    void afterMethod(Method method);
}

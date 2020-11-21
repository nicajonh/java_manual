package com.tida.manual.diymap;


public interface DIYMap<K,V>{
    //Map双列集合,基本功能是快速取
    V get(K k, V v);


    //快速取
   V get(K v);

    //定义一个内部接口
    interface Entry<K,V>{
        K getKey();
        V getValue();
    }

}

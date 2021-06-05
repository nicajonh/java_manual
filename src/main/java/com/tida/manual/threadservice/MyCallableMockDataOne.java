package com.tida.manual.threadservice;
import java.util.Random;
import java.util.concurrent.Callable;

//Callable<Integer> callable = new Callable<Integer>() {
//public Integer call() throws Exception {
//        return new Random().nextInt(100);
//        }
//        };


class MyCallableMockDataOne implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        return new Random().nextInt(100);
    }
}

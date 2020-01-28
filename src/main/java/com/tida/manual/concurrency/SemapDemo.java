package com.tida.manual.concurrency;/**
 * Created by nicajonh on 2019/3/14.
 * Description ${TEXT}
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @ClassName SemapDemo
 * @Description TODO
 * @Author nicajonh
 * @Date 2019/3/14 19:05
 * @Version 1.0
 **/
public class SemapDemo implements Runnable {

    //控制5个线程可以同时获取信号量的许可
    final Semaphore semaphore=new Semaphore(5);
    @Override
    public void run() {
        try {
            semaphore.acquire();
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getId()+":done!");
            semaphore.release();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        ExecutorService exec=Executors.newFixedThreadPool(20);
        //20个线程,所以每次5个5个执行
        final SemapDemo semapDemo=new SemapDemo();
        for (int i=0;i<20;i++){
            exec.submit(semapDemo);
        }
    }
}

package com.tida.manual.service;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class NettyMain{
	public static void main(String[] args){
		ExecutorService work = Executors.newCachedThreadPool();
		ExecutorService boss = Executors.newCachedThreadPool();
        //初始化线程池
        ThreadHandle threadHandle = new ThreadHandle(boss,work);
        //声明端口
        threadHandle.bind(new InetSocketAddress(9000));
        System.out.println("start");
	}
}
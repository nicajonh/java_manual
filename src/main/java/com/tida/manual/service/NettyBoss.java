package com.tida.manual.service;

import java.io.IOException;
import java.nio.channels.*;

import java.util.Iterator;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

public class NettyBoss{
	//线程池
	public final Executor executor;
	//boss选择器
	protected Selector selector;
	
	//原子变量,主要用来保护线程安全,当本线执行的时候,排除其他线程的执行
	protected final AtomicBoolean wakeUp=new AtomicBoolean();
	
	//队列,线程安全队列。
	public final Queue<Runnable> taskQueue=new ConcurrentLinkedQueue<>();
	//线程处理,这主要是拿到work的线程池
	protected ThreadHandle threadHandle;
	
	//初始化
	public NettyBoss(Executor executor,ThreadHandle threadHandle){
		this.executor=executor;
		this.threadHandle=threadHandle;
		try{
			this.selector=Selector.open();
		}catch(IOException e){
			e.printStackTrace();
		}
		//从线程池中获取一个线程执行以下内容

		executor.execute(()->{
			while(true){
				try{
					//这里的目前就是排除其他线程同时执行false因为这里阻塞状态,不用开启
					wakeUp.set(false);
					//选择器阻塞
					selector.select();
					//运行队列中的任务
					while(true){
						final Runnable task=taskQueue.poll();
						if (task==null){
							break;
						}
						//如果任务在开始运行
						task.run();
					}
					//分配worker对进来的select事件处理
					this.process(selector);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		});
	}
	public void process(Selector selector) throws IOException {
		Set<SelectionKey> selectedKeys=selector.selectedKeys();
		if(selectedKeys.isEmpty()){
			return;
		}
		for(Iterator<SelectionKey> i = selectedKeys.iterator();i.hasNext();){
			SelectionKey key=i.next();
			i.remove();
			ServerSocketChannel server=(ServerSocketChannel) key.channel();
			//新客户端
			SocketChannel channel = server.accept();
			//设置为非阻塞
			channel.configureBlocking(false);
			//获取一个Worker
			NettyWork nextwork= threadHandle.workers[Math.abs(threadHandle.workIndex.getAndIncrement())%threadHandle.workers.length];
			//注册新客户端接入任务
			Runnable runnable=()->{
				try{
					//将客户端注册到selector中
					channel.register(nextwork.selector,SelectionKey.OP_READ);
				}catch(ClosedChannelException e){
					e.printStackTrace();
				}
			};

			//添加到work的队列中
			nextwork.taskQueue.add(runnable);
			if (nextwork.selector!=null){
				//这里的worker就是开启执行过程
				if(nextwork.wakeUp.compareAndSet(false,true)){
					//放开本次阻塞,进行下一步执行
					nextwork.selector.wakeup();
				}
			}else{
				//任务完成移除线程
				taskQueue.remove(runnable);
			}
			System.out.println("新客户端链接");
			
		}
		
	}
}
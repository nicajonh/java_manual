package com.tida.manual.tutoriol;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedTransferQueue;

/**
 * Created by nica007 on 2018/12/25.
 * Description ${TEXT}
 */
public class LinkedTransferQueueDemo {

    static LinkedTransferQueue<String> linkedTransferQueue= new LinkedTransferQueue<>();

    public static void main(String[] args){
        ExecutorService excServer=Executors.newFixedThreadPool(2);
        Producer producer= new LinkedTransferQueueDemo().new Producer();
        Consumer consumer= new LinkedTransferQueueDemo().new Consumer();

        excServer.execute(producer);
        excServer.execute(consumer);
        excServer.shutdown();
        
    }


    class Consumer implements Runnable{
        @Override
        public void run(){
            for (int i=0;i<3;i++){
                try{
                    System.out.println("Consumer is waiting to take element...");
                    String s= linkedTransferQueue.take();
                    System.out.println("Consumer received Element: "+s);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }

    class Producer implements Runnable{
        @Override
        public void run(){
            for (int i=0;i<3;i++){
                try{
                    System.out.println("Producer is waiting to transfer...");
                    linkedTransferQueue.transfer("A"+i);
                    System.out.println("producer transfered element: A"+i);

                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }
}

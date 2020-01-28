package com.tida.manual.concurrency;/**
 * Created by nicajonh on 2019/3/14.
 * Description ${TEXT}
 */

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName ReenterLockerCondition
 * @Description TODO
 * @Author nicajonh
 * @Date 2019/3/14 19:12
 * @Version 1.0
 **/
public class ReenterLockerCondition implements Runnable {
    public static ReentrantLock lock = new ReentrantLock();
    public static Condition condition=lock.newCondition();
    @Override
    public void run() {
        try {
            lock.lock();
            condition.await();
            System.out.println("Thead is going on");
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public static void main(String[] args) throws InterruptedException {
        ReenterLockerCondition condition1= new ReenterLockerCondition();
        Thread t2 = new Thread(condition1);
        t2.start();
        Thread.sleep(2000);
        System.out.println("send signal to condition");
        //通知线程t2继续执行
        lock.lock();
        condition.signal();
        lock.unlock();
    }
}

package com.tida.manual.concurrency;/**
 * Created by Administrator on 2020/12/23.
 * Description ${TEXT}
 */

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName AtomicIntegerTest
 * @Description TODO
 * @Author Administrator
 * @Date 2020/12/23 16:08
 * @Version 1.0
 **/
public class AtomicIntegerTest {    // 请求总数
    public static int clientTotal = 5000;    // 同时并发执行的线程数
    public static int threadTotal = 200;
    public static AtomicInteger count = new AtomicInteger(0);
    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal ; i++) {
            executorService.execute(() -> {
                try {
                semaphore.acquire();
                add();
                semaphore.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        System.out.println(count);
    }
    private static void add() {
        count.incrementAndGet();
    }
}
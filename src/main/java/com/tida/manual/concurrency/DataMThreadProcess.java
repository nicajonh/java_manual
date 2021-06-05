package com.tida.manual.concurrency;/**
 * Created by Administrator on 2021/6/5.
 * Description ${TEXT}
 */

import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.*;
import com.tida.manual.threadservice.MyCallableMockDataTwo;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @ClassName DataMThreadProcess
 * @Description 多线程处理数据
 * @Author Administrator
 * @Date 2021/6/5 16:39
 * @Version 1.0
 **/
public class DataMThreadProcess {

    private List<String> result = new ArrayList<>();
    private List<String> list = new ArrayList<>();

    //thread pool
    TimeUnit unit;
    BlockingQueue workQueue;
    static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(4, 10, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>(200), new ThreadPoolExecutor.CallerRunsPolicy());

    public void mockData() {
        //模拟原始数据
        for (int i = 0; i < 1211; i++) {
            list.add(i + "-");
            System.out.println("添加原始数据:" + i);
        }
    }

    /**
     * @Title: DataMThreadProcess
     * @Package com.tida.manual.concurrency
     * @Description: 模拟数据
     * @author nicajonh
     * @date 2021/6/5 16:44
     * @version V1.0
     */
    public String parse() throws Exception {

        int size = 50;//切分粒度，每size条数据，切分一块，交由一条线程处理
        int countNum = 0;//当前处理到的位置
        int count = list.size() / size;//切分块数
        int threadNum = 0;//使用线程数
        if (count * size > list.size()) {
            count++;
        }

        final CountDownLatch countDownLatch = new CountDownLatch(count);


        //使用Guava的ListeningExecutorService装饰线程池
        ListeningExecutorService executorService = MoreExecutors.listeningDecorator(threadPoolExecutor);
        while (countNum < count * size) {
            //分段处理数据
            threadNum++;
            countNum += size;
            MyCallableMockDataTwo myCallableMockDataTwo = new MyCallableMockDataTwo();
            myCallableMockDataTwo.setList(ImmutableList.copyOf(
                    list.subList(countNum - size, list.size() > countNum ? countNum : list.size())));

            ListenableFuture listenableFuture = executorService.submit(myCallableMockDataTwo);

            //call back process data
            //回调函数
            Futures.addCallback(listenableFuture, new FutureCallback<List<String>>() {
                //任务处理成功时执行
                @Override
                public void onSuccess(List<String> list) {
                    countDownLatch.countDown();
                    System.out.println("第h次处理完成");
                    result.addAll(list);
                }

                //任务处理失败时执行
                @Override
                public void onFailure(Throwable throwable) {
                    countDownLatch.countDown();
                    System.out.println("处理失败："+throwable);
                }
            });
        }
        //设置时间，超时了直接向下执行，不再阻塞
        countDownLatch.await(3, TimeUnit.SECONDS);
        result.forEach(s -> System.out.println(s));
        System.out.println("------------结果处理完毕，返回完毕,使用线程数量：" + threadNum);
        return "处理完了";
    }

    public static void main(String[] args) throws Exception {
        DataMThreadProcess dataMThreadProcess = new DataMThreadProcess();
        dataMThreadProcess.mockData();
        dataMThreadProcess.parse();
    }
}

package com.tida.manual.threadservice;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;

//Callable<Integer> callable = new Callable<Integer>() {
//public Integer call() throws Exception {
//        return new Random().nextInt(100);
//        }
//        };


public class MyCallableMockDataTwo implements Callable{

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    private List<String> list;

    @Override
    public List<String> call() throws Exception {
        List<String> listReturn = new ArrayList<>();
        //mock
        for (int i = 0; i < list.size(); i++) {
            listReturn.add(list.get(i)+"：处理时间："+System.currentTimeMillis()+"---:处理线程："+Thread.currentThread());
        }
        return listReturn;
    }
}

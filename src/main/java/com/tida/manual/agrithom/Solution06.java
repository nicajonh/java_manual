package com.tida.manual.agrithom;/**
 * Created by Administrator on 2020/3/6.
 * Description ${TEXT}
 */

import javax.xml.crypto.Data;
import java.util.PriorityQueue;

/**
 * @ClassName Solution06
 * @Description 求解topk问题
 * @Author Administrator
 * @Date 2020/3/6 15:32
 * @Version 1.0
 **/

class DataWithSource implements Comparable<DataWithSource>{


    /**
     * 数值
     */
    private int value;

    /**
     * 记录数值来源的数组
     */
    private int source;

    /**
     * 记录数值在数组中的索引
     */
    private int sourceIndex;

    public DataWithSource(int value, int source, int sourceIndex) {
        this.value=value;
        this.source=source;
        this.sourceIndex=sourceIndex;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getSourceIndex() {
        return sourceIndex;
    }

    public void setSourceIndex(int sourceIndex) {
        this.sourceIndex = sourceIndex;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }
    @Override
    public int compareTo(DataWithSource o) {
        return Integer.compare(o.getValue(),this.getValue());
    }

}


/**
 *
 * 由于 PriorityQueue 使用小顶堆来实现，这里通过修改
 * 两个整数的比较逻辑来让 PriorityQueue 变成大顶堆
 */
class Solution06 {
    //data array
    private int[][] data;
    Solution06(int[][] data){
        this.data=data;
    }
    public int[] getTopk(){
        int rowSize=data.length;
        int columnSize=data[0].length;
        // new data array for store result
        int[] result = new int[columnSize];
        PriorityQueue<DataWithSource> maxHeap=new PriorityQueue<>();
        for (int i = 0; i < data.length; i++) {
            // 将每个数组的最大一个元素放入堆中
            DataWithSource d = new DataWithSource(data[i][0],i,0);
            maxHeap.add(d);
        }
        int num=0;
        while (num<columnSize){
            //删除堆顶元素
            DataWithSource d = maxHeap.poll();
            result[num++]=d.getValue();
            if(num>columnSize){
                break;
            }
            //取出改组后续元素,继续放入最大堆中
            d.setValue(data[d.getSource()][d.getSourceIndex()+1]);
            d.setSourceIndex(d.getSourceIndex()+1);
            maxHeap.add(d);
        }
        return result;
    }
}

package com.tida.manual.agrithom;/**
 * Created by Administrator on 2020/1/28.
 * Description ${TEXT}
 */

/**
 * @ClassName Node
 * @Description TODO
 * @Author Administrator
 * @Date 2020/1/28 17:40
 * @Version 1.0
 **/
class LinkNode<T> implements Comparable {

    public T data;
    public LinkNode nextNode;
    public LinkNode(T data){this.data=data;}

    @Override
    public int compareTo(Object o) {
        LinkNode o2=(LinkNode)o;
        if((Integer)this.data>(Integer)o2.data){
            return 1;
        }else if((Integer)this.data < (Integer)o2.data) {
            return -1;
        }else {
            return 0;
        }
    }
}

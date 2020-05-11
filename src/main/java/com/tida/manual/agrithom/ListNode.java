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
class ListNode<T> implements Comparable {

    public T val;
    public ListNode next;
    public ListNode(T data){this.val =data;}

    @Override
    public int compareTo(Object o) {
        ListNode o2=(ListNode)o;
        if((Integer)this.val >(Integer)o2.val){
            return 1;
        }else if((Integer)this.val < (Integer)o2.val) {
            return -1;
        }else {
            return 0;
        }
    }
}

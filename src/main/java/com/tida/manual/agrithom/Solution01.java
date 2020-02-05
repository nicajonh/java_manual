package com.tida.manual.agrithom;

/**
 * @ClassName Solution01
 * @Description 在链表中删除倒数n的节点
 * @Author nicajonh
 * @Date 2019/11/19 17:50
 * @Version 1.0
 **/
class Solution01 {


    LinkNode removeNthFromEnd(LinkNode head, int nth) throws Exception {
        LinkNode before = new LinkNode('0');
        LinkNode end = new LinkNode('0');
        //get length of linknode
        int length=0;
        if(nth<=0){
            throw new Exception("error input val");
        }
        end=before=head;
        while(before!=null){
            before=before.next;
            length++;
        }
        if(length==nth){
            head=head.next;
            return head;
        }
        int removeNth=length-nth-1;//倒数第n个节点
        while(removeNth>0){
            end=end.next;
            removeNth--;
        }
        end.next =end.next.next;
        return head;
    }
}

package com.tida.manual.agrithom;

/**
 * @ClassName Solution01
 * @Description 在链表中删除倒数n的节点
 * @Author nicajonh
 * @Date 2019/11/19 17:50
 * @Version 1.0
 **/
class Solution01 {


//    ListNode removeNthFromEnd(ListNode head, int nth) throws Exception {
//        ListNode before = new ListNode('0');
//        ListNode end = new ListNode('0');
//        //get length of linknode
//        int length=0;
//        if(nth<=0){
//            throw new Exception("error input val");
//        }
//        end=before=head;
//        while(before!=null){
//            before=before.next;
//            length++;
//        }
//        if(length==nth){
//            head=head.next;
//            return head;
//        }
//        int removeNth=length-nth-1;//倒数第n个节点
//        while(removeNth--!=0){
//            end=end.next;
//        }
//        end.next =end.next.next;
//        return head;
//    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode afterNode = head;

        ListNode current = head;
        int length=0;
        if(n<0){
            return head;
        }
        while(current!=null){
            current=current.next;
            length++;
        }
        int nth=(length-n)%length-1;
        if(nth==-1){
            head=afterNode.next;
            return head;
        }
        while(nth-->0){
            afterNode=afterNode.next;
        }

        if(afterNode.next!=null){
           // head=afterNode.next;
            afterNode.next=afterNode.next.next;
        }else{
            return head.next;
        }

        return head;
    }
}

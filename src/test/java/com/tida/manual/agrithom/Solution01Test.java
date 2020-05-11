package com.tida.manual.agrithom;

import org.junit.Test;

/**
 * Created by Administrator on 2020/1/28.
 * Description ${TEXT}
 */
public class Solution01Test {


    @Test
    public void TestSolution01() throws Exception {

        int length=0;
        ListNode head = null;
        ListNode curr = null;
        for(char i='1';i<='2';i++){
            if(head==null){
                head=new ListNode(i);
                curr=head;
            }else{
                ListNode linkNode=new ListNode(i);
                curr.next =linkNode;
                curr=curr.next;
            }
        }
        Solution01 solution01 = new Solution01();
        ListNode newhead=solution01.removeNthFromEnd(head,2);
        while(newhead!=null){
            System.out.println(newhead.val);
            newhead=newhead.next;
            length++;
        }
        System.out.println("length of linkNode is"+' '+length);

    }
}
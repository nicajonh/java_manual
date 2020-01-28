package com.tida.manual.agrithom;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2020/1/28.
 * Description ${TEXT}
 */
public class Solution01Test {


    @Test
    public void TestSolution01() throws Exception {

        int length=0;
        LinkNode head = null;
        LinkNode curr = null;
        for(char i='A';i<='F';i++){
            if(head==null){
                head=new LinkNode(i);
                curr=head;
            }else{
                LinkNode linkNode=new LinkNode(i);
                curr.nextNode=linkNode;
                curr=curr.nextNode;
            }
        }
        Solution01 solution01 = new Solution01();
        LinkNode newhead=solution01.removeNthFromEnd(head,2);
        while(newhead!=null){
            System.out.println(newhead.data);
            newhead=newhead.nextNode;
            length++;
        }
        System.out.println("length of linkNode is"+' '+length);

    }
}
package com.tida.manual.agrithom;/**
 * Created by nicajonh on 2019/5/7.
 * Description ${TEXT}
 */

/**
 * @ClassName findMedianSortedArrays
 * @Description 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度,分治思想
 * @Author nicajonh
 * @Date 2019/5/7 17:28
 * @Version 1.0
 **/
class Solution03 {
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length==0)
            return null;
        int left = 0;
        int right = lists.length-1;
        while (left<right){
            lists[left] = mergeTwoLists(lists[left],lists[right]);
            if (left==right-1){
                left = 0;
                right--;
            }else if(left==right-2){
                left = 0;
                right--;
            }else {
                left++;
                right--;
            }
        }
        return lists[0];
    }
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode l3 = new ListNode(0);
        ListNode l4 = l3;
        while(l1!=null&&l2!=null)
        {
            if((Integer)l1.val > (Integer)l2.val)
            {
                l3.next = l2;
                l2 = l2.next;
            }else{
                l3.next = l1;
                l1 = l1.next;
            }
            l3 = l3.next;
        }
        if(l1==null)
        {
            l3.next = l2;
        }else{
            l3.next = l1;
        }
        return l4.next;
    }
}

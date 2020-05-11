package com.tida.manual.agrithom;/**
 * Created by nicajonh on 2019/5/7.
 * Description ${TEXT}
 */

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @ClassName findMedianSortedArrays
 * @Description 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度
 * @Author nicajonh
 * @Date 2019/5/7 17:28
 * @Version 1.0
 **/
class Solution04 {
    public ListNode mergeKLists(ListNode[] lists) {
        int len = lists.length;
        if (len == 0) {
            return null;
        }
        PriorityQueue<ListNode> priorityQueue = new PriorityQueue<ListNode>(len, Comparator.comparingInt(a -> (int) a.val));
        ListNode dummyNode = new ListNode(-1);
        ListNode curNode = dummyNode;
        for (ListNode list : lists) {
            if (list != null) {
                // 这一步很关键，不能也没有必要将空对象添加到优先队列中
                priorityQueue.add(list);
            }
        }
        while (!priorityQueue.isEmpty()) {
            // 优先队列非空才能出队
            ListNode node = priorityQueue.poll();
            // 当前节点的 next 指针指向出队元素
            curNode.next = node;
            // 当前指针向前移动一个元素，指向了刚刚出队的那个元素
            curNode = curNode.next;
            if (curNode.next != null) {
                // 只有非空节点才能加入到优先队列中
                priorityQueue.add(curNode.next);
            }
        }
        return dummyNode.next;
    }
}

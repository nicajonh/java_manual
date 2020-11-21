package com.tida.manual.agrithom;/**
 * Created by nicajonh on 2019/5/7.
 * Description ${TEXT}
 */

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @ClassName findMedianSortedArrays
 * @Description A sequence of number is called arithmetic if it consists of at least three elements and if the difference between any two consecutive elements is the same.
 * 求等差数列个数
 * For example, these are arithmetic sequence:
 * @Author nicajonh
 * @Date 2019/5/7 17:28
 * @Version 1.0
 **/
class Solution05 {
    public int numberOfArithmeticSlices(int[] A) {
        int n = A.length;
        int res = 0;
        int[] dp = new int[n];
        for (int i=2;i<n;i++){
            if(A[i]-A[i-1]==A[i-1]-A[i-2])
                dp[i]=dp[i-1]+1;
            res+=dp[i];
        }
        return res;
    }
}

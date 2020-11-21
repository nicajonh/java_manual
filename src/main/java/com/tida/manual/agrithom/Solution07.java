package com.tida.manual.agrithom;/**
 * Created by nicajonh on 2019/5/7.
 * Description ${TEXT}
 */

/**
 * @ClassName insertInterval
 * @Description Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
 * You may assume that the intervals were initially sorted according to their start times.
 * For example, these are arithmetic sequence:
 * @Version 1.0
 **/
class Solution07 {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        if(intervals.length<=0) return intervals;
        int length=intervals.length;
        int[][] ans=new int[length][length];
        int k=0;
        int i=0;
        for (; i < length; i++) {
          int[] interval=intervals[i];
          if(interval[1]<newInterval[0]) ans[k++]=interval;
          else break;
        }
        for (; i < length; i++) {
            int[] interval=intervals[i];
            if(interval[0]<newInterval[1]) {
                newInterval[0] = Math.min(newInterval[0], interval[0]);//get min between interval's start and
                newInterval[1] = Math.max(newInterval[1], interval[1]);//get min between interval's start and
            }else break;
        }
        ans[k++]=newInterval;
        for (; i < length; i++) {
            ans[k++]=intervals[i];
        }
        return ans;
    }
    public static void main(String[] args) {
        Solution07 solution = new Solution07();
        int[][] intervals={{1,3},{6,9}};
        int[] newInterval = {2,5};
        //.out.println(solution.insert(intervals,newInterval));
        int[][] result=solution.insert(intervals,newInterval);
        for (int i = 0; i < result.length; i++) {
            int[] interval=result[i];
            System.out.println("start:"+interval[0]+": end:"+interval[1]);
        }
        //Interval.print(solution.insert(Interval.createTestData("[1,2],[3,5],[6,7],[8,10],[12,16]"), new Interval(4, 9)));
    }
}

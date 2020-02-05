package com.tida.manual.agrithom;

import junit.framework.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2020/2/5.
 * Description ${TEXT}
 */
public class Solution05Test {
    @Test
    public void TestSolution5(){
        int[][] numtrix= new int[][]{{3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5}};
        NumMatrix matrix=new NumMatrix(numtrix);
        int answer=9,result=0;
        result=matrix.sumRegion(2,1,4,3);
        Assert.assertEquals(answer,result);
    }
}
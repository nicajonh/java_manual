package com.tida.manual.agrithom;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2020/3/6.
 * Description ${TEXT}
 */
public class Solution06Test {

    @Test
    public void TestSolution6(){
        int[][] data = {
                {29, 17, 14, 2, 1},
                {19, 17, 16, 15, 6},
                {30, 25, 20, 14, 5},
        };
        Solution06 solution06=new Solution06(data);
        int[] topk=solution06.getTopk();
        System.out.println(Arrays.toString(topk));
    }

}
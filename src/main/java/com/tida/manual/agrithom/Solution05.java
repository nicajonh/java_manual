package com.tida.manual.agrithom;/**
 * Created by Administrator on 2020/2/5.
 * Description ${TEXT}
 */

/**
 * @ClassName Solution05
 * Range Sum Query 2D - Immutable
 * @Description Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
 * 来源：力扣（LeetCode）
 * 思路：搞清楚区域划分
 * @Author Administrator
 * @Date 2020/2/5 13:25
 * @Version 1.0
 **/
class NumMatrix {
    private int[][] dp;
    public NumMatrix(int[][] matrix) {
        if(matrix.length==0 || matrix[0].length==0) return;
        this.dp=new int[matrix.length+1][matrix[0].length+1];
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[i].length;j++){
                dp[i+1][j+1]+=dp[i+1][j]+dp[i][j+1]+matrix[i][j]-dp[i][j];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        if(row1>row2){
            swap(row1,row2);
        }
        if(col1>col1){
            swap(col1,col2);
        }
        return dp[row2+1][col2+1]-dp[row2+1][col1]-dp[row1][col2+1]+dp[row1][col1];
    }
    public void swap(int a1,int a2){
        int t=a1;
        a1=a2;
        a2=t;
    }
}
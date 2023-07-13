package com.qiqi.problem2;

import java.util.Arrays;

/**
 * @projectName: Test
 * @package: com.qiqi.problem2
 * @className: Day06
 * @author: Eric
 * @description: TODO
 * @date: 2023/7/11 22:49
 * @version: 1.0
 */
public class Day06 {

    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;

        long[] f = new long[n + 1];

        long[] g = new long[n + 1];

        for (int i = 1; i <= n; ++i) {

            f[i] = Math.max(g[i - 1] - nums[i - 1], f[i - 1]);

            g[i] = Math.max(f[i - 1] + nums[i - 1], g[i - 1]);

        }

        return Math.max(f[n], g[n]);
    }
    public int alternateDigitSum(int n) {
        String s = String.valueOf(n);
        int result = 0;
        for (int i = 1; i <= s.length(); i++) {
            result += i%2==1?s.charAt(i-1)-'0':-(s.charAt(i-1)-'0');
        }
        return result;
    }
    
    //荷兰国旗问题

    public static void test(int[] arr,int num){
        int right = arr.length;
        int index = 0;
        while( index<right){
            if (arr[index]<num){
                index++;
            }else if (arr[index] > num){
                swap(--right,index,arr);
            }
        }
    }

    private static void swap(int i, int index, int[] arr) {
        int num = arr[i];
        arr[i] = arr[index];
        arr[index] = num;
    }

    public static void main(String[] args) {
        minFallingPathSum(new int[][]{{2,1,3},{6,5,4},{7,8,9}});
    }

    //快排

    public static void quickSort(int[] arr){

    }

    //下降路径最小和
    public static int minFallingPathSum(int[][] matrix) {
        int len = matrix.length;
        int[][] dp = new int[len][len];
        for (int i = 0; i < len; i++) {
            dp[0][i] = matrix[0][i];
        }
        for (int i = 1; i <len ; i++) {
            for (int j = 0; j < len; j++) {
                dp[i][j] = matrix[i][j] + fun(matrix,i,j,dp);
            }
        }
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < len; i++) {
            result = Math.min(result,dp[len-1][i]);
        }
        return result;
    }

    private static int fun(int[][] matrix, int i, int j,int[][] dp) {
        if (j == 0){
            return Math.min(dp[i-1][j],dp[i-1][j+1]);
        }
        else if (j == matrix.length-1){
            return Math.min(dp[i-1][j],dp[i-1][j-1]);
        }
        return Math.min(dp[i-1][j],Math.min(dp[i-1][j+1],dp[i-1][j-1]));
    }

}

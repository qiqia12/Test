package com.qiqi.algorithm;

import java.util.Arrays;

/**
 * @projectName: Test
 * @package: com.qiqi.algorithm
 * @className: Day15
 * @author: Eric
 * @description: TODO
 * @date: 2023/5/31 16:33
 * @version: 1.0
 */
public class Day15 {
    int[][] f;
    int [][] g;
    public int mctFromLeafValues(int[] arr) {
        int len = arr.length;
        f = new int[len][len];
        g = new int[len][len];
        for (int i = len - 1; i >= 0; --i) {
            g[i][i] = arr[i];
            for (int j = i + 1; j < len; ++j) {
                g[i][j] = Math.max(g[i][j - 1], arr[j]);
            }
        }
        return dfs(0,len-1);
    }

    private int  dfs(int left, int right) {
        if (left == right) return 0;
        if (f[left][right]!=0) return f[left][right];
        int reslult = Integer.MAX_VALUE;
        for (int i = left; i <=right ; i++) {
            reslult = Math.min(reslult,dfs(left,i)+dfs(i+1,right)+g[left][i]*g[i+1][right]);
        }
        f[left][right] = reslult;
        return reslult;
    }

    public static long mostPoints(int[][] questions) {
        int len = questions.length;
        long[] dp = new long[len+1];
        for (int i = len-1; i >=0; i--) {
            dp[i] = questions[i][0];
            if (i+questions[i][1] +1 < len){
                dp[i] = Math.max(questions[i][0]+dp[i+questions[i][1]+1],dp[i+1]);
            }else{
                dp[i] = Math.max(questions[i][0],dp[i+1]);
            }
        }
        return dp[0];

    }

    public static void main(String[] args) {
        mostPoints(new int[][]{{21,5},{92,3},{74,2},{39,4},{58,2},{5,5},{49,4},{65,3}});
    }

    private long ddf(int[][] questions, int left, int right) {
        return 0;
    }

    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        Arrays.fill(dp,amount+1);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (i >= coins[j]){
                    dp[i] = Math.min(dp[i - coins[j]] +1 ,dp[i]);
                }
            }
        }
        return dp[amount] ==amount+1?-1:dp[amount];
    }
}

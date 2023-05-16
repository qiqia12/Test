package com.qiqi.algorithm;

import javax.swing.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @projectName: Test
 * @package: com.qiqi.algorithm
 * @className: Day04
 * @author: Eric
 * @description: TODO
 * @date: 2023/5/16 19:28
 * @version: 1.0
 */
public class Day04 {

    public int minDifficulty(int[] jobDifficulty, int d) {
        int len = jobDifficulty.length;
        int[][] dp = new int[d+1][len+1];
        for (int i = 0; i < len; i++) {
            dp[i][i] = jobDifficulty[i]+dp[i-1][i-1];
        }
        for (int i = 2; i <= len ; i++) {
            dp[1][i] = Math.max(dp[1][i-1],jobDifficulty[i-1]);
        }
        for (int i = 2; i <= d; i++) {
            for (int j = i; j <= len; j++) {
                int max = Integer.MAX_VALUE;
                for (int k = j; k <= len; k++) {
                    max = Math.min(max,jobDifficulty[k]);
                }
                dp[i][j] = dp[i-1][j-1]+max;
            }
        }

        return dp[d][len]==0?-1:dp[d][len];
    }



    public int maxProfit(int k, int[] prices) {
        int len = prices.length;
        int[][][] dp = new int[len][k+1][2];
        // 0 代表不持有  1  代表持有
        for (int i = 1; i <= k; i++) {
            dp[0][i][1] = - prices[0];
        }
        for (int i = 1; i < len; i++) {
            for (int j = k; j >= 1; j--) {
                dp[i][j][0] = Math.max(dp[i-1][j][0],dp[i-1][j][1]+prices[i]);
                dp[i][j][1] = Math.max(dp[i-1][j][1],dp[i-1][j-1][0]-prices[i]);
            }
        }
        return dp[len-1][k][0];
    }


    public int numTrees(int n) {
        if (n<=2) return n;
        int [] dp = new int[n+1];
        Arrays.fill(dp,1);
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                // 左侧 j 个节点与右侧 i-j-1 个节点能组成的 BST
                dp[i] += dp[j] * dp[i - j - 1];
            }
        }
        return dp[n];
    }

    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }     TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }


}

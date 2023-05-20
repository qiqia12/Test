package com.qiqi.algorithm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * @projectName: Test
 * @package: com.qiqi.algorithm
 * @className: Day09
 * @author: Eric
 * @description: TODO
 * @date: 2023/5/21 19:33
 * @version: 1.0
 */
public class Day09 {

    public static int storeWater(int[] bucket, int[] vat) {
        int mx = Arrays.stream(vat).max().getAsInt();
        if (mx == 0) {
            return 0;
        }
        int n = vat.length;
        int ans = 1 << 30;
        for (int x = 1; x <= mx; ++x) {
            int y = 0;
            for (int i = 0; i < n; ++i) {
                y += Math.max(0, (vat[i] + x - 1) / x - bucket[i]);
            }
            ans = Math.min(ans, x + y);
        }
        return ans;
    }

    private static int proc(int b, int v) {
        if (b == 0)return Integer.MAX_VALUE;
        int result = v/b;
        if (v%b!=0) result++;
        return result;
    }

    public static void main(String[] args) {
        int[] arr1 = {0};
        int[] arr2 = {0};
        System.out.println("storeWater(arr1,arr2) = " + storeWater(arr1, arr2));

    }
    public class TreeNode {
       int val;
       TreeNode left;
       TreeNode right;
       TreeNode() {}
       TreeNode(int val) { this.val = val; }
       TreeNode(int val, TreeNode left, TreeNode right) {
           this.val = val;
           this.left = left;
           this.right = right;
       }
   }
   public class Node{
        boolean flag;
        int max;
        int result;
        public Node(int max,int result){
            this.max = max;
            this.result = result;
        }
   }
   int res = 0;
    public int maxPathSum(TreeNode root) {
        process(root);
        return res;
    }

    private Node process(TreeNode root) {
        if (root == null) return new Node(0,0);
        Node left = process(root.left);
        Node right = process(root.right);
        int max = root.val +Math.max(0,Math.max(left.result, right.result)) ;
        res = Math.max(res,max);
        int result = root.val + left.max + right.max;
        res = Math.max(res,result);
        return new Node(max,result);
    }

    public int numSquares(int n) {
        int [] dp = new int[n+1];
        for (int i = 1; i <=n ; i++) {
            dp[i] = i;
            for (int j = 1; i - j * j >= 0; j++) {
                dp[i] = Math.min(dp[i],dp[i - j * j]+1);
            }
        }
        return dp[n];
    }


    //零钱兑换
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount+1];
        for (int i = 0; i < coins.length; i++) {
            dp[coins[i]] = 1;
        }
        int min = Arrays.stream(coins).min().getAsInt();
        for (int i = min+1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (i-coins[j]>0);
            }
            for (int j = 1; i - j >=0 ; j++) {
                if (dp[i-j]!=0){
                    dp[i] = Math.min(dp[i-1]+1,dp[i]);
                }
            }
        }


        return dp[amount];
    }
}

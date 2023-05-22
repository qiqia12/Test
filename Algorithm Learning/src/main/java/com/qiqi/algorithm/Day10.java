package com.qiqi.algorithm;

/**
 * @projectName: Test
 * @package: com.qiqi.algorithm
 * @className: Day10
 * @author: Eric
 * @description: TODO
 * @date: 2023/5/22 19:59
 * @version: 1.0
 */
public class Day10 {

    public static class TreeNode {
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


   static int num ;
    public static TreeNode sufficientSubset(TreeNode root, int limit) {
        boolean rootDeleted = process(root, 0);
        if (rootDeleted) {
            return null;
        }
        return root;

    }

    private static boolean process(TreeNode root, int sum) {
        if (root.left == null && root.right == null){
            return sum+root.val < num;
        }
        boolean leftNum = false,rightNum = false;
        if (root.left != null){
            leftNum = process(root.left,sum+root.val);
        }
        if (root.right != null){
            rightNum = process(root.right,sum+root.val);
        }
        if (leftNum ){
            root.left = null;
        }
        if (rightNum){
            root.right = null;
        }
        return leftNum && rightNum;
    }

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(5);
        TreeNode n2 = new TreeNode(4);
        TreeNode n3 = new TreeNode(11);
        TreeNode n4 = new TreeNode(7);
        TreeNode n5 = new TreeNode(1);
        n1.left = n2;
        n2.left = n3;
        n3.left = n4;
        n3.right = n5;
        sufficientSubset(n1,22);
    }

    //零钱兑换
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount+1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int i = 1; i <= amount; i++) {
                if (i<coin)
                    continue;
                dp[i] += dp[i-coin];
            }
        }
        return dp[amount];
    }


}

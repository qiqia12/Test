package com.qiqi.algorithm;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * @projectName: Test
 * @package: com.qiqi.algorithm
 * @className: Day14
 * @author: Eric
 * @description: TODO
 * @date: 2023/5/30 17:30
 * @version: 1.0
 */
public class Day14 {
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
    static HashSet<Integer> set;
    public static List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        set = new HashSet<>();
        for (int i : to_delete) {
            set.add(i);
        }
        List<TreeNode> result = new LinkedList<>();
        TreeNode cur = root;
        if (!set.contains(root.val)){
            result.add(root);
            dfs(root,result);
        }else {
            dfs(cur,result);
        }
        return result;
    }

    private static  void dfs(TreeNode cur, List<TreeNode> result) {
        if (cur == null) return;
        if (cur.left!=null && set.contains(cur.left.val)){
            dfs1(cur.left,result);
            cur.left = null;
        }else{
            dfs(cur.left,result);
        }
        if (cur.right!=null && set.contains(cur.right.val)){
            dfs1(cur.right,result);
            cur.right = null;
        }else{
            dfs(cur.right,result);
        }

    }

    private static void dfs1(TreeNode cur, List<TreeNode> result) {
        if (cur == null) return;
        if (cur.left!=null && !set.contains(cur.left.val)){
            result.add(cur.left);
            dfs(cur.left,result);
        }else{
            dfs1(cur.left,result);
        }
        if (cur.right!=null && !set.contains(cur.right.val)){
            result.add(cur.right);
            dfs(cur.right,result);
        }else{
            dfs1(cur.right,result);
        }
    }

    public static void main(String[] args) {
        findMaxForm(new String[]{"10","0001","111001","1","0"},5,3);
    }

    public static int findMaxForm(String[] strs, int m, int n) {
        int[][] arr = new int[strs.length][2];
        for (int i = 0; i < strs.length; i++) {
            arr[i] = pro(strs[i]);
        }
        int[][] dp = new int[m+1][n+1];
        for (int[] ints : arr) {
            if (ints[0]<=m && ints[1] <= n)
                dp[ints[0]][ints[1]] = 1;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                for (int[] ints : arr) {
                    if (i>=ints[0] && j>= ints[1]){
                        dp[i][j] = Math.max(dp[i][j],dp[i-ints[0]][j-ints[1]]+1);
                    }

                }
            }
        }
        return dp[m][n];
    }
    private static  int[] pro(String str) {
        int m = 0;
        int n = 0;
        for (char c : str.toCharArray()) {
            if (c == '0') {
                m++;
            } else {
                n++;
            }
        }
        return new int[]{m,n};
    }


}

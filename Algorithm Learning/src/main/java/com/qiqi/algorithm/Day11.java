package com.qiqi.algorithm;

import java.util.*;

/**
 * @projectName: Test
 * @package: com.qiqi.algorithm
 * @className: Day11
 * @author: Eric
 * @description: TODO
 * @date: 2023/5/23 19:42
 * @version: 1.0
 */
public class Day11 {

    public static int largestValsFromLabels(int[] values, int[] labels, int numWanted, int useLimit) {
        int len = values.length;
        int[][] arr = new int[len][2];
        for (int i = 0; i < len; i++) {
            arr[i][0] = values[i];
            arr[i][1] = labels[i];
        }
        Arrays.sort(arr,(a, b)-> {return b[0]-a[0];});
        int result = 0;
        HashSet<Integer> set = new HashSet<>();
        int index = 0;
        while(index<len && numWanted >= 1){
            if (!set.add(arr[index][1])){
                if (useLimit > 1){
                    numWanted--;
                    useLimit--;
                    result += arr[index][0];
                }
            }else{
                numWanted--;
                result += arr[index][0];
            }
            index++;
        }

        return result;
    }

    public static void main(String[] args) {
        int[] a1 = {9};
        int[] a2 = {2};
        largestValsFromLabels(a1,a2,1,1);
    }

    public int largestValsFromLabels1(int[] values, int[] labels, int numWanted, int useLimit) {
        int len = values.length;
        int [][] arr = new int[len][2];
        for (int i = 0; i < len; i++) {
            arr[i][0] = values[i];
            arr[i][1] = labels[i];
        }
        int[] ar = new int[len];
        for (int i = 0; i < len; i++) {
            if (ar[labels[i]] < useLimit){
                ar[labels[i]]++;
            }
        }
        Arrays.sort(arr,(a,b)->b[0]-a[0]);
        int index = 0;
        int result = 0;
        while(index < len && numWanted >=1){
            if (ar[arr[index][1]]>0){
                ar[arr[index][1]]--;
                result += arr[index][0];
                numWanted--;
            }
            index++;
        }
        return result;
    }

    //组合数组
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target+1];
        for (int num : nums) {
            if (num <= target){
                dp[num] =1;
            }
        }
        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                if (i>=num){
                    dp[i] += dp[i-num];
                }
            }
        }
        return dp[target];
    }

    public int findMaxForm(String[] strs, int m, int n) {
        int len = strs.length;
        int[][] array = new int[len][2];
        for (int i = 0; i < len; i++) {
            int[] arr = proce(strs[i]);
            array[i][0] = arr[0];
            array[i][1] = arr[1];
        }
        int[][] dp = new int[m+1][n+1];


        return dp[m][n];
    }

    private int[] proce(String str) {
        int m = 0 , n =0;
        for (char c : str.toCharArray()) {
            if (c == '0') m++;
            else n++;
        }
        return new int[]{m,n};
    }


    private double ans;

    public double frogPosition(int n, int[][] edges, int t, int target) {
        List<Integer>[] g = new ArrayList[n + 1];
        Arrays.setAll(g, e -> new ArrayList<>());
        g[1].add(0); // 减少额外判断的小技巧
        for (int[] e : edges) {
            int x = e[0], y = e[1];
            g[x].add(y);
            g[y].add(x); // 建树
        }
        dfs(g, target, 1, 0, t, 1);
        return ans;
    }

    private boolean dfs(List<Integer>[] g, int target, int x, int fa, int leftT, long prod) {
        // t 秒后必须在 target（恰好到达，或者 target 是叶子停在原地）
        if (x == target && (leftT == 0 || g[x].size() == 1)) {
            ans = 1.0 / prod;
            return true;
        }
        if (x == target || leftT == 0) return false;
        for (int y : g[x])  // 遍历 x 的儿子 y
            if (y != fa && dfs(g, target, y, x, leftT - 1, prod * (g[x].size() - 1)))
                return true; // 找到 target 就不再递归了
        return false; // 未找到 target
    }
    public String oddString(String[] words) {
        var d = new HashMap<String, List<String>>();
        for (var s : words) {
            int m = s.length();
            var cs = new char[m - 1];
            for (int i = 0; i < m - 1; ++i) {
                cs[i] = (char) (s.charAt(i + 1) - s.charAt(i));
            }
            var t = String.valueOf(cs);
            d.computeIfAbsent(t, k -> new ArrayList<>()).add(s);
        }
        for (var ss : d.values()) {
            if (ss.size() == 1) {
                return ss.get(0);
            }
        }
        return "";

    }
}

}

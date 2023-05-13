package com.qiqi.algorithm;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Stack;

/**
 * @author qiqi
 * @date 2023/5/13 17:45
 */
public class Day01 {

    //与对应附属同时存在的最大正整数

    public int findMaxK(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int length = nums.length;
        Arrays.sort(nums);
        for (int i = length-1; i >= 0; i--) {
            if (set.contains(-nums[i])){
                return nums[i];
            }
        }
        return -1;
    }
    //俄罗斯套娃信封问题

    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes,(a,b)->{return a[0] == b[0]? b[1] - a[1]:a[0]-b[0];});
        int len = envelopes.length;
        int[] tail = new int[len];
        int maxLen = 0;
        for (int i = 0; i < len; i++) {
            int left =0;
            int right = maxLen;
            while(left < right){
                int mid = left + (right - left) / 2;
                if (envelopes[i][1] > tail[mid]){
                    left = mid +1;
                }else{
                    right = mid;
                }
            }
            tail[left] = envelopes[i][1];
            if (right == maxLen) maxLen++;
        }
        return maxLen;
    }

    //最长递增子序列

    public int lengthOfLIS(int[] nums) {
        int len = nums.length;
        int [] tail = new int[len+1];
        int maxLen = 1;
        for (int i = 0; i < len; i++) {
            int left = 1;
            int right = maxLen;
            while(left < right){
                int mid = left + (right-left)/2;
                if (nums[i] > tail[mid]){
                    left = mid+1;
                }else{
                    right = mid;
                }
            }
            tail[left] = nums[i];
            if (maxLen == right) maxLen++;
        }
        return maxLen;
    }


    public static int[] longestObstacleCourseAtEachPosition(int[] obstacles) {
        int[] stack = new int[obstacles.length];
        int top = -1;
        int[] res = new int[obstacles.length];
        for (int i = 0; i < obstacles.length; i++) {
            if (top == -1 || obstacles[i] >= stack[top]) {
                stack[++top] = obstacles[i];
                res[i] = top + 1;
            } else {
                //二分
                int l = 0, r = top, m;
                while (l < r) {
                    m = l + (r - l) / 2;
                    if (stack[m] <= obstacles[i]) {
                        l = m + 1;
                    } else {
                        r = m;
                    }
                }
                stack[r] = obstacles[i];
                res[i] = r + 1;
            }
        }
        return res;
    }

    //最长公共子序列

    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m+1][n+1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i-1) == text2.charAt(j-1))
                    dp[i][j] = dp[i-1][j-1]+1;
                else
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
            }
        }
        return dp[m][n];
    }
    //不相交的线

    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int[][] dp = new int[m+1][n+1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (nums1[i-1] == nums2[j-1]) dp[i][j] = dp[i-1][j-1] + 1;
                else dp[i][j] = Math.max(dp[i][j-1],dp[i-1][j]);
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        int[] arr ={3,1,5,6,4,2};
        longestObstacleCourseAtEachPosition(arr);
    }
}

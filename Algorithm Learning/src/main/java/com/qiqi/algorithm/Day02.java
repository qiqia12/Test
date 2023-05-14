package com.qiqi.algorithm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: Eric
 * @date: 2023/5/14 19:29
 * @version: 1.0
 */
public class Day02 {


    //距离相等的条形码

    public int[] rearrangeBarcodes(int[] barcodes) {
        int len = barcodes.length;
        int index = 0;
        for (int i = 0; i < len - 1; i++) {
            if (barcodes[i] != barcodes[i+1]) continue;
            index = i+2;
            while (index<len && barcodes[i] == barcodes[index]) index++;
            if (index == len) break;
            swap(barcodes,index,i+1);
        }
        if (index < len) return barcodes;
        for (int i = len-1; i >=1; i--) {
            if (barcodes[i] == barcodes[i-1]) continue;
            index = i-2;
            while(index >=0 && barcodes[i] == barcodes[index]) index--;
            swap(barcodes,index,i-1);
        }
        return barcodes;
    }
    public void swap(int[] ar, int i, int j) {
        int t = ar[i];
        ar[i] = ar[j];
        ar[j] = t;
    }

    //最长回文子序列

    public int minInsertions(String s) {
        int m = s.length();
        StringBuilder sb = new StringBuilder(s);
        StringBuilder str = sb.reverse();
        int[][] dp = new int[m+1][m+1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= m; j++) {
                if (s.charAt(i-1) == str.charAt(j-1)) dp[i][j] = dp[i-1][j-1]+1;
                else dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
            }
        }
        return m - dp[m][m];
    }

    //买股票最佳时期  含冷冻期
    public int maxProfit(int[] prices) {
        if (prices.length <2)return 0;
        int len = prices.length;
        //0, 不持股 不是冷冻期   1,持股   2,不持股 是冷冻期
        int[][] dp = new int[len][3];
        dp[0][1] = -prices[0];
        for (int i = 1; i < len; i++) {
            dp[i][0] = Math.max(dp[i-1][1]+prices[i],Math.max(dp[i-1][2],dp[i-1][0]));
            dp[i][1] = Math.max(dp[i-1][0]-prices[i],Math.max(dp[i-1][1],dp[i-1][2]-prices[i]));
            dp[i][2] = dp[i-1][1]+prices[i-1];
        }
        return Math.max(dp[len-1][0],dp[len-1][2]);
    }




}

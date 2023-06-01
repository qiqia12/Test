package com.qiqi.algorithm;

import javax.swing.*;
import java.util.Arrays;

/**
 * @projectName: Test
 * @package: com.qiqi.algorithm
 * @className: Day16
 * @author: Eric
 * @description: TODO
 * @date: 2023/6/1 19:36
 * @version: 1.0
 */
public class Day16 {

    public int maximumTastiness(int[] price, int k) {
        Arrays.sort(price);
        int left = price[0];
        int right = price[price.length-1] - price[0];
        if (k == 2){
            return right;
        }
        int ans = 0;
        while(left <= right){
            int mid = left + (right - left)/2;
            if (chack(price,mid,k)){
                left = mid+1;
                ans = Math.max(ans,mid);
            }else{
                right = mid-1;
            }
        }
        return ans;
    }

    private boolean chack(int[] price, int mid, int k) {
        int num = 1;
        int index = price[0];
        for (int i = 1; i < price.length; i++) {
            if (price[i] - index >= mid){
                num++;
                index = price[i];
            }
        }
        return num>=k;
    }
    public static void main(String[] args) {
        canPlaceFlowers(new int[]{1,0,0,0,0,1},2);
    }
    public static  boolean canPlaceFlowers(int[] flowerbed, int n) {
        int free = 0;
        int result = 0;
        int time = 0;
        boolean flag = flowerbed[0] == 0;
        for (int i = 0; i < flowerbed.length; i++) {
            if (flowerbed[i] == 1){
                time++;
                if (free > 0 && flag){
                    result += free / 2;
                    free = 0;
                    flag = false;
                }else if (free > 0){
                    result += (free-1)/2;
                    free = 0;
                }
            }else{
                free++;
            }
        }
        if (free > 0){
            if (time>0){
                result += free / 2;
            }else{
                result += (free+1) / 2;
            }
        }
        return result>=n;
    }
    public int maximumBags(int[] capacity, int[] rocks, int additionalRocks) {
        int len = capacity.length;
        int[] dp = new int[len];
        for (int i = 0; i < len; i++) {
            dp[i] = capacity[i] - rocks[i];
        }
        Arrays.sort(dp);
        int result = 0;
        for (int i = 0; additionalRocks>0 && i < len; i++) {
            if (dp[i] == 0){
                result++;
            }else{
                if (additionalRocks>=dp[i]){
                    result++;
                    additionalRocks-=dp[i];
                }else{
                    break;
                }
            }
        }
        return result;
    }
    public long putMarbles(int[] weights, int k) {
        if (weights.length == k) return 0;
        return 1l;
    }
}

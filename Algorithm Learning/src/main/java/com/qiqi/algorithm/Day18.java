package com.qiqi.algorithm;

import java.util.Arrays;
import java.util.HashMap;

public class Day18 {

    public static int[] applyOperations(int[] nums) {
        for (int i = 0; i < nums.length-1; i++) {
            if (nums[i] != 0 && nums[i] == nums[i+1]){
                nums[i] = 2 * nums[i];
                nums[i+1] = 0;
            }
        }
        int start = 0;
        while(start<nums.length && nums[start]!= 0){
            start++;
        }
        int end = start+1;
        while(end < nums.length){
            if (end > start && nums[end] != 0){
                nums[start] = nums[end];
                nums[end] = 0;
                while(nums[start]!= 0){
                    start++;
                }
            }
            end++;
        }
        return nums;
    }

    class ListNode {
       int val;
       ListNode next;
       ListNode(int x) {
           val = x;
           next = null;
       }
   }
    public boolean hasCycle(ListNode head) {
        if (head == null ||head.next == null) return false;
        ListNode slow = head;
        ListNode fast = head.next;
        while(slow.next!=null && fast.next!=null && fast.next.next != null){
            if (slow == fast) return true;
            slow = slow.next;
            fast = fast.next.next;
        }
        return false;
    }

    public int countTime(String time) {
        String sub1 = time.substring(0, 2);
        String sub2 = time.substring(3,5);
        int result = 1;
        if (("??").equals(sub1)){
            result = result * 24;
        }else if (sub1.charAt(0) == '?'){
            int num = sub1.charAt(1) -'0';
            if (num<4){
                result = result * 3;
            }else{
                result *= 2;
            }
        }else if (sub1.charAt(1) == '?'){
            int num = sub1.charAt(0) - '0';
            if (num == 2){
                result *= 4;
            }else{
                result *= 10;
            }
        }

        if (("??").equals(sub2)){
            result = result * 60;
        }else if (sub2.charAt(0) == '?'){
            result = result * 6;
        }else if (sub2.charAt(1) == '?'){
            result = result *10;
        }

        return result;
    }

    public int numPairsDivisibleBy60(int[] time) {
        int result = 0;
        for (int i = 0; i < time.length; i++) {
            for (int j = i+1; j < time.length; j++) {
                if ((time[i]+time[j])%60 == 0) result++;
            }
        }
        return result;
    }

    public int numPairsDivisibleBy601(int[] time) {
        int len = time.length;
        int[] dp = new int[len];
        for (int i = len-1; i >=0 ; i--) {
            int num = 0;
            for (int j = i+1; j <= len; j++) {
                if ((time[i]+time[j])%60 == 0){
                    num++;
                }
                if (time[i] == time[j]){
                    num+=dp[j];
                    break;
                }
            }
            dp[i] = num;
        }
        return Arrays.stream(dp).sum();
    }

    public static void main(String[] args) {
        applyOperations(new int[]{847,847,0,0,0,399,416,416,879,879,206,206,206,272});
    }
}

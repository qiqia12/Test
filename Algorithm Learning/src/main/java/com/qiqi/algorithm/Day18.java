package com.qiqi.algorithm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

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


    public int equalPairs(int[][] grid) {
        int n = grid.length;
        int  res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][0] == grid[0][j]){
                    int k = 1;
                    for (; k < n; k++) {
                        if (grid[i][k] != grid[k][j]){
                            break;
                        }
                    }
                    if (k == n)
                        res++;
                }
            }
        }
        return res;
    }

    //X  的平方根

    public int mySqrt(int x) {
        return (int)Math.sqrt(x);
    }

    public static int mySqrt1(int x) {
        if (x < 2) return x;
        int left = 0,right = x;
        while(left < right){
            int mid = left + (right - left)/2;
            System.out.println(mid);
            if (mid>x/mid){
                right = mid-1;
            }else if (mid  < x/ mid){
                left = mid+1;
            }else{
                return mid;
            }
        }
        return left * left <x?left:left-1;
    }
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        PriorityQueue<Integer> queue1 = new PriorityQueue<>();
        PriorityQueue<Integer> queue2 = new PriorityQueue<>();
        for (int i = 0; i < m; i++) {
            queue1.add(nums1[i]);
        }
        for (int i = 0; i < n; i++) {
            queue2.add(nums2[i]);
        }
        for (int i = 0; i < nums1.length; i++) {
            if (queue1.isEmpty()){
                nums1[i] = queue1.poll();
            }else if (queue2.isEmpty()){
                nums1[i] = queue2.poll();
            }else{
                int num = 0;
                if (queue1.peek()<queue2.peek()){
                    num = queue1.poll();
                }else{
                    num = queue2.poll();
                }
                nums1[i] = num;
            }
        }
    }

    //冒泡排序

    public int[] sortArray(int[] nums) {
        for (int i = 0; i <nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                if (nums[i]>nums[j]){
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                }
            }
        }
        return nums;
    }

    //选择排序

    public int[] sortArray1(int[] nums){
        for (int i = 0; i < nums.length; i++) {
            int num = i;
            for (int j = i+1; j < nums.length; j++) {
               if (nums[j]< nums[num]){
                   num = j;
               }
            }
            int temp = nums[num];
            nums[num] = nums[i];
            nums[i] = temp;
        }
        return nums;
    }
    //归并排序

    public static int[] sortArray2(int[] nums){
        mergeArray(nums,0,nums.length-1);
        return nums;
    }

    private static void mergeArray(int[] nums, int left, int right) {
        if (left == right) return;
        int mid = left +(right - left)/2;
        mergeArray(nums,left,mid);
        mergeArray(nums,mid+1,right);
        combin(nums,left,mid,right);
    }

    private static void combin(int[] nums, int left, int mid, int right) {
        int len = right - left +1;
        int[] help = new int[len];
        int startL = left;
        int startR = mid+1;
        for (int i = 0; i < len; i++) {
            if (startL>mid){
               help[i] = nums[startR++];
            }else if (startR > right){
                help[i] = nums[startL++];
            }else if (nums[startL] > nums[startR]){
                help[i] = nums[startR++];
            }else{
                help[i] = nums[startL++];
            }
        }
        for (int i = 0; i < len; i++) {
            nums[i+left] = help[i];
        }
    }

    //快速排序


    public static void main(String[] args) {
        System.out.println(sortArray4(new int[]{}));
    }

    public static int[] sortArray4(int[] nums) {
        if (nums.length < 1) return null;
        quickSort(nums,0,nums.length-1);
        return nums;
    }

    private static void quickSort(int[] nums, int left, int right) {
        if (left >= right) return;
        int sign = nums[left];
        int index = left;
        int l = left;
        int r = right+1;
        while(l<r){
            if (nums[l]>sign){
                int temp = nums[--r];
                nums[r] = nums[l];
                nums[l] = temp;
            }else if (nums[l] == sign){
                l++;
            } else{
                nums[index] = nums[l];
                nums[l] = sign;
                index = l;
                l++;
            }
        }
        quickSort(nums,left,index);
        quickSort(nums,index+1,right);
    }
}

package com.qiqi.problem2;

import java.util.*;

/**
 * @projectName: Test
 * @package: com.qiqi.problem2
 * @className: Day03
 * @author: Eric
 * @description: TODO
 * @date: 2023/6/11 20:13
 * @version: 1.0
 */
public class Day03  {
    public static  class ListNode {
       int val;
       ListNode next;
       ListNode() {}
       ListNode(int val) { this.val = val; }
       ListNode(int val, ListNode next) { this.val = val; this.next = next; }
   }

    public static ListNode removeZeroSumSublists(ListNode head) {
        ListNode H = new ListNode();
        ListNode h = H;
        H.next = head;
        while(h!=null){
            ListNode cur = h.next;
            int sum = 0;
            while(cur!=null){
                sum+=cur.val;
                if (sum == 0){
                    h.next = cur.next;
                }
                cur = cur.next;
            }
            h = h.next;
        }
        return H.next;
    }

    public static int[] distributeCandies(int candies, int num_people) {
        int[] result = new int[num_people];
        int index =0;
        while(candies>0){
            if (index+1 > candies){
                result[index%num_people] += candies;
                candies =0;
            }else{
                result[index%num_people] += index+1;
                candies -= index+1;
                index++;
            }

        }
        return result;
    }

    private int next(int index,int sum) {

        return index %sum;
    }


    class TreeAncestor {
        int[][] pa;

        public TreeAncestor(int n, int[] parent) {
            int m = 32 - Integer.numberOfLeadingZeros(n); // n 的二进制长度

            pa = new int[n][m];

            for (int i = 0; i < n; i++)

                pa[i][0] = parent[i];

            for (int i = 0; i < m - 1; i++) {

                for (int x = 0; x < n; x++) {

                    int p = pa[x][i];

                    pa[x][i + 1] = p < 0 ? -1 : pa[p][i];

                }

            }
        }

        public int getKthAncestor(int node, int k) {
            int m = 32 - Integer.numberOfLeadingZeros(k); // k 的二进制长度

            for (int i = 0; i < m; i++) {

                if (((k >> i) & 1) > 0) { // k 的二进制从低到高第 i 位是 1

                    node = pa[node][i];

                    if (node < 0) break;

                }

            }
            return node;
        }
        public int unequalTriplets(int[] nums) {
            HashMap<Integer,Integer> map = new HashMap<>();
            for (int num : nums) {
                int n = map.getOrDefault(num,0)+1;
                map.put(num,n);
            }
            if (map.size()<3)return 0;
            int reslut = 0;
            int a = 0;
            int n = nums.length;
            for (Integer b : map.values()) {
                int c = n - a - b;

                reslut += a * b * c;

                a += b;
            }
            return reslut;
        }
    }
    public static int numTimesAllBlue(int[] flips) {
        int[] dp = new int[flips.length+1];
        dp[0] =1;
        int low = 0;
        int result = 0;
        int max = 0;
        for (int flip : flips) {
            dp[flip] = 1;
            while(low < flips.length-1 && dp[low+1] ==1){
                low++;
            }
            max = Math.max(max,flip);
            if (max == low){
                result++;
            }
        }
        while(low < flips.length && dp[low]==1){
            low++;
        }
        if (max == low){
            result++;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(numTimesAllBlue(new int[]{1, 2, 3, 4, 5, 6, 7}));
        System.out.println(numTimesAllBlue(new int[]{3, 2, 4, 1, 5}));
    }
}

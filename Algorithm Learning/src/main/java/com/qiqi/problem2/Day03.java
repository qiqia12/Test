package com.qiqi.problem2;

import java.util.Arrays;

/**
 * @projectName: Test
 * @package: com.qiqi.problem2
 * @className: Day03
 * @author: Eric
 * @description: TODO
 * @date: 2023/6/11 20:13
 * @version: 1.0
 */
public class Day03 {
    public static  class ListNode {
       int val;
       ListNode next;
       ListNode() {}
       ListNode(int val) { this.val = val; }
       ListNode(int val, ListNode next) { this.val = val; this.next = next; }
   }

    public static void main(String[] args) {
        distributeCandies(10,3);
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
    }
}

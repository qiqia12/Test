package com.qiqi.algorithm;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * @projectName: Test
 * @package: com.qiqi.algorithm
 * @className: Day4
 * @author: Eric
 * @description: TODO
 * @date: 2023/5/28 19:47
 * @version: 1.0
 */
public class Day4 {


    public int kthSmallest(int[][] mat, int k) {
        int[] a= new int[]{0};
        for (int[] ints : mat) {
            int[] b = new int[ints.length * a.length];
            int index = 0;
            for (int i : a) {
                for (int anInt : ints) {
                    b[index++] = i + anInt;
                }
            }
            if (b.length > k)
                b = Arrays.copyOfRange(b,0,k);
            a= b;
        }
        return a[k-1];
    }

    public class ListNode {
       int val;
       ListNode next;
       ListNode() {}
       ListNode(int val) { this.val = val; }
       ListNode(int val, ListNode next) { this.val = val; this.next = next; }
   }

    public ListNode reverseKGroup(ListNode head, int k) {
        Stack<ListNode> stack = new Stack<>();
        ListNode h = new ListNode();
        h.next = head;
        ListNode cur = head;
        ListNode hh = h;
        while(cur != null){
            stack.add(cur);
            cur = cur.next;
            if (stack.size() == k){
                while(!stack.isEmpty()){
                    stack.peek().next = null;
                    hh.next = stack.pop();
                    hh = hh.next;
                }
                hh.next = cur;
            }
        }
        return h.next;
    }

    public static void test(){
        Scanner cs
    }



}

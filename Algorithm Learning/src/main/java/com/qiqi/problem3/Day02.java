package com.qiqi.problem3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @projectName: Test
 * @package: com.qiqi.problem3
 * @className: Day02
 * @author: Eric
 * @description: TODO
 * @date: 2023/10/9 19:23
 * @version: 1.0
 */
public class Day02 {

    public static void test(String str){

        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        StringBuilder sb = new StringBuilder();
        sb.append(chars);
        System.out.println(sb);
    }




    public class ListNode {
     int val;
     ListNode next = null;
     public ListNode(int val) {
       this.val = val;
     }
   }
    public ListNode deleteNode (ListNode head, int val) {
        // write code here
        ListNode Node = new ListNode(0);
        Node.next = head;
        ListNode cur = head;
        ListNode pre = Node;
        while(cur.val!=val){
            cur = cur.next;
            pre = pre.next;
        }
        pre.next = cur.next;
        return Node.next;
    }


    public static long countPairs (ArrayList<Integer> A, int n, int sum) {
        // write code here
        List<Integer> collect = A.stream().sorted().collect(Collectors.toList());
        long result = 0l;
        for (int i = 0; i < n-1; i++) {
            int start = collect.get(i);
            if (start >= sum) break;
            int end = sum - start;
            //二分查找 是否存在end
            result += process(collect,i+1,n-1,end);
        }
        return result;
    }

    private static long process(List<Integer> collect, int left, int right, int num) {
        if (left == right){
            return collect.get(left) == num ? 1:0;
        }
        if (left + 1 == right){
            return (collect.get(left) == num || collect.get(right) == num)?1:0;
        }
        int mid = left + (right - left)/2;
        if (collect.get(mid) == num){
            int l = mid;
            int r = mid;
            while(l>0 && collect.get(l) == num){
                l--;
            }
            while(r < collect.size() &&collect.get(r) == num){
                r++;
            }
            return r-l-1;
        }else if (collect.get(mid) < num){
            return process(collect,mid,right,num);
        }else{
            return process(collect,left,mid-1,num);
        }
    }

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        System.out.println(countPairs(list, 5, 6));

    }
}

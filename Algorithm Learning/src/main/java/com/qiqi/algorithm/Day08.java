package com.qiqi.algorithm;

import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * @projectName: Test
 * @package: com.qiqi.algorithm
 * @className: Day08
 * @author: Eric
 * @description: TODO
 * @date: 2023/5/20 17:32
 * @version: 1.0
 */
public class Day08 {
    public class TreeNode {
       int val;
       TreeNode left;
       TreeNode right;
       TreeNode() {}
       TreeNode(int val) { this.val = val; }
       TreeNode(int val, TreeNode left, TreeNode right) {
           this.val = val;
           this.left = left;
           this.right = right;
       }
   }

   public class Node1 {
        boolean flag;
        int value;
        int min;

        public Node1(boolean f, int v, int min){
            this.flag = f;
            this.value = v;
            this.min = min;
        }
   }
    PriorityQueue<Integer> queue;
    public int maxSumBST1(TreeNode root) {
        queue = new PriorityQueue<>((a,b)->b-a);
        process(root);
        return Math.max(0,queue.peek());
    }

    private Node1 process(TreeNode root) {
        if (root == null) return null;
        Node1 left = process(root.left);
        Node1 right = process(root.right);
        if (left == null && right == null){
            queue.add(root.val);
            return new Node1(true, root.val, root.val);
        }
        if (left == null){
            if (right.flag && root.val<right.value && root.val < right.min){
                int value = root.val + right.value;
                queue.add(value);
                return new Node1(true,value, right.min);
            }else{
                return new Node1(false,right.value, right.min);
            }
        }
        if (right == null){
            if (left.flag && root.val> left.value){
                int value = root.val+ left.value;
                queue.add(value);
                return new Node1(true,value, left.min);
            }else{
                return new Node1(false,left.value, left.min);
            }
        }
        if (!left.flag && ! right.flag){
            if (left.value > right.value){
                return new Node1(false, left.value, left.min);
            }else{
                return new Node1(false, right.value, right.min);
            }
        }
        boolean f = left.flag && right.flag;
        if (!f){
            return new Node1(false,Math.max(left.value,right.value),Math.min(left.min, right.min));
        }
        f = f && root.val > left.value && root.val < right.value && root.val < right.min;
        if (f){
            int value = root.val+left.value + right.value;
            queue.add(value);
            return new Node1(f,value,left.min);
        }else{
            return new Node1(false,Math.max(left.value,right.value), left.min);
        }
    }


    public class  Node{
        int max;
        int min;
        int res;
        boolean flag;
        public Node(boolean f,int n,int m,int r){
            flag = f;
            min = n;
            max = m;
            res = r;
        }
    }




}

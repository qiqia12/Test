package com.qiqi.algorithm;

import java.util.*;

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
    //统计范围内的元音字符串数
    HashSet<Character> set;
    public int[] vowelStrings(String[] words, int[][] queries) {
        set = new HashSet<>();
        set.add('a');
        set.add('e');
        set.add('i');
        set.add('o');
        set.add('u');
        int len = queries.length;
        int [] arr = new int[len+1];
        for (int i = 1; i <= len; i++) {
            arr[i] = check(words[i-1])+arr[i-1];
        }

        int [] result = new int[len];
        for (int i = 0; i < len; i++) {
            int start = queries[i][0];
            int end = queries[i][1];
            result[i] = arr[end] - arr[start-1];
        }
        return result;
    }

    private int check(String word) {
        if (set.contains(word.charAt(0)) && set.contains(word.charAt(word.length()-1))){
            return 1;
        }
        return 0;
    }

    private int check(int start, int end, int[] arr) {
        int res = 0;
        for (int i = start; i <= end; i++) {
            if (arr[i] == 1)
                res++;
        }
        return res;
    }



    class Solution {
        public int[] vowelStrings(String[] words, int[][] queries) {
            Set<Character> vowels = new HashSet<>();
            int n = words.length;
            int[] s = new int[n + 1];
            for (int i = 0; i < n; ++i) {
                char a = words[i].charAt(0), b = words[i].charAt(words[i].length() - 1);
                s[i + 1] = s[i] + (vowels.contains(a) && vowels.contains(b) ? 1 : 0);
            }
            int m = queries.length;
            int[] ans = new int[m];
            for (int i = 0; i < m; ++i) {
                int l = queries[i][0], r = queries[i][1];
                ans[i] = s[r + 1] - s[l];
            }
            return ans;
        }
    }

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

   //二叉树的层序遍历

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (root == null) return new LinkedList<>();
        List<List<Integer>> res = new LinkedList<>();
        Stack<List<Integer>> stack = new Stack<>();
        TreeNode nextEnd = root;
        TreeNode curNext = root;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        List<Integer> now = new LinkedList<>();
        while(!queue.isEmpty()){
            TreeNode poll = queue.poll();
            now.add(poll.val);
            if (poll.left!=null){
                queue.add(poll.left);
                nextEnd = poll.left;
            }
            if (poll.right != null){
                queue.add(poll.right);
                nextEnd = poll.right;
            }
            if (poll == curNext){
                curNext = nextEnd;
                stack.add(now);
                now = new LinkedList<Integer>();
            }
        }
        while(!stack.isEmpty()){
            res.add(stack.pop());
        }
        return res;
    }

    class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
    HashMap<Node,Node> map = new HashMap<>();
    public Node cloneGraph(Node node) {
        Node node1 = Clone(node);
        node1.neighbors = cloneNeighbors(node);
        return node1;
    }

    private List<Node> cloneNeighbors(Node node) {
        List<Node> res = new LinkedList<>();
        for (Node neighbor : node.neighbors) {
            Node clone = null;
            if (map.containsKey(neighbor)){
                clone = map.get(neighbor);
            }else{
                clone = Clone(neighbor);
            }
            res.add(clone);
            clone.neighbors = cloneNeighbors(neighbor);
        }
        return res;
    }

    private Node Clone(Node node) {
        Node node1 = new Node(node.val);
        map.put(node,node1);
        return node1;
    }


}

package com.qiqi.algorithm;

import java.util.*;
import java.util.stream.Stream;

/**
 * @projectName: Test
 * @package: com.qiqi.algorithm
 * @className: Day07
 * @author: Eric
 * @description: TODO
 * @date: 2023/5/19 19:46
 * @version: 1.0
 */
public class Day07 {

    //排列 组合
    public int numTilePossibilities(String tiles) {
        char[] chars = tiles.toCharArray();
        int len = chars.length;
        ArrayList<Character> list = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            list.add(chars[i]);
        }
        return process(list);
    }

    private int process(ArrayList<Character> list) {
        if (list.size() == 1) return 1;
        HashSet<Character> set = new HashSet<>();
        int result = 0;
        int len = list.size();
        for (int i = 0; i < len; i++) {
            if (set.add(list.get(i))){
                list.remove(i);
                result += process(list);
            }
        }
        return result;
    }


    public static int numTilePossibilities1(String tiles) {
        int len = tiles.length();
        ArrayList<Character> list = new ArrayList<>();
        HashSet<String> result = new HashSet<>();
        for (char c : tiles.toCharArray()) {
            list.add(c);
        }
        HashSet<Character> set = new HashSet<>();
        for (int i = 0; i < len; i++) {
            Character character = list.get(i);
            if (set.add(character)){
                result.add(character+"");
                process1(new ArrayList<>(list),""+character,result,i);
            }
        }
        return result.size();
    }

    private static void process1(ArrayList<Character> list,String str,HashSet<String> result,int pos) {
        list.remove(pos);
        if (list.isEmpty())
            return;
        HashSet<Character> set = new HashSet<>();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Character character = list.get(i);
            if (set.add(character)){
                result.add(str+character);
                process1(new ArrayList<>(list),str+character,result,i);
            }
        }
    }

    public static void main(String[] args) {
        numTilePossibilities1("AAB");
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
    public List<TreeNode> generateTrees(int n) {
        return pro(1,n);
    }

    private List<TreeNode> pro(int left,int right) {
        List<TreeNode> result = new LinkedList<>();
        if (left > right){
            result.add(null);
            return result;
        }
        for (int i = left; i <= right; i++) {
            List<TreeNode> leftTree = pro(left,i-1);
            List<TreeNode> rightTree = pro(i+1,right);
            for (TreeNode leftNode : leftTree) {
                for (TreeNode rightNode : rightTree) {
                    TreeNode node = new TreeNode(i);
                    node.left = leftNode;
                    node.right = rightNode;
                    result.add(node);
                }
            }
        }
        return result;
    }

    //打家劫舍
    public int rob(TreeNode root) {
        if (root == null) return 0;
        int val = root.val;
        if (root.right!=null){
            val += rob(root.right.right)+rob(root.right.left);
        }
        if (root.left != null){
            val += rob(root.left.left) +rob(root.left.right);
        }
        int mon = rob(root.right) + rob(root.left);
        return Math.max(val,mon);
    }

}

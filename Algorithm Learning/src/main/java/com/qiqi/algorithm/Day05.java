package com.qiqi.algorithm;

import java.util.LinkedList;
import java.util.List;

/**
 * @projectName: Test
 * @package: com.qiqi.algorithm
 * @className: Day05
 * @author: Eric
 * @description: TODO
 * @date: 2023/5/17 19:50
 * @version: 1.0
 */
public class Day05 {

    public static boolean haveConflict(String[] event1, String[] event2) {
        int [] e1 = process(event1);
        int [] e2 = process(event2);
        if (e1[0]<e2[0]){
            return e2[0] <= e1[1];
        }else{
            return e1[0] <= e2[1];
        }
    }

    private static int[] process(String[] event2) {
        event2[0] = event2[0].replace(":","");
        event2[1] = event2[1].replace(":","");
        return new int[]{Integer.parseInt(event2[0]), Integer.parseInt(event2[1])};
    }

    public static void main(String[] args) {
        haveConflict(new String[]{"01:15","02:00"},new String[]{"02:00","03:00"});
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
        List<TreeNode> list = new LinkedList<>();

    }
}

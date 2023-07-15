package com.qiqi.problem2;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @projectName: Test
 * @package: com.qiqi.problem2
 * @className: Day06
 * @author: Eric
 * @description: TODO
 * @date: 2023/7/11 22:49
 * @version: 1.0
 */
public class Day06 {

    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;

        long[] f = new long[n + 1];

        long[] g = new long[n + 1];

        for (int i = 1; i <= n; ++i) {

            f[i] = Math.max(g[i - 1] - nums[i - 1], f[i - 1]);

            g[i] = Math.max(f[i - 1] + nums[i - 1], g[i - 1]);

        }

        return Math.max(f[n], g[n]);
    }
    public int alternateDigitSum(int n) {
        String s = String.valueOf(n);
        int result = 0;
        for (int i = 1; i <= s.length(); i++) {
            result += i%2==1?s.charAt(i-1)-'0':-(s.charAt(i-1)-'0');
        }
        return result;
    }
    
    //荷兰国旗问题

    public static void test(int[] arr,int num){
        int right = arr.length;
        int index = 0;
        while( index<right){
            if (arr[index]<num){
                index++;
            }else if (arr[index] > num){
                swap(--right,index,arr);
            }
        }
    }

    private static void swap(int i, int index, int[] arr) {
        int num = arr[i];
        arr[i] = arr[index];
        arr[index] = num;
    }
    private static void swap( int[] arr,int i, int index) {
        int num = arr[i];
        arr[i] = arr[index];
        arr[index] = num;
    }


    //快排

    public static void quickSort(int[] arr){

    }

    //下降路径最小和
    public static int minFallingPathSum(int[][] matrix) {
        int len = matrix.length;
        int[][] dp = new int[len][len];
        for (int i = 0; i < len; i++) {
            dp[0][i] = matrix[0][i];
        }
        for (int i = 1; i <len ; i++) {
            for (int j = 0; j < len; j++) {
                dp[i][j] = matrix[i][j] + fun(matrix,i,j,dp);
            }
        }
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < len; i++) {
            result = Math.min(result,dp[len-1][i]);
        }
        return result;
    }

    private static int fun(int[][] matrix, int i, int j,int[][] dp) {
        if (j == 0){
            return Math.min(dp[i-1][j],dp[i-1][j+1]);
        }
        else if (j == matrix.length-1){
            return Math.min(dp[i-1][j],dp[i-1][j-1]);
        }
        return Math.min(dp[i-1][j],Math.min(dp[i-1][j+1],dp[i-1][j-1]));
    }


    public static int[] netherLandsFlag(int[] arr){
        int left = -1;
        int right = arr.length-1;
        int index = 0;
        int num = arr[arr.length-1];
        while(index<right){
            if (arr[index]<num){
                swap(++left,index++,arr);
            }else if (arr[index] == num){
                index++;
            }else{
                swap(--right,index,arr);
            }
        }
        swap(right,arr.length-1,arr);
        return new int[]{left+1,right};
    }


    public static void main(String[] args) {
        int[] ints = {12, 23, 3, 43, 52, 3, 5634, 6, 34, 5, 45, 46, 4, 67, 785, 53, 23, 4234, 23};
        quickSort3(ints);
        for (int anInt : ints) {
            System.out.print(anInt);
            System.out.print(" ");
        }

    }
    public static void quickSort3(int [] arr){
        if (arr == null ||arr.length < 2 ) return;
        process(arr,0,arr.length-1);
    }

    private static void process(int[] arr, int l, int r) {
        if (l >= r) return;
        swap(arr,r,l+(int)(Math.random()*(r-l+1)));
        int[] M = partation(arr,l,r);
        process(arr,l,M[0]-1);
        process(arr,M[1]+1,r);
    }

    private static int[] partation(int[] arr, int l, int r) {
        if (arr.length<2 || arr == null) {
            return new int[]{-1,-1};
        }
        if (l == r){
            return new int[]{l,r};
        }
        int min = l-1;
        int max = r;
        int index = l;
        while(index <= max){
            if (arr[index] == arr[r]){
                index++;
            }else if (arr[index] < arr[r]){
                swap(arr,index++,++min);
            }else{
                swap(arr,index,--max);
            }
        }
        swap(arr,max,r);

        return new int[]{min+1,max};
    }

    public static void  quick3(int [] arr){
        if (arr.length<2||arr==null){
            return;
        }
        proce2(arr,0,arr.length-1);
    }

    private static void proce2(int[] arr, int l, int r) {
        if (l>=r){
            return;
        }
        swap(arr,l+(int)(Math.random()*(r-l+1)),r);
        int[] M = partition2(arr,l,r);
        proce2(arr,l,M[0]-1);
        proce2(arr,M[1]+1,r);
    }
    private static int[] partition2(int[] arr, int l, int r) {
        if (arr.length<2||arr==null){
            return new int[] {-1,-1};
        }

        if (l==r){
            return new int[] {l,r};
        }
        int Min = l-1;
        int Max = r;
        int index=l;
        while(index<=Max){
            if (arr[index] == arr[r]){
                index++;
            }else if (arr[index]<arr[r]){
                swap(arr,index++,++Min);
            }else{
                swap(arr,index,--Max);
            }
        }
        swap(arr,Max,r);
        return new int[] {Min+1,Max};
    }

    //双检锁
    public class Singleton{
        private volatile  Singleton singleton;
        private  Singleton(){};
        public  Singleton getSingleton(){
            if (singleton == null){
                synchronized (Singleton.class){
                    if (singleton == null){
                        singleton = new Singleton();
                    }
                }
            }
            return singleton;
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
    private int ans;
    public int distributeCoins(TreeNode root) {

        dfs(root);

        return ans;

    }



    private int[] dfs(TreeNode node) {

        if (node == null)

            return new int[]{0, 0};

        int[] left = dfs(node.left);

        int[] right = dfs(node.right);

        int coins = left[0] + right[0] + node.val; // 子树硬币个数

        int nodes = left[1] + right[1] + 1; // 子树节点数

        ans += Math.abs(coins - nodes);

        return new int[]{coins, nodes};

    }
    List<List<Integer>> result = new LinkedList<>();
    List<String> strList = new LinkedList<>();
    public List<List<Integer>> fourSum(int[] nums, int target) {

        for (int i = 0; i < nums.length - 3; i++) {
            List<Integer> integers = new LinkedList<Integer>();
            integers.add(nums[i]);
            proc(integers,nums[i],target,i+1,nums);
        }

        return result;
    }

    private void proc(List<Integer> lists,int sum, int target,int index,int[] nums) {
        if (sum == target && lists.size() == 4){
            lists.sort((a,b)-> a-b);
            String str = func(lists);
            if (!strList.contains(str)){
                result.add(lists);
                strList.add(str);
            }
            return;
        }
        if ((lists.size() == 4 && sum!=target) || index == nums.length ){
            return;
        }
        //不选当前数字
        proc(new LinkedList<>(lists),sum,target,index+1,nums);
        //选择当前数字
        lists.add(nums[index]);
        proc(lists,sum+nums[index],target,index+1,nums);

    }

    private String func(List<Integer> lists) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Integer list : lists) {
            stringBuilder.append(list);
        }
        return stringBuilder.toString();
    }

}

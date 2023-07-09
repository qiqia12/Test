package com.qiqi.problem2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @projectName: Test
 * @package: com.qiqi.problem2
 * @className: Day04
 * @author: Eric
 * @description: TODO
 * @date: 2023/6/18 20:25
 * @version: 1.0
 */
public class Day04 {
    class Solution {

        public int closedIsland(int[][] grid) {
            int m = grid.length, n = grid[0].length;
            if (m < 3 || n < 3) return 0;
            for (int i = 0; i < m; i++) {
                int step = i == 0 || i == m - 1 ? 1 : n - 1;
                for (int j = 0; j < n; j += step)
                    dfs(grid, i, j);
            }

            int ans = 0;
            for (int i = 1; i < m - 1; i++) {
                for (int j = 1; j < n - 1; j++) {
                    if (grid[i][j] == 0) { // 从没有访问过的 0 出发
                        ans++; // 一定是封闭岛屿
                        dfs(grid, i, j);
                    }
                }
            }
            return ans;
        }



        private void dfs(int[][] grid, int x, int y) {
            if (x < 0 || x >= grid.length || y < 0 || y >= grid[x].length || grid[x][y] != 0)
                return;
            grid[x][y] = 1; // 标记 (x,y) 被访问，避免重复访问
            dfs(grid, x - 1, y);
            dfs(grid, x + 1, y);
            dfs(grid, x, y - 1);
            dfs(grid, x, y + 1);

        }
    }
    public int maxSumDivThree(int[] nums) {
        int n = nums.length;

        int[][] f = new int[n + 1][3];

        f[0][1] = f[0][2] = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++)

            for (int j = 0; j < 3; j++)

                f[i + 1][j] = Math.max(f[i][j], f[i][(j + nums[i]) % 3] + nums[i]);

        return f[n][0];
    }
    public int[] pondSizes(int[][] land) {
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < land.length; i++) {
            for (int j = 0; j < land[0].length; j++) {
                if (land[i][j] == 0){
                    list.add(process(land,i,j));
                }
            }
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        Arrays.sort(res);
        return res;
    }

    private int process(int[][] land, int i, int j) {
        if (i<0 || i>land.length-1 || j<0 || j>land[0].length) return 0;
        if (land[i][j]!=0) return 0;
        land[i][j] = 1;
        return 1+process(land,i-1,j)+process(land, i+1, j)+process(land, i, j-1)+process(land, i, j+1)
                +process(land,i-1,j+1)+process(land, i+1, j-1)+process(land, i+1, j+1)+process(land, i-1, j-1);
    }
    public int maximumValue(String[] strs) {
        int result = 0;
        for (String str : strs) {
            int num = 0;
            try{
                 num = Integer.parseInt(str);
            }catch (Exception e){
                num = str.length();
            }
            result = Math.max(num,result);
        }
        return result;
    }
    private int m;

    private int mx;

    private int[] f;

    private int[][] g;

    private int[][] bits;

    private int[] ix;

    private int[] ex;

    private Integer[][][][] memo;

    private final int[][] h = {{0, 0, 0}, {0, -60, -10}, {0, -10, 40}};



    public int getMaxGridHappiness(int m, int n, int introvertsCount, int extrovertsCount) {

        this.m = m;

        mx = (int) Math.pow(3, n);

        f = new int[mx];

        g = new int[mx][mx];

        bits = new int[mx][n];

        ix = new int[mx];

        ex = new int[mx];

        memo = new Integer[m][mx][introvertsCount + 1][extrovertsCount + 1];

        for (int i = 0; i < mx; ++i) {

            int mask = i;

            for (int j = 0; j < n; ++j) {

                int x = mask % 3;

                mask /= 3;

                bits[i][j] = x;

                if (x == 1) {

                    ix[i]++;

                    f[i] += 120;

                } else if (x == 2) {

                    ex[i]++;

                    f[i] += 40;

                }

                if (j > 0) {

                    f[i] += h[x][bits[i][j - 1]];

                }

            }

        }

        for (int i = 0; i < mx; ++i) {

            for (int j = 0; j < mx; ++j) {

                for (int k = 0; k < n; ++k) {

                    g[i][j] += h[bits[i][k]][bits[j][k]];

                }

            }

        }

        return dfs(0, 0, introvertsCount, extrovertsCount);

    }



    private int dfs(int i, int pre, int ic, int ec) {

        if (i == m || (ic == 0 && ec == 0)) {

            return 0;

        }

        if (memo[i][pre][ic][ec] != null) {

            return memo[i][pre][ic][ec];

        }

        int ans = 0;

        for (int cur = 0; cur < mx; ++cur) {

            if (ix[cur] <= ic && ex[cur] <= ec) {

                ans = Math.max(ans, f[cur] + g[pre][cur] + dfs(i + 1, cur, ic - ix[cur], ec - ex[cur]));

            }

        }

        return memo[i][pre][ic][ec] = ans;

    }


    public static boolean checkOverlap(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                if (pro(radius,xCenter,yCenter,i,j)){
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean pro(int radius, int xCenter, int yCenter, int i, int j) {

        return radius > Math.sqrt(Math.pow(Math.abs(xCenter-i),2)+Math.pow(Math.abs(yCenter-j),2));
    }

    public static int maximumSum(int[] arr) {
        int ans = Integer.MIN_VALUE / 2, f0 = ans, f1 = ans;

        for (int x : arr) {

            f1 = Math.max(f1 + x, f0);

            f0 = Math.max(f0, 0) + x;

            ans = Math.max(ans, Math.max(f0, f1));

        }

        return ans;

    }
    public static  void sort(int[] arr){
        if (arr==null || arr.length<2){
            return;
        }
        proc1(arr,0,arr.length-1);
    }

    private static void proc1(int[] arr, int start, int end) {
        if (start == end) return;
        int mid = start + (end - start) /2;
        proc1(arr,start,mid);
        proc1(arr,mid+1,end);
        merge(arr,start,mid,end);
    }

    private static void merge(int[] arr, int start, int mid, int end) {
        int[] help = new int[end - start+1];
        int i = 0;
        int left = start;
        int right = mid+1;
        while(left <= mid && right <= end){
            help[i++] = arr[left] < arr[right] ? arr[left++]:arr[right++];
        }
        while(left <= mid){
            help[i++] = arr[left++];
        }
        while(right <= end){
            help[i++] = arr[right++];
        }
        for (int j = 0; j < help.length ;j++){
            arr[start+j] = help[j];
        }
    }

    public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode();
        boolean flag = (l1.val + l2.val)/10 > 0;
        head.val = (l1.val + l2.val)%10;
        l1 = l1.next;
        l2 = l2.next;
        ListNode next = head;
        while(l1!=null && l2!=null){
            ListNode cur = new ListNode();
            cur.val = (flag?1:0) +l1.val + l2.val;
            flag = cur.val/10 > 0;
            cur.val = cur.val%10;
            next.next = cur;
            next = cur;
            l1 = l1.next;
            l2 = l2.next;
        }
        while (l1 != null){
            l1.val += flag?1:0;
            flag = l1.val/10>0;
            l1.val = l1.val%10;
            l1 = l1.next;
        }


        if (l2!=null){
            next.next = l2;
            while (l2 != null){
                l2.val += flag?1:0;
                flag = l2.val/10>0;
                l2.val = l2.val%10;
                l2 = l2.next;
            }
        }
        if (flag){
            ListNode node = new ListNode(1);
            next.next = node;
        }
        return head;

    }
    public int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length-1;
        while(numbers[left] + numbers[right] != target && left < right){
            int flag = numbers[left] + numbers[right]>target?right--:left++;

        }
        return new int[]{left,right};
    }

    public static List<String> restoreIpAddresses(String s) {
        List<String> result = new LinkedList<>();
        if (s.length()>12||s.length()<4) return result;
        StringBuilder str = new StringBuilder();
        StringBuilder string = new StringBuilder();
        String substring = s.substring(0, 1);
        function(result,s.substring(1),str.append(substring),string.append(substring),0);
        return result;
    }

    private static void function(List<String> result, String s, StringBuilder str,StringBuilder string,int time) {
        String str1 = str.toString();
        if ((str1.startsWith("0")&&str.length()>1)|| Integer.parseInt(str1) > 255){
            return;
        }
        if ("".equals(s) && time == 3){
            result.add(string.toString());
            return;
        }
        if (time>3 || "".equals(s)){
            return;
        }
        char substring = s.charAt(0);
        if (substring < '0' || substring > '9'){
            return;
        }
        //加点
        function(result,s.substring(1),new StringBuilder().append(substring),new StringBuilder().append(string).append(".").append(substring),time+1);
        //不加点
        if (str1.length()<3 ){
            function(result,s.substring(1),str.append(substring),string.append(substring),time);
        }
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();
        fun(result,nums,0,0,1,new ArrayList<Integer>());
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(0);
        fun(result,nums,nums[0],1,1,integers);
        return result;
    }

    private static void fun(List<List<Integer>> result, int[] nums, int sum ,int time,int index,List<Integer> arr) {
        if (time == 3 ){
            if (sum == 0){
                result.add(arr);
            }
            fun(result,nums,0,0,index+1,new ArrayList<Integer>());
            ArrayList<Integer> integers = new ArrayList<>();
            integers.add(nums[index]);
            fun(result,nums,nums[index],1,index+1,integers);
            return;
        }

        if (index == nums.length) return;
        //不选
        fun(result,nums,sum,time,index+1,new ArrayList<>(arr));
        //选
        arr.add(nums[index]);
        fun(result,nums,sum+nums[index],time+1,index+1,arr);

    }

    public static void main(String[] args) {
        List<List<Integer>> lists = threeSum(new int[]{-1, 0, 1, 2, -1, -4});
        for (List<Integer> list : lists) {
            System.out.println(list);
        }
    }

}

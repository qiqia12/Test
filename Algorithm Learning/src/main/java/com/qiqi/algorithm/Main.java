package com.qiqi.algorithm;

import java.util.*;

public class Main {
    static List<int[]> res;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int end = sc.nextInt();
        res = new LinkedList<>();
        int start = 1;
        dfs(start,end,0);
        System.out.println(res.size());
        for (int i = 0; i < res.size(); i++) {
            int[] ints = res.get(i);
            System.out.println(ints[0]+" "+ints[1]+" "+ints[2]);
        }

    }

    private static void dfs(int start, int end,int index) {
        if (start == end) return;
        int mid = start +(end-start)/2 ;
        int[] arr = new int[]{start+index,mid+index,end+index};
        res.add(arr);
        //dfs(start,mid,index+mid-1);
        dfs(start,mid,end/2+index);
        dfs(mid+1,end,-((end+1)/2));
    }

    public int distinctAverages(int[] nums) {
        HashSet<Float> set = new HashSet<>();
        Arrays.sort(nums);
        int start = 0,end = nums.length-1;
        while(start<end){
            int s = nums[start++];
            int e = nums[end--];
            float res = (s+e)/2f;
            set.add(res);

        }
        return set.size();
    }
    //最低票价
    public int mincostTickets(int[] days, int[] costs) {
        int len = days.length;
        int max = days[len-1];
        int min = days[0];
        int[] dp = new int[max + 31];

        for (int i = max,index = len-1 ; i >=0; i--) {
            if (i == days[index]){
                index--;
                dp[i] = Math.min(costs[0]+dp[i+1],Math.min(costs[1]+dp[i+7],costs[2]+dp[i+30]));
            }else{
                dp[i] = dp[i+1];
            }
        }
        return dp[min];
    }

    public int numTilings(int n) {
        if (n == 1) return 1;
        int[] dp = new int[n+1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <=n; i++) {
            dp[i] = dp[i-1]+dp[i-2]+2;
            for (int j = i-3; j >=1 ; j++) {
                dp[i]+=2*dp[j];
            }
        }
        return dp[n];
    }
}

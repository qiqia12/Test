package com.qiqi.algorithm;

import java.util.*;

/**
 * @projectName: Test
 * @package: com.qiqi.algorithm
 * @className: Day13
 * @author: Eric
 * @description: TODO
 * @date: 2023/5/27 20:01
 * @version: 1.0
 */
public class Day13 {

    public static double[] sampleStats(int[] count) {
        long total = 0;
        int maxIndex = 0;
        double max = 0;
        int index = 0;
        double min = 0;
        int size = 0;
        for (; index < count.length ; index++) {
            if (count[index]>0){
                min = index;
                break;
            }
        }
        for (;index<count.length;index++){
            if (count[index]>0){
                max = Math.max(max,index);
                maxIndex = count[maxIndex] < count[index]?index:maxIndex;
                size += count[index];
                total += (long)index * count[index];
            }
        }
        double mid = 0d;
        if (size%2 == 1){
            mid = find(count,size/2+1);
        }else{
            mid = (find(count,size/2) + find(count,size/2+1))/2.0;
        }
        return new double[]{min,max,total/size,mid,maxIndex};

    }

    private static int find(int[] count, int index) {
        int pos = 0;
        for (int i = 0; ; i++) {
            pos += count[i];
            if (index<= pos){
                return i;
            }
        }
    }

    public static void main(String[] args) {
        test();
    }
    static int[] days;
    static int[][] dis;
    static int[] dp;
    public static void test(){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        days = new int[n+1];
        dp = new int[n+1];
        for (int i = 1; i <=n ; i++) {
            days[i] = sc.nextInt();
        }
        dis = new int[n+1][n+1];
        ArrayList<Integer>[] list  = new ArrayList[m+1];
        Arrays.setAll(list,e->new ArrayList<>());
        for (int i = 0; i <m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int distance = sc.nextInt();
            list[a].add(b);
            list[b].add(a);
            dis[a][b] = dis[b][a] = distance;
        }
        int result = dfs(1,n,list,new HashSet<Integer>());
        System.out.println(result);
        sc.close();

    }

    private static int dfs(int index, int aim, ArrayList<Integer>[] list,HashSet<Integer> set) {
        if (dp[index]>0)return dp[index];
        int result = 1000000;
        if (index == aim) return 0;
        set.add(index);
        for (Integer integer : list[index]) {
            if (!set.contains(integer)){
                result = Math.min(result,dfs(integer,aim,list,set)+dis[integer][index]);
            }
        }
        set.remove(index);
        dp[index] = result+days[index];
        return dp[index];
    }


}

package com.qiqi.algorithm;

import java.util.*;

/**
 * @projectName: Test
 * @package: com.qiqi.algorithm
 * @className: Test
 * @author: Eric
 * @description: TODO
 * @date: 2023/5/26 18:31
 * @version: 1.0
 */
public class Test {
    private int _textnum;
    private static int s_textnum = 3;
    public Test(int textnum){
        _textnum = textnum;
    }
    private void fn() {
        if(_textnum > 0){
            _textnum -= s_textnum;
            fn();
        }
    }
    private String str() {
        s_textnum = 2;
        String ret = _textnum+","+_textnum+s_textnum;
        return ret;
    }

    public String test(){
        fn();
        return str();
    }



    //最短路径
    public static int shortestPathBinaryMatrix(int[][] grid) {
        if (grid[0][0] != 0) return -1;
        int len = grid.length;
        int[][] dp = new int[len+1][len+1];
        for (int i = 0; i <= len; i++) {
            Arrays.fill(dp[i],10000);
        };
        for (int i = 1; i <= len; i++) {
            if (dp[1][i] == 1) break;
            dp[1][i] = i;
        }

        for (int i = 2; i <= len; i++) {
            for (int j = 1; j <= len; j++) {
                if (grid[i-1][j-1] == 0){
                    dp[i][j] = Math.min(dp[i-1][j],Math.min(dp[i-1][j-1],dp[i][j-1]))+1;
                }
            }
        }
        return dp[len][len]>=10000?-1:dp[len][len];
    }

    public static void main(String[] args) {
        tt();
    }


    public int shortestPathBinaryMatrix1(int[][] grid) {
        int len = grid.length;
        if (grid[0][0] != 0) return -1;
        int[][] dp = new int[len+1][len+1];
        dfs(dp,grid,1,1,0);
        return dp[len][len]==0?-1:dp[len][len];
    }

    private void dfs(int[][] dp, int[][] grid, int i, int j,int value) {
        if (!isArea(i,j,grid) || grid[i-1][j-1] == 1) return;
        dp[i][j] = value+1;
        grid[i-1][j-1] = 1;
        dfs(dp,grid,i-1,j-1,value+1);
        dfs(dp,grid,i+1,j+1,value+1);
        dfs(dp,grid,i-1,j+1,value+1);
        dfs(dp,grid,i+1,j-1,value+1);
        dfs(dp,grid,i+1,j,value+1);
        dfs(dp,grid,i-1,j,value+1);
        dfs(dp,grid,i,j+1,value+1);
        dfs(dp,grid,i,j-1,value+1);

    }

    private boolean isArea(int i, int j, int[][] grid) {
        return i-1 >=0 && i-1<grid.length && j-1>=0 && j-1 < grid.length;
    }

    public static void t(){
        for (int i = 0; i <= 6; i++) {
            for (int j = 0; j < 60; j++) {
                for (int k = 1; k < 60; k++) {
                    if (Math.abs(i*5-j) == 2* Math.abs(j-k)){
                        System.out.println(i+" "+j+" "+k);
                        return;
                    }
                }
            }
        }
    }

    public static void tt(){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] days = new int[n+1];
        for (int i = 1; i <= n; i++) {
            days[i] = sc.nextInt();
        }
        days[1] = 0;
        int[][] roads = new int[n+1][n+1];
        ArrayList<Integer>[] list = new ArrayList[n+1];
        Arrays.setAll(list,e->new ArrayList<>());
        for (int i = 1; i <= m ; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int length = sc.nextInt();
            roads[a][b] = length;
            roads[b][a] = length;
            list[a].add(b);
            list[b].add(a);
        }
        int result = dffs(days,roads,list,1,n,new HashSet<Integer>());
        System.out.println(result);

    }

    private static int dffs(int[] days, int[][] roads, ArrayList<Integer>[] list, int i, int n,HashSet<Integer> set) {
        if (i == n) return 0;
        set.add(i);
        int result = Integer.MAX_VALUE;
        for (Integer integer : list[i]) {
            if (!set.contains(integer)){
                result = Math.min(result,dffs(days,roads,list,integer,n,set)+roads[integer][i]);
            }
        }
        set.remove( i);
        return result+days[i];
    }

}

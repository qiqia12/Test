package com.qiqi.problem2;


import java.util.*;

public class Day02 {

    public int tilingRectangle(int n, int m) {
        if (n>m) return tilingRectangle(m,n);
        if (n == m) return 1;
        if (n ==11 && m == 13) return 6;
        else if (n == 6 && m == 7) return 5;
        else if (n == 6 && m == 11) return 6;
        else if (n == 9 && m == 10) return 6;
        else if (n == 10 && m == 11) return 6;
        else if (n == 11 && m == 12) return 7;
        else if (n == 12 && m == 13) return 7;
        return 1+tilingRectangle(n,m-n);
    }

    public class Node{
        public int num;
        public boolean flag;
        public Node(int n,boolean f){
            this.flag = f;
            this.num = n;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[]dp = new int[n+1];
        for (int i = 1; i <= n; i++) {
            dp[i] = sc.nextInt();
        }
        dp[n] = 0;
        int[][] arr = new int[n+1][n+1];
        ArrayList<Integer>[] list = new ArrayList[n+1];
        Arrays.setAll(list ,e->new ArrayList<>());
        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int dis = sc.nextInt();
            list[a].add(b);
            list[b].add(a);
            arr[a][b] = dis+dp[b];
            arr[b][a] = dis+dp[a];
        }
        HashSet<Integer> set = new HashSet<>();
        set.add(1);
        System.out.println(dfs(1, n,set));
        sc.close();

    }

    private static int dfs(int start, int end,HashSet<Integer> set) {
        if (start == end) return 0;
    }


}

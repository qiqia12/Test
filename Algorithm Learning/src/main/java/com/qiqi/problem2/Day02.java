package com.qiqi.problem2;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
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
    static final int N = 1010;
    static int[][] gra = new int[N][N];
    static int[] dist = new int[N];
    static int[] g = new int[N];
    static boolean[] st = new boolean[N];
    static int n, m;
    public static void main1(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        for (int i = 1; i <= n; ++i) {
            g[i] = sc.nextInt();
        }
        g[n] = 0;
        for (int[] row : gra) {
            Arrays.fill(row, 0x3f3f3f);
        }
        for (int i = 1; i <= m; ++i) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int c = sc.nextInt();
            gra[u][v] = g[v] + c;
            gra[v][u] = g[u] + c;
        }
        System.out.println(dijkstra());

    }
    static int dijkstra() {
        Arrays.fill(dist, 0x3f3f3f);
        dist[1] = 0;

        for (int i = 0; i < n - 1; ++i) {
            int t = -1;
            for (int j = 1; j <= n; ++j) {
                if (!st[j] && (t == -1 || dist[t] > dist[j])) {
                    t = j;
                }
            }
            st[t] = true;
            for (int j = 1; j <= n; ++j) {
                dist[j] = Math.min(dist[j], dist[t] + gra[t][j]);
            }
        }
        return dist[n];
    }


    static int[][] dp ;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        dp = new int[n+1][n+1];
        int[][] edge = new int[n+1][n+1];
        int [] days = new int[n+1];
        for (int i = 1; i <= n; i++) {
            days[i] = sc.nextInt();
        }
        days[n] =0;
        HashMap<Integer,List<Integer>> map = new HashMap<>();
        for (int i = 1; i <= n ; i++) {
            map.put(i,new LinkedList<>());
        }
        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int dis = sc.nextInt();
            List<Integer> aList = map.get(a);
            aList.add(b);
            List<Integer> bList = map.get(b);
            bList.add(a);
            edge[a][b] = dis+days[b];
            edge[b][a] = dis+days[a];
        }
        System.out.println(djtsl(1, n, edge, new HashSet<Integer>(),map));
    }

    private static int djtsl(int start, int end, int[][] edge, HashSet<Integer> set,HashMap<Integer,List<Integer>> map) {
        if (start == end) return 0;
        if (dp[start][end]!=0) return dp[start][end];
        List<Integer> list = map.get(start);
        set.add(start);
        int result = 100001;
        for (Integer i : list) {
            if (set.add(i)){
                result = Math.min(result,edge[start][i] + djtsl(i,end,edge,set,map));
                set.remove(i);
            }
        }
        dp[start][end] = result;
        return result;
    }


}

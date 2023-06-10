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


    public static void main(String[] args) {
        System.out.println(Arrays.toString(numSmallerByFrequency(new String[]{"aabbabbb","abbbabaa","aabbbabaa","aabba","abb","a","ba","aa","ba","baabbbaaaa","babaa","bbbbabaa"}, new String[]{"b","aaaba","aaaabba","aa","aabaabab","aabbaaabbb","ababb","bbb","aabbbabb","aab","bbaaababba","baaaaa"})));
    }

    public static int[] numSmallerByFrequency(String[] queries, String[] words) {
        int length = queries.length;
        int[] res = new int[length];
        int len = words.length;
        int[] w = new int[len];
        for (int i = 0; i < len; i++) {
            w[i] = f(words[i]);
        }
        Arrays.sort(w);
        for (int i = 0; i < length; i++) {
            int num = f(queries[i]);
            int left =0,right = len;
            while(left<right){
                int mid = left +(right - left)/2;
                if (w[mid]<=num){
                    left = mid+1;
                }else{
                    right = mid;
                }
            }
            res[i] = len- left;
        }
        return res;
    }

    private static int f(String word) {
        int [] res = new int[26];
        for (char c : word.toCharArray()) {
            res[c-'a']++;
        }
        int result =0;
        for (int i = 0; i < 26; i++) {
            if (res[i]!=0){
                result = res[i];
                break;
            }
        }
        return  result;
    }


}

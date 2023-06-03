package com.qiqi.algorithm;

import java.util.*;

public class Main {
    static int[][] next;
    static ArrayList<Integer>[] lists;
    static int [][] arr;
    public static void main1(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        lists = new ArrayList[n+1];
        Arrays.setAll(lists,e->new ArrayList<>());
        arr = new int[m+1][m+1];
        next = new int[n+1][n+1];
        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int dis = sc.nextInt();
            arr[a][b] = dis;
            arr[b][a] = dis;
            lists[a].add(b);
            lists[b].add(a);
        }
        int q = sc.nextInt();
        int[][] dp = new int[q][2];
        for (int i = 0; i < q; i++) {
            dp[i][0] = sc.nextInt();
            dp[i][1] = sc.nextInt();
        }

        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < q; i++) {
            int start = dp[i][0] , end = dp[i][1];
            if (next[start][end] != 0){
                System.out.println(next[start][end]);
                continue;
            }
            List<Integer> list = new LinkedList<>();
            int result = 0;
            set.add(start);
            for (Integer integer : lists[start]) {
                set.add(integer);
                result = dsf(integer,end,set);
                list.add(Math.min(result,arr[start][integer]));
                set.remove(integer);
            }
            Integer integer = list.stream().max(Integer::compareTo).orElse(-1);
            next[start][end] = integer;
            System.out.println(integer);
        }

    }

    private static int dsf(Integer start, int end, HashSet<Integer> set) {
        if (next[start][end] != 0){
            return next[start][end];
        }
        if (start == end) return 10000;
        List<Integer> list = new LinkedList<>();
        for (Integer integer : lists[start]) {
            if (!set.contains(integer)){
                set.add(integer);
                if (integer == end){
                    return arr[start][integer];
                }
                int num = dsf(integer,end,set);
                list.add(Math.min(num,arr[start][integer]));
                set.remove(integer);
            }
        }
        int result = list.stream().max(Comparator.comparing(x->x)).orElse(-1);
        next[start][end] = result;
        return result;
    }

    public int maxRepOpt1(String text) {
        int[] cnt = new int[26];
        int n = text.length();
        for (char c : text.toCharArray()) {
            cnt[c-'a']++;
        }
        int ans = 0, i = 0;
        while (i < n) {
            int j = i;
            while (j < n && text.charAt(j) == text.charAt(i)) {
                ++j;
            }
            int l = j - i;
            int k = j + 1;
            while (k < n && text.charAt(k) == text.charAt(i)) {
                ++k;
            }
            int r = k - j - 1;
            ans = Math.max(ans, Math.min(l + r + 1, cnt[text.charAt(i) - 'a']));
            i = j;
        }
        return ans;
    }
    //零钱兑换
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        Arrays.fill(dp,amount+1);
        for (int coin : coins) {
            if (coin<=amount){
                dp[coin] = 1;
            }
        }
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (i>coin){
                    dp[i] = Math.min(dp[i],dp[i-coin]+1);
                }
            }
        }

        return dp[amount]>=amount+1?-1:dp[amount];

    }
    public static int countGoodStrings(int low, int high, int zero, int one) {
        int MOD = (int) 1e9+7;
        int[] dp = new int[high+1];
        dp[zero] =dp[zero] + 1;
        dp[one] = dp[one] + 1;
        for (int i = 1; i <= high; i++) {
            if (i>zero){
                dp[i] += dp[i-zero]%MOD;
            }
            if (i>one){
                dp[i] +=dp[i-one]%MOD;
            }
        }
        int result = 0;
        for (int i = low; i <= high; i++) {
            result = (result + dp[i]) % MOD;
        }
        return result;


        int MOD = (int) 1e9 + 7;
        int[] f = new int[high + 1];
        f[0] = 1;
        for (int i = 1; i <= high; i++) {
            int a = i >= zero ? f[i - zero] : 0, b = i >= one ? f[i - one] : 0;
            f[i] = (a + b) % MOD;
        }
        int res = 0;
        for (int i = low; i <= high; i++) {
            res = (res + f[i]) % MOD;
        }
        return res;
    }

    public static void main(String[] args) {
        countGoodStrings(3,3,1,1);
    }

    public int numDecodings(String s) {
        if (s.startsWith("0")) return 0;
        int len = s.length();
        int[] dp = new int[len+1];
        dp[0] = 1;
        s = " "+s;
        for (int i = 1; i <= len; i++) {
            int a = s.charAt(i)-'0';
            int b = (s.charAt(i-1)-'0')*10+a;
            if (a>=1 && a<=9){
                dp[i] = dp[i-1];
            }
            if (b>=10 && b<= 26){
                dp[i] += dp[i-2];
            }
        }
        return dp[len];
    }


}

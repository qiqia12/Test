package com.qiqi.problem3;


import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int box = sc.nextInt();
        int goods =sc.nextInt();
        int[] dp = new int[box+1];
        dp[1] = goods;
        for (int i = 2; i <= box; i++) {
            dp[i] = (goods-1)*dp[i-1];
        }
        System.out.println(dp[box]);
        sc.close();
    }
}

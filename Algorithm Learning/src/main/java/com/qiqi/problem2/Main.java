package com.qiqi.problem2;


import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        char[] chars = str.toCharArray();
        int result = str.length();
        char aChar = chars[0];
        for (int i = 1; i < str.length(); i++) {
            if (chars[i] == aChar){
                int s = i+1;
                int start = 1;
                while(s < str.length() && chars[start] == chars[s]){
                    start++;
                    s++;
                }
                result += s-i;
            }
        }
        System.out.println(result);
        sc.close();
    }
}

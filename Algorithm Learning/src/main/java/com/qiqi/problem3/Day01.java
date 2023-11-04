package com.qiqi.problem3;

import java.util.HashSet;

/**
 * @projectName: Test
 * @package: com.qiqi.problem3
 * @className: Day01
 * @author: Eric
 * @description: TODO
 * @date: 2023/9/12 14:07
 * @version: 1.0
 */
public class Day01 {
    // 给定一个正整数n（1≤n≤9）, 输出1到n的所有排列 输入 3 输出 123, 132, 213, 231, 312, 321


    public static void main(String[] args) {
        test(3);
        System.out.println(result);

    }
    static HashSet<String> result = new HashSet<>();
    public static void  test(int n){
        HashSet<Integer> set = new HashSet<>();
        for (int i = 1; i <= n; i++) {
            set.add(i);
            StringBuilder stringBuilder = new StringBuilder();
            process(1,i,n,stringBuilder.append(i),set);
            set.remove(i);
        }


    }

    private static void process(int num , int i,int n,StringBuilder str,HashSet<Integer> set) {
        if (num == n){
            result.add(str.toString());
            return;
        }

        for (int j = 1; j <= n ; j++) {
            if (j == i || set.contains(j)) continue;
            set.add(j);
            process(num+1,j,n,str.append(j),set);
            str.delete(num,num+1);
            set.remove(j);
        }
    }




}

package com.qiqi.problem3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Day03 {


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        String str = in.nextLine();
        int n = Integer.parseInt(in.nextLine());
        char[] u = str.substring(0,3).toCharArray();
        char[] g = str.substring(3,6).toCharArray();
        char[] o = str.substring(6).toCharArray();
        HashMap<Character,Integer> map = new HashMap<>();
        map.put('r',0);
        map.put('w',1);
        map.put('x',2);
        for (int i = 0; i < n; i++) {
            String string = in.nextLine().split(" ")[1];
            if (string.contains("+")){
                int index = string.indexOf("+");
                char[] user = string.substring(0, index).toCharArray();
                char[] op = string.substring(index+1).toCharArray();
                for (int j = 0; j < user.length; j++) {
                    if (user[j] == 'u'){
                        for (char c : op) {
                            u[map.get(c)] = c;
                        }
                    }else if (user[j] == 'g'){
                        for (char c : op) {
                            g[map.get(c)] = c;
                        }
                    }else if(user[j] == '0'){
                        for (char c : op) {
                            o[map.get(c)] = c;
                        }
                    }else{
                        for (char c : op) {
                            o[map.get(c)] = c;
                            g[map.get(c)] = c;
                            u[map.get(c)] = c;
                        }
                    }
                }
            }else if (string.contains("-")){
                int index = string.indexOf("-");
                char[] user = string.substring(0, index).toCharArray();
                char[] op = string.substring(index+1).toCharArray();
                for (int j = 0; j < user.length; j++) {
                    if (user[j] == 'u'){
                        for (char c : op) {
                            u[map.get(c)] = '-';
                        }
                    }else if (user[j] == 'g'){
                        for (char c : op) {
                            g[map.get(c)] = '-';
                        }
                    }else if(user[j] == '0') {
                        for (char c : op) {
                            o[map.get(c)] = '-';
                        }
                    }else {
                        for (char c : op) {
                            o[map.get(c)] = '-';
                            g[map.get(c)] = '-';
                            u[map.get(c)] = '-';
                        }
                    }
                }
            }else if (string.contains("=")){
                int index = string.indexOf("=");
                char[] user = string.substring(0, index).toCharArray();
                char[] op = string.substring(index+1).toCharArray();
                for (int j = 0; j < user.length; j++) {
                    if (user[j] == 'u'){
                        for (char c : op) {
                            u[map.get(c)] = c;
                        }
                    }else if (user[j] == 'g'){
                        for (char c : op) {
                            g[map.get(c)] = c;
                        }
                    }else if(user[j] == '0'){
                        for (char c : op) {
                            o[map.get(c)] = c;
                        }
                    }else{
                        for (char c : op) {
                            o[map.get(c)] = c;
                            g[map.get(c)] = c;
                            u[map.get(c)] = c;
                        }
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(u).append(g).append(o);
        System.out.println(sb);
        in.close();
    }

}

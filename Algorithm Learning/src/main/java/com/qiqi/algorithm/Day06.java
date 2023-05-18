package com.qiqi.algorithm;

import java.util.LinkedList;

/**
 * @projectName: Test
 * @package: com.qiqi.algorithm
 * @className: Day06
 * @author: Eric
 * @description: TODO
 * @date: 2023/5/18 19:38
 * @version: 1.0
 */
public class Day06 {

    public static int[] addNegabinary(int[] arr1, int[] arr2) {
        if(arr1[0] == 0) return arr2;
        if (arr2[0] == 0) return arr1;
        LinkedList<Integer> list1 = new LinkedList<>();
        LinkedList<Integer> list2 = new LinkedList<>();
        int len = Math.max(arr1.length,arr2.length);
        int index1 = len-1;
        int len1 = arr1.length-1;
        int len2 = arr2.length-1;
        while(index1-->=0){
            list1.addFirst(len1>=0?arr1[len1]:0);
            list2.addFirst(len2>=0?arr2[len2]:0);
            len1--;
            len2--;
        }
        LinkedList<Integer> result = new LinkedList<>();
        while(!list1.isEmpty()){
            int num = list1.peekLast() + list2.peekLast();
            list1.pollLast();
            list2.pollLast();
            if (num >= 2){
                if (list2.isEmpty()){
                    result.addFirst(num%2);
                    result.addFirst(1);
                    result.addFirst(1);
                }else if (list1.peekLast() >= 1){
                    if (num==2)
                        list1.addLast(list1.pollLast()-1);
                    else
                        list2.addLast(list2.pollLast()+1);
                    result.addFirst(num%2);
                }else if (list2.peekLast() >= 1){
                    if (num==2)
                        list2.addLast(list2.pollLast()-1);
                    else
                        list1.addLast(list1.pollLast()+1);
                    result.addFirst(num%2);
                }else{
                    result.addFirst(num%2);
                    if (num>2){
                        list1.addLast(list1.pollLast()+1);
                    }else{
                        list1.pollLast();
                        list2.pollLast();
                        list1.addLast(2);
                        list2.addLast(1);
                    }
                }
            }else{
                result.addFirst(num);
            }
        }
        while( result.peekFirst() == 0 && result.size()>1){
            result.pollFirst();
        }
        int length = result.size();
        int[] arr = new int[length];
        int index = 0;
        while(!result.isEmpty()){
            arr[index++] = result.pollFirst();
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr1 = {1,1};
        int[] arr2 = {1};
        System.out.println(addNegabinary(arr1, arr2));
    }

    public int[] addNegabinary1(int[] arr1, int[] arr2) {
        if(arr1[0] == 0) return arr2;
        if (arr2[0] == 0) return arr1;
        LinkedList<Integer> list1 = new LinkedList<>();
        LinkedList<Integer> list2 = new LinkedList<>();
        int len = Math.max(arr1.length,arr2.length);
        int index1 = len-1;
        int len1 = arr1.length-1;
        int len2 = arr2.length-1;
        while(index1-->=0){
            list1.addFirst(len1>=0?arr1[len1]:0);
            list2.addFirst(len2>=0?arr2[len2]:0);
            len1--;
            len2--;
        }
        LinkedList<Integer> result = new LinkedList<>();
        while(!list1.isEmpty()){
            int num = list1.peekLast() + list2.peekLast();
            list1.pollLast();
            list2.pollLast();
            if (num >= 2){
                result.addFirst(num%2);
                if (list2.isEmpty()){
                    result.addFirst(1);
                    result.addFirst(1);
                }else if (list1.peekLast() >= 1){
                    list1.addLast(list1.pollLast()-1);
                }else if (list2.peekLast() >= 1){
                    list2.addLast(list2.pollLast()-1);
                }else{
                    list1.pollLast();
                    list2.pollLast();
                    list1.addLast(2);
                    list2.addLast(1);
                }
            }else{
                result.addFirst(num);
            }
        }
        while( result.peekFirst() == 0 && result.size()>1){
            result.pollFirst();
        }
        int length = result.size();
        int[] arr = new int[length];
        int index = 0;
        while(!result.isEmpty()){
            arr[index++] = result.pollFirst();
        }
        return arr;
    }
}

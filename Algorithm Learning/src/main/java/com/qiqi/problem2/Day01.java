package com.qiqi.problem2;


import java.util.*;

public class Day01 {

    public static class Node {
        public int value;
        public int index;
        public Node(int v,int i){
            this.value = v;
            this.index = i;
        }
    }
    public static int miceAndCheese(int[] reward1, int[] reward2, int k) {
        int len = reward1.length;
        PriorityQueue<Node> queue = new PriorityQueue<>((a,b)->a.value-b.value);
        for (int i = 0; i < len; i++) {
            int value = reward2[i] - reward1[i];
            queue.add(new Node(value,i));
        }
        int result = 0;
        while(!queue.isEmpty()){
            Node poll = queue.poll();
            if (k>0){
                result+=reward1[poll.index];
                k--;
            }else{
                result+=reward2[poll.index];
            }
        }
        return result;
    }

    //分糖果

    public int distributeCandies(int[] candyType) {
        HashSet<Integer> set = new HashSet<>();
        int num = candyType.length/2;
        for (int i : candyType) {
            set.add(i);
            if (set.size()==num){
                return num;
            }
        }
        return candyType.length;
    }

    public int maximumCandies(int[] candies, long k) {
        long sum = 0L;
        int max = candies[0];
        for (int candy : candies) {
            max = Math.max(max,candy);
            sum+=candy;
        }
        if (sum<k) return 0;
        int left = 0,right = max;
        while(left < max){
            int mid = left +((right - left+1) >> 1);
            if (check(candies,k,mid)){
                left = mid;
            }else{
                right = mid - 1;
            }
        }
        return left;
    }

    private boolean check(int[] candies, long k, int mid) {
        for (int candy : candies) {
            k -= candy / mid;
            if (k <= 0)return true;
        }
        return k <= 0;
    }

    public int minEatingSpeed(int[] piles, int h) {
        int max = 0;
        for (int pile : piles) {
            max = Math.max(pile,max);
        }
        int left = 1,right = max;
        while(left < right){
            int mid = left +(right - left)/2;
            if (check1(piles,mid,h)){
                right = mid;
            }else{
                left = mid+1;
            }
        }
        return left;
    }

    private boolean check1(int[] piles, int mid, int h) {
        int hour = 0;
        for (int pile : piles) {
            if (pile%mid == 0){
                hour += pile/mid;
            }else{
                hour += pile/mid +1;
            }
            if (hour > h) return false;
        }
        return hour <= h;
    }

    public static int shipWithinDays(int[] weights, int days) {
        int max = 0,sum =0;
        for (int weight : weights) {
            sum += weight;
            max = Math.max(max,weight);
        }
        int right = sum;
        int left = max;
        while(left < right){
            int mid = left + (right - left) /2;
            if (ch(weights,days,mid)){
                right = mid;
            }else{
                left = mid+1;
            }
        }
        return left;
    }

    private static boolean ch(int[] ws, int t, int d) {
        int n = ws.length;
        int cnt = 1;
        for (int i = 1, sum = ws[0]; i < n; sum = 0, cnt++) {
            while (i < n && sum + ws[i] <= t) {
                sum += ws[i];
                i++;
            }
        }
        return cnt - 1 <= d;
    }

    public static void main(String[] args) {
        shipWithinDays(new int[]{3,3,3,3,3,3},2);
    }

}

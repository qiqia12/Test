package com.qiqi.problem3;


import java.util.*;

public class Main {


    public int[] minInterval(int[][] intervals, int[] queries) {

        int n = intervals.length, m = queries.length;
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        int[][] qs = new int[m][0];
        for (int i = 0; i < m; ++i) {
            qs[i] = new int[] {queries[i], i};
        }
        Arrays.sort(qs, (a, b) -> a[0] - b[0]);
        int[] ans = new int[m];
        Arrays.fill(ans, -1);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        int i = 0;
        for (int[] q : qs) {
            while (i < n && intervals[i][0] <= q[0]) {
                int a = intervals[i][0], b = intervals[i][1];
                pq.offer(new int[] {b - a + 1, b});
                ++i;
            }
            while (!pq.isEmpty() && pq.peek()[1] < q[0]) {
                pq.poll();
            }
            if (!pq.isEmpty()) {
                ans[q[1]] = pq.peek()[0];
            }
        }
        return ans;
    }
    public static int robotSim(int[] commands, int[][] obstacles) {
        //2 代表向北  1 代表向东 -2 代表南  -1 代表向西
        int[] position = new int[]{0,0,2};
        int result = 0;
        HashSet<String> set = new HashSet<>();
        for (int[] obstacle : obstacles) {
            set.add(obstacle[0]+","+obstacle[1]);
        }
        for (int command : commands) {
            position = process(command,set,position);
            result = Math.max(result,(int)(Math.pow(Math.abs(position[0]),2) + Math.pow(Math.abs(position[1]),2)));
        }
        return result;
    }

    private static int[] process(int command, HashSet<String> set, int[] position) {
        if (command == -1 || command == -2){
            position = nextDirection(position,command);
            return position;
        }
        if (position[2] == 1){
            for (int i = 1; i <= command; i++) {
                if (set.contains((position[0]+1)+","+position[1])){
                    return position;
                }
                position[0]++;
            }
        }else if (position[2] == 2){
            for (int i = 1; i <= command; i++) {
                if (set.contains(position[0]+","+(position[1]+1))){
                    return position;
                }
                position[1]++;
            }
        }else if (position[2] == -1){
            for (int i = 1; i <= command; i++) {
                if (set.contains((position[0]-1)+","+position[1])){
                    return position;
                }
                position[0]--;
            }
        }else{
            for (int i = 1; i <= command; i++) {
                if (set.contains(position[0]+","+(position[1]-1))){
                    return position;
                }
                position[1]--;
            }
        }
        return position;
    }

    private static int[] nextDirection(int[] position, int command) {
        if (command == -1){
            if (position[2] == 1){
                position[2] =-2;
                return position;
            }
            else if (position[2] == 2){
                position[2] =1;
                return position;
            }else if (position[2] == -1){
                position[2] = 2;
                return position;
            }else{
                position[2] = -1;
                return position;
            }
        }else{
            if (position[2] == 1){
                position[2] = 2;
                return position;
            }
            else if (position[2] == 2){
                position[2] = -1;
                return position;
            }else if (position[2] == -1){
                position[2] = -2;
                return position;
            }else{
                position[2] = 1;
                return position;
            }
        }
    }


    public int maxSubarraySumCircular(int[] A) {
        int total = 0, maxSum = A[0], curMax = 0, minSum = A[0], curMin = 0;

        for (int a : A) {
            curMax = Math.max(curMax + a, a);
            maxSum = Math.max(maxSum, curMax);
            curMin = Math.min(curMin + a, a);
            minSum = Math.min(minSum, curMin);
            total += a;
        }
        return maxSum > 0 ? Math.max(maxSum, total - minSum) : maxSum;
    }

    public static void main(String[] args) {
        System.out.println(robotSim(new int[]{4,-1,4,-2,4}, new int[][]{{2,4}}));
    }

    public int findMaxValueOfEquation(int[][] points, int k) {
        int ans = Integer.MIN_VALUE;

        ArrayDeque<int[]> q = new ArrayDeque<int[]>();

        for (int[] p : points) {

            int x = p[0], y = p[1];

            while (!q.isEmpty() && q.peekFirst()[0] < x - k) // 队首超出范围

                q.pollFirst(); // 弹它！

            if (!q.isEmpty())

                ans = Math.max(ans, x + y + q.peekFirst()[1]); // 加上最大的 yi-xi

            while (!q.isEmpty() && q.peekLast()[1] <= y - x) // 队尾不如新来的强

                q.pollLast(); // 弹它！

            q.addLast(new int[]{x, y - x});

        }

        return ans;
    }

}

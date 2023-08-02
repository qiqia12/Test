package com.qiqi.problem3;


import sun.text.resources.sr.FormatData_sr_Latn;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

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

    public int thirdMax(int[] nums) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(3);
        HashSet<Integer> set = new HashSet<>();
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            max = Math.max(max,num);
            if(queue.size()<3 && !set.contains(num)){
                queue.add(num);
                set.add(num);
            }else if (queue.size()==3 && num > queue.peek() && !set.contains(num)){
                queue.poll();
                queue.add(num);
                set.add(num);
            }
        }
        return queue.size()==3?queue.peek():max;
    }
    public int numJewelsInStones(String jewels, String stones) {
        HashSet<Character> set = new HashSet<>();
        char[] chars = jewels.toCharArray();
        for (char aChar : chars) {
            set.add(aChar);
        }
        int result = 0;
        for (char c : stones.toCharArray()) {
            if (set.contains(c)) result++;
        }
        return result;
    }
    public int halveArray(int[] nums) {
        float sum = Arrays.stream(nums).sum();
        PriorityQueue<Float> queue = new PriorityQueue<Float>(new Comparator<Float>() {
            @Override
            public int compare(Float o1, Float o2) {
                return (int) (o2-o1);
            }
        });
        for (int num : nums) {
            queue.add((float)num);
        }
        float now = sum;
        int result = 0;
        while(now > sum/2){
            result++;
            float cur = queue.poll();
            cur/=2;
            now -= cur;
            queue.add(cur);
        }
        return result;
    }
    public static  long[] handleQuery(int[] nums1, int[] nums2, int[][] queries) {
        long[] nums11 = new long[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            nums11[i] = nums1[i];
        }
        long[] nums22 = new long[nums2.length];
        for (int i = 0; i < nums2.length; i++) {
            nums22[i] = nums2[i];
        }
        List<Long> list = new ArrayList<>();
        for (int[] query : queries) {
            if (query[0] ==3){
                list.add(Arrays.stream(nums22).sum());
            }else if (query[0] ==1){
                reverse(nums11,query[1],query[2]);
            }else{
                proce(nums22,nums11,query[1]);
            }
        }
        long[] result = new long[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    private static void proce(long[] nums2, long[] nums1, int i) {
        for (int j = 0; j < nums2.length; j++) {
            nums2[j] += nums1[j] *i;
        }
    }

    private static void reverse(long[] nums1, int left, int right) {
        while(left<=right){
            if (nums1[left] == 0){
                nums1[left++] =1;
            }else if (nums1[left] ==1){
                nums1[left++] =0;
            }else{
                left++;
            }
        }
    }

    public static void main(String[] args) {
        handleQuery(new int[]{1,0,1},new int[]{0,0,0},new int[][]{{1,1,1},{2,1,0},{3,0,0}});
    }
    public int deleteGreatestValue(int[][] grid) {
        for (int[] ints : grid) {
            Arrays.sort(ints);
        }
        int result = 0;
        for (int i = grid[0].length-1; i >= 0 ; i--) {
            int max =0;
            for (int j = 0; j < grid.length; j++) {
                max = Math.max(max,grid[j][i]);
            }
            result+=max;
        }
        return result;
    }
    public int minimumTime(int n, int[][] relations, int[] time) {
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        int[] indeg = new int[n];
        for (int[] e : relations) {
            int a = e[0] - 1, b = e[1] - 1;
            g[a].add(b);
            ++indeg[b];
        }
        Deque<Integer> q = new ArrayDeque<>();
        int[] f = new int[n];
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int v = indeg[i], t = time[i];
            if (v == 0) {
                q.offer(i);
                f[i] = t;
                ans = Math.max(ans, t);
            }
        }
        while (!q.isEmpty()) {
            int i = q.pollFirst();
            for (int j : g[i]) {
                f[j] = Math.max(f[j], f[i] + time[j]);
                ans = Math.max(ans, f[j]);
                if (--indeg[j] == 0) {
                    q.offer(j);
                }
            }
        }
        return ans;
    }
    class ListNode {
       int val;
       ListNode next;
       ListNode(int x) {
           val = x;
           next = null;
       }
   }
    public boolean hasCycle(ListNode head) {
        if(head == null || head.next == null) return false;
        ListNode slow = head;
        ListNode fast = head.next;
        while(slow.next != null && fast.next!=null && fast.next.next!=null ){
            if (slow == fast) return true;
            slow = slow.next;
            fast = fast.next.next;
        }
        return false;
    }

    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null){
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next !=null && fast.next.next!=null){
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast)
                break;
        }
        if (fast.next == null || fast.next.next == null){
            return null;
        }
        while(head!=fast){
            head = head.next;
            fast = fast.next;
        }
        return head;
    }
    public void reorderList(ListNode head) {
        if(head == null || head.next == null)
            return;
        ListNode slow = head;
        ListNode fast = head;
        while( fast.next!=null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode cur = slow.next;
        slow.next = null;
        Stack<ListNode> stack = new Stack<>();
        while(cur!=null){
            stack.add(cur);
            cur = cur.next;
        }
        ListNode h = head;
        while(!stack.isEmpty()){
            ListNode pop = stack.pop();
            pop.next = h.next;
            h.next = pop;
            h = pop.next;
        }
    }

    public int sumOfPower(int[] nums) {
        final long MOD = (long) 1e9 + 7;
        Arrays.sort(nums);
        long ans = 0, s = 0;
        for (long x : nums) { // x 作为最大值
            ans = (ans + x * x % MOD * (x + s)) % MOD; // 中间模一次防止溢出
            s = (s * 2 + x) % MOD; // 递推计算下一个 s
        }
        return (int) ans;
    }
    public int flipgame(int[] fronts, int[] backs) {
        int result = Integer.MAX_VALUE;
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < fronts.length; i++) {
            if (fronts[i] == backs[i] ){
                set.add(fronts[i]);
            }
        }
        for (int front : fronts) {
            if (!set.contains(front))
                result = Math.min(result,front);
        }
        for (int back : backs) {
            if (set.contains(back))
                result = Math.min(result,back);
        }
        return result == Integer.MAX_VALUE?0:result;
    }


}

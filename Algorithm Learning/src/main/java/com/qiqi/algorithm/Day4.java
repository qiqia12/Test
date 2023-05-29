package com.qiqi.algorithm;

import java.util.*;

/**
 * @projectName: Test
 * @package: com.qiqi.algorithm
 * @className: Day4
 * @author: Eric
 * @description: TODO
 * @date: 2023/5/28 19:47
 * @version: 1.0
 */
public class Day4 {


    public int kthSmallest(int[][] mat, int k) {
        int[] a= new int[]{0};
        for (int[] ints : mat) {
            int[] b = new int[ints.length * a.length];
            int index = 0;
            for (int i : a) {
                for (int anInt : ints) {
                    b[index++] = i + anInt;
                }
            }
            if (b.length > k)
                b = Arrays.copyOfRange(b,0,k);
            a= b;
        }
        return a[k-1];
    }

    public class ListNode {
       int val;
       ListNode next;
       ListNode() {}
       ListNode(int val) { this.val = val; }
       ListNode(int val, ListNode next) { this.val = val; this.next = next; }
   }

    public ListNode reverseKGroup(ListNode head, int k) {
        Stack<ListNode> stack = new Stack<>();
        ListNode h = new ListNode();
        h.next = head;
        ListNode cur = head;
        ListNode hh = h;
        while(cur != null){
            stack.add(cur);
            cur = cur.next;
            if (stack.size() == k){
                while(!stack.isEmpty()){
                    stack.peek().next = null;
                    hh.next = stack.pop();
                    hh = hh.next;
                }
                hh.next = cur;
            }
        }
        return h.next;
    }
    public static class Node{
        public int pai;
        public int time;
        public int index;
        public Node(int pai,int index){
            this.pai = pai;
            this.index = index;
        }
    }

    public static void test(){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        PriorityQueue<Node> queue = new PriorityQueue<>((a,b)->b.pai - a.pai);
        HashMap<Integer,Node> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int num = sc.nextInt();
            if (queue.size()<m){
                Node node = new Node(num,i);
                queue.add(node);
                map.put(i,node);
            }else{
                if (num < queue.peek().pai){
                    Node node = new Node(num,i);
                    Node poll = queue.poll();
                    queue.add(node);
                    map.remove(poll.index);
                }
            }
        }
        PriorityQueue<Node> q = new PriorityQueue<>((a,b)->a.pai-b.pai);
        for (int i = 0; i < n; i++) {
            int time = sc.nextInt();
            if (map.containsKey(i)){
                Node node = map.get(i);
                node.time = time;
                q.add(node);
            }
        }
        while(m > 0){
            Node node = q.poll();
            if (node.time < 1){
                break;
            }
            node.time--;
            node.pai++;
            m--;
            q.add(node);
        }
        System.out.println(q.peek().pai);
    }

    public int averageValue(int[] nums) {
        int max =0;
        int time = 0;
        for (int num : nums) {
            if (num %3 == 0 && num%2 ==0){
                max += num;
                time++;
            }
        }
        return time == 0?0:max/time;
    }

    public static void main(String[] args) {
        String[] arr = new String[]{"10","0001","111001","1","0"};
        findMaxForm(arr,5,3);
    }


    public static void process(){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] dp = new int[n+1][n+1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i],Integer.MAX_VALUE);
        }
        int m = sc.nextInt();
        dp[n-1][n-1] = 0;
        int temp = 0;
        HashMap<String,List<int[]>> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            int x1 = sc.nextInt() -1;
            int y1 = sc.nextInt() -1;
            int x2 = sc.nextInt() -1;
            int y2 = sc.nextInt() -1;
            String str1 = x1+","+y1;
            String str2 = x2+","+y2;
            List<int[]> list1 = map.getOrDefault(str1, new LinkedList<>());
            list1.add(new int[]{x2,y2});
            map.put(str1,list1);
            List<int[]> list2 = map.getOrDefault(str2, new LinkedList<>());
            list2.add(new int[]{x1,y1});
            map.put(str2,list2);
        }
        for (int i = n-1; i >=0 ; i--) {
            for (int j = i==n-1?n-2:n-1; j >= 0 ; j--) {
                dp[i][j] = Math.min(dp[i][j+1],dp[i+1][j]);
                if (map.containsKey(i+","+j)){
                    List<int[]> ints = map.get(i + "," + j);
                    for (int[] anInt : ints) {
                        dp[i][j] = Math.min(dp[i][j],dp[anInt[0]][anInt[1]]);
                    }
                }
                dp[i][j]+=1;
                temp += dp[i][j];
            }
        }
        float total = n*n;
        System.out.printf("%.2f",temp/total);
        sc.close();
    }

    public static void aaa(){
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int m = scan.nextInt();
        int d = scan.nextInt();
        int[] arrN = new int[n];
        int[] arrM = new int[m];
        for (int i = 0; i < n; i++) {
            arrN[i] = scan.nextInt();
        }
        for (int i = 0; i < m; i++) {
            arrM[i] = scan.nextInt();
        }
        Arrays.sort(arrM);
        Arrays.sort(arrN);
        float reslut = arrN[0];
        int iN = 0;
        int iM =0;
        while(iN < n && iM < m){
            if (arrN[iN] > arrM[iM]){
                reslut += Math.sqrt(Math.pow(arrN[iN] - arrM[iM],2)+Math.pow(d,2));
                if (iM+1 < m){
                    reslut += arrM[iM+1] - arrM[iM];
                }
                iM++;
            }else if (arrN[iN] < arrM[iM]){
                reslut += Math.sqrt(Math.pow(arrM[iM] - arrN[iN],2)+Math.pow(d,2));
                if (iN+1 < n){
                    reslut += arrN[iN+1] - arrN[iN];
                }
                iN++;
            }else{
                if (iN+1 < n){
                    reslut += arrN[iN+1] - arrN[iN];
                }
                iN++;
            }
        }
        if (iN<n){
            reslut += arrN[n-1] - arrN[iN];
        }
        if (iM < m){
            reslut += arrM[m-1] - arrM[iM];
        }
        System.out.println(reslut);
    }

    public static int findMaxForm(String[] strs, int m, int n) {
        int[][] arr = new int[strs.length][2];
        for (int i = 0; i < strs.length; i++) {
            arr[i] = pro(strs[i]);
        }
        int[][] dp = new int[m+1][n+1];
        for (int[] ints : arr) {
            if (ints[0]<=m && ints[1] <= n)
                dp[ints[0]][ints[1]] =1;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                for (int[] ints : arr) {
                    if (i>=ints[0] && j>= ints[1]){
                        dp[i][j] = Math.max(dp[i][j],dp[i-ints[0]][j-ints[1]]+1);
                    }

                }
            }
        }
        return dp[m][n];
    }

    private static int[] pro(String str) {
        int m = 0;
        int n = 0;
        for (char c : str.toCharArray()) {
            if (c == '0') {
                m++;
            } else {
                n++;
            }
        }
        return new int[]{m,n};
    }


}

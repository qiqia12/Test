package com.qiqi.problem2;

/**
 * @projectName: Test
 * @package: com.qiqi.problem2
 * @className: Day03
 * @author: Eric
 * @description: TODO
 * @date: 2023/6/11 20:13
 * @version: 1.0
 */
public class Day03 {
    public static  class ListNode {
       int val;
       ListNode next;
       ListNode() {}
       ListNode(int val) { this.val = val; }
       ListNode(int val, ListNode next) { this.val = val; this.next = next; }
   }

    public static void main(String[] args) {
        distributeCandies(10,3);
    }
    public static ListNode removeZeroSumSublists(ListNode head) {
        ListNode H = new ListNode();
        ListNode h = H;
        H.next = head;
        while(h!=null){
            ListNode cur = h.next;
            int sum = 0;
            while(cur!=null){
                sum+=cur.val;
                if (sum == 0){
                    h.next = cur.next;
                }
                cur = cur.next;
            }
            h = h.next;
        }
        return H.next;
    }

    public static int[] distributeCandies(int candies, int num_people) {
        int[] result = new int[num_people];
        int index =0;
        while(candies>0){
            if (index+1 > candies){
                result[index%num_people] += candies;
                candies =0;
            }else{
                result[index%num_people] += index+1;
                candies -= index+1;
                index++;
            }

        }
        return result;
    }

    private int next(int index,int sum) {

        return index %sum;
    }
}

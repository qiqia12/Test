package com.qiqi.problem2;

import java.util.Arrays;

/**
 * @projectName: Test
 * @package: com.qiqi.problem2
 * @className: Day05
 * @author: Eric
 * @description: TODO
 * @date: 2023/7/10 21:31
 * @version: 1.0
 */
public class Day05 {
    public int threeSumClosest(int[] nums, int target) {
        if (nums.length == 3) return Arrays.stream(nums).sum();
        Arrays.sort(nums);
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length - 2; i++) {
            int left = i+1;
            int right = nums.length-1;
            while(left < right){
                int cur = nums[i] + nums[left] + nums[right];
                result = Math.abs(target-cur)<Math.abs(target-result)?cur:result;
                if (result == target) return result;
                int num = cur<target?left++:right--;
            }
        }
        return result;
    }

    private int fun(int target, int cur, int result) {
        return Math.abs(target-cur)<Math.abs(target-result)?cur:result;
    }

}

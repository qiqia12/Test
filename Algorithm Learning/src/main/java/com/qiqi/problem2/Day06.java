package com.qiqi.problem2;

/**
 * @projectName: Test
 * @package: com.qiqi.problem2
 * @className: Day06
 * @author: Eric
 * @description: TODO
 * @date: 2023/7/11 22:49
 * @version: 1.0
 */
public class Day06 {

    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;

        long[] f = new long[n + 1];

        long[] g = new long[n + 1];

        for (int i = 1; i <= n; ++i) {

            f[i] = Math.max(g[i - 1] - nums[i - 1], f[i - 1]);

            g[i] = Math.max(f[i - 1] + nums[i - 1], g[i - 1]);

        }

        return Math.max(f[n], g[n]);
    }

}

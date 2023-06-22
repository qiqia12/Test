package com.qiqi.problem2;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * @projectName: Test
 * @package: com.qiqi.problem2
 * @className: Day04
 * @author: Eric
 * @description: TODO
 * @date: 2023/6/18 20:25
 * @version: 1.0
 */
public class Day04 {
    class Solution {

        public int closedIsland(int[][] grid) {
            int m = grid.length, n = grid[0].length;
            if (m < 3 || n < 3) return 0;
            for (int i = 0; i < m; i++) {
                int step = i == 0 || i == m - 1 ? 1 : n - 1;
                for (int j = 0; j < n; j += step)
                    dfs(grid, i, j);
            }

            int ans = 0;
            for (int i = 1; i < m - 1; i++) {
                for (int j = 1; j < n - 1; j++) {
                    if (grid[i][j] == 0) { // 从没有访问过的 0 出发
                        ans++; // 一定是封闭岛屿
                        dfs(grid, i, j);
                    }
                }
            }
            return ans;
        }



        private void dfs(int[][] grid, int x, int y) {
            if (x < 0 || x >= grid.length || y < 0 || y >= grid[x].length || grid[x][y] != 0)
                return;
            grid[x][y] = 1; // 标记 (x,y) 被访问，避免重复访问
            dfs(grid, x - 1, y);
            dfs(grid, x + 1, y);
            dfs(grid, x, y - 1);
            dfs(grid, x, y + 1);

        }
    }
    public int maxSumDivThree(int[] nums) {
        int n = nums.length;

        int[][] f = new int[n + 1][3];

        f[0][1] = f[0][2] = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++)

            for (int j = 0; j < 3; j++)

                f[i + 1][j] = Math.max(f[i][j], f[i][(j + nums[i]) % 3] + nums[i]);

        return f[n][0];
    }
    public int[] pondSizes(int[][] land) {
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < land.length; i++) {
            for (int j = 0; j < land[0].length; j++) {
                if (land[i][j] == 0){
                    list.add(process(land,i,j));
                }
            }
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        Arrays.sort(res);
        return res;
    }

    private int process(int[][] land, int i, int j) {
        if (i<0 || i>land.length-1 || j<0 || j>land[0].length) return 0;
        if (land[i][j]!=0) return 0;
        land[i][j] = 1;
        return 1+process(land,i-1,j)+process(land, i+1, j)+process(land, i, j-1)+process(land, i, j+1)
                +process(land,i-1,j+1)+process(land, i+1, j-1)+process(land, i+1, j+1)+process(land, i-1, j-1);
    }


}

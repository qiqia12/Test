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
    public int maximumValue(String[] strs) {
        int result = 0;
        for (String str : strs) {
            int num = 0;
            try{
                 num = Integer.parseInt(str);
            }catch (Exception e){
                num = str.length();
            }
            result = Math.max(num,result);
        }
        return result;
    }
    private int m;

    private int mx;

    private int[] f;

    private int[][] g;

    private int[][] bits;

    private int[] ix;

    private int[] ex;

    private Integer[][][][] memo;

    private final int[][] h = {{0, 0, 0}, {0, -60, -10}, {0, -10, 40}};



    public int getMaxGridHappiness(int m, int n, int introvertsCount, int extrovertsCount) {

        this.m = m;

        mx = (int) Math.pow(3, n);

        f = new int[mx];

        g = new int[mx][mx];

        bits = new int[mx][n];

        ix = new int[mx];

        ex = new int[mx];

        memo = new Integer[m][mx][introvertsCount + 1][extrovertsCount + 1];

        for (int i = 0; i < mx; ++i) {

            int mask = i;

            for (int j = 0; j < n; ++j) {

                int x = mask % 3;

                mask /= 3;

                bits[i][j] = x;

                if (x == 1) {

                    ix[i]++;

                    f[i] += 120;

                } else if (x == 2) {

                    ex[i]++;

                    f[i] += 40;

                }

                if (j > 0) {

                    f[i] += h[x][bits[i][j - 1]];

                }

            }

        }

        for (int i = 0; i < mx; ++i) {

            for (int j = 0; j < mx; ++j) {

                for (int k = 0; k < n; ++k) {

                    g[i][j] += h[bits[i][k]][bits[j][k]];

                }

            }

        }

        return dfs(0, 0, introvertsCount, extrovertsCount);

    }



    private int dfs(int i, int pre, int ic, int ec) {

        if (i == m || (ic == 0 && ec == 0)) {

            return 0;

        }

        if (memo[i][pre][ic][ec] != null) {

            return memo[i][pre][ic][ec];

        }

        int ans = 0;

        for (int cur = 0; cur < mx; ++cur) {

            if (ix[cur] <= ic && ex[cur] <= ec) {

                ans = Math.max(ans, f[cur] + g[pre][cur] + dfs(i + 1, cur, ic - ix[cur], ec - ex[cur]));

            }

        }

        return memo[i][pre][ic][ec] = ans;

    }


    public static boolean checkOverlap(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                if (pro(radius,xCenter,yCenter,i,j)){
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean pro(int radius, int xCenter, int yCenter, int i, int j) {

        return radius > Math.sqrt(Math.pow(Math.abs(xCenter-i),2)+Math.pow(Math.abs(yCenter-j),2));
    }

    public static void main(String[] args) {
        maximumSum(new int[]{1,-2,0,3});
    }
    public static int maximumSum(int[] arr) {
        int ans = Integer.MIN_VALUE / 2, f0 = ans, f1 = ans;

        for (int x : arr) {

            f1 = Math.max(f1 + x, f0);

            f0 = Math.max(f0, 0) + x;

            ans = Math.max(ans, Math.max(f0, f1));

        }

        return ans;

    }
    public int minimumIncompatibility(int[] nums, int k) {
        int n = nums.length;

        int m = n / k;

        int[] g = new int[1 << n];

        Arrays.fill(g, -1);

        for (int i = 1; i < 1 << n; ++i) {

            if (Integer.bitCount(i) != m) {

                continue;

            }

            Set<Integer> s = new HashSet<>();

            int mi = 20, mx = 0;

            for (int j = 0; j < n; ++j) {

                if ((i >> j & 1) == 1) {

                    if (!s.add(nums[j])) {

                        break;

                    }

                    mi = Math.min(mi, nums[j]);

                    mx = Math.max(mx, nums[j]);

                }

            }

            if (s.size() == m) {

                g[i] = mx - mi;

            }

        }

        int[] f = new int[1 << n];

        final int inf = 1 << 30;

        Arrays.fill(f, inf);

        f[0] = 0;

        for (int i = 0; i < 1 << n; ++i) {

            if (f[i] == inf) {

                continue;

            }

            Set<Integer> s = new HashSet<>();

            int mask = 0;

            for (int j = 0; j < n; ++j) {

                if ((i >> j & 1) == 0 && !s.contains(nums[j])) {

                    s.add(nums[j]);

                    mask |= 1 << j;

                }

            }

            if (s.size() < m) {

                continue;

            }

            for (int j = mask; j > 0; j = (j - 1) & mask) {

                if (g[j] != -1) {

                    f[i | j] = Math.min(f[i | j], f[i] + g[j]);

                }

            }

        }

        return f[(1 << n) - 1] == inf ? -1 : f[(1 << n) - 1];
    }

}

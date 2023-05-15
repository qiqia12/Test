package com.qiqi.algorithm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @className: Day03
 * @author: Eric
 * @date: 2023/5/15 19:41
 * @version: 1.0
 */
public class Day03 {


    public int maxEqualRowsAfterFlips(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int [][] copy = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                copy[i][j] = matrix[i][j] == 0 ?1:0;
            }
        }
        int [] dp = new int[m];
        Arrays.fill(dp,1);
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < i; j++) {
                if (isEmp(matrix,i,copy,j,n) || isEmp(matrix,i,matrix,j,n)){
                    dp[i] = Math.max(dp[i],dp[j]+1);
                }
            }
        }
        return Arrays.stream(dp).max().getAsInt();
    }

    private boolean isEmp(int[][] matrix, int i, int[][] matrix1, int j, int n) {
        for (int k = 0; k < n; k++) {
            if (matrix[i][k] != matrix1[j][k]) return false;
        }
        return true;
    }


    public int maxEqualRowsAfterFlips1(int[][] matrix) {
        Map<String, Integer> cnt = new HashMap<>();
        int ans = 0, n = matrix[0].length;
        for (int[] row : matrix) {
            char[] cs = new char[n];
            for (int i = 0; i < n; ++i) {
                cs[i] = (char) (row[0] ^ row[i]);
            }
            ans = Math.max(ans, cnt.merge(String.valueOf(cs), 1, Integer::sum));
        }
        return ans;
    }
}

package main;

import template.linear_algebra.MatrixUtils;

import java.util.Arrays;

public class RecurrenceRelation {
    public int moduloTen(int[] coefficients, int[] initial, int N) {
        int m = initial.length;
        if (N < m)
            return modTen(initial[N]);
        int[][] W = new int[m][m];
        for (int i = 0; i < m; ++i) {
            W[i][m - 1] = coefficients[i];
            if (i < m - 1)
                W[i + 1][i] = 1;
        }
        int[][] pW = pow(W, N - m + 1);
        int ans = 0;
        for (int i = 0; i < m; ++i)
            ans = modTen(ans + modTen(initial[i] * pW[i][m - 1]));
        return ans;
    }

    int[][] pow(int[][] W, int p) {
        int n = W.length;
        int[][] res = new int[n][n];
        for (int i = 0; i < n; ++i)
            res[i][i] = 1;
        while (p > 0) {
            if ((p & 1) > 0) {
                res = dot(res, W);
            }
            W = dot(W, W);
            p >>= 1;
        }
        return res;
    }

    int[][] dot(int[][] x, int[][] y) {
        int n = x.length;
        int[][] z = new int[n][n];
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < n; ++j)
                for (int k = 0; k < n; ++k)
                    z[i][j] = modTen(z[i][j] + modTen(x[i][k] * y[k][j]));
        return z;
    }

    int modTen(int x) {
        return x > 0 ? x % 10 : (10 - -x % 10) % 10;
    }
}

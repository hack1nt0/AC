package main;

import java.util.Arrays;

public class Pricing {
    public int maxSales(int[] price) {
        int N = price.length;
        int M = 4;
        int[][] dp = new int[M + 1][N + 1];
        //(M, x) = 0, (x, N) = 0
        for (int i = 0; i < N + 1; ++i) dp[M][i] = 0;
        for (int i = 0; i < M + 1; ++i) dp[i][N] = 0;

        Arrays.sort(price);

        for (int m = M - 1; m >= 0; --m)
            for (int n = 0; n <= N; ++n) {
                int mn = 0;
                for (int k = 0; n + k <= N; ++k) {
                    int mnk = maxSegSales(n, n + k, price);
                    mnk += dp[m + 1][n + k];
                    mn = Math.max(mn, mnk);
                }
                dp[m][n] = mn;
            }

        int ans = dp[0][0];
        return ans;
    }

    private int maxSegSales(int L, int R, int[] price) {
        int P = -1;
        int res = 0;
        for (int i = L; i < R; ++i) {
            if (price[i] == P) continue;
            P = price[i];
            res = Math.max(res, P * (R - i));
        }
        return res;
    }
}

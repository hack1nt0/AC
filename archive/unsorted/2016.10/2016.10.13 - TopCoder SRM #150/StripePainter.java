package main;

public class StripePainter {
    public int minStrokes(String stripes) {
        int N = stripes.length();
        int[][] dp = new int[N + 1][N + 1];
        for (int i = 0; i < dp.length - 1; ++i) dp[i][i + 1] = 1;
        String[][] strategy = new String[N + 1][N + 1];
        for (int i = 0; i < strategy.length; ++i) strategy[i][i] = "";

        for (int len = 2; len <= N; ++len)
            for (int L = 0; L + len <= N; ++L) {
                int R = L + len;
                int res = N;
                for (int m = L + 1; m < R; ++m) {
                    int candRes = dp[L][m] + dp[m][R];
                    if (candRes < res) {
                        res = candRes;
                    }
                }
                if (stripes.charAt(L) == stripes.charAt(R - 1))
                res = Math.min(res, dp[L][R - 1]);
                dp[L][R] = res;
            }
        System.out.println(strategy[0][N]);
        System.out.println(dp[0][N]);

        int[][][] memo = new int[N + 1][N + 1]['Z' + 1];
        String[][][] strategy1 = new String[N + 1][N + 1]['Z' + 1];
        for (int i = 0; i <= N; ++i)
            for (int c = 0; c <= 'Z'; ++c) strategy1[i][i][c] = "";
        for (int len = 1; len <= N; ++len)
            for (int l = 0; l + len <= N; ++l) {
                int r = l + len;
                for (int c = 0; c <= 'Z'; ++c) {
                    int res = N;
                    char cl = stripes.charAt(l);
                    String stra = null;
                    if (c == cl) {
                        res = Math.min(res, memo[l + 1][r][c]);
                        stra = strategy1[l + 1][r][c];
                    }
                    else {
                        for (int m = l + 1; m < r; ++m) {
                            int ress = memo[l][m][cl] + memo[m][r][c] + 1;
                            if (ress < res) {
                                res = ress;
                                stra = l + "-->" + m + ":" + cl;
                                stra += "\n";
                                stra += strategy1[l][m][cl] + strategy1[m][r][c];
                            }
                        }
                        int ress = 1 + memo[l + 1][r][cl];
                        if (ress < res) {
                            res = ress;
                            stra = l + "-->" + r + ":" + cl;
                            stra += "\n";
                            stra += strategy1[l + 1][r][cl];
                        }
                    }
                    memo[l][r][c] = res;
                    strategy1[l][r][c] = stra;
                }
            }
        System.out.println(strategy1[0][N][0]);
        System.out.println(memo[0][N][0]);
        return dp[0][N];
    }
}

package main;

public class TrySail {
    public int get(int[] strength) {
        int tot = 0;
        for (int str: strength) tot ^= str;
        int MAXSTR = 255 + 1;
        int N = strength.length;
        int[][][] dp = new int[N + 1][MAXSTR][MAXSTR];
        //(i, j, k) = max((i-1, j, k), (i-1, j^str(i), k), (i-1, j, k^str(i))
        for (int j = 0; j < MAXSTR; ++j)
            for (int k = 0; k < MAXSTR; ++k)
                dp[N][j][k] = (tot ^ j ^ k) + j + k;

        for (int i = N - 1; i >= 0; --i)
            for (int j = 0; j < MAXSTR; ++j)
                for (int k = 0; k < MAXSTR; ++k) {
                    int res = dp[i + 1][j][k];
                    res = Math.max(res, dp[i + 1][j ^ strength[i]][k]);
                    res = Math.max(res, dp[i + 1][j][k ^ strength[i]]);
                    dp[i][j][k] = res;
                }
        int res = dp[0][0][0];
        return res;
    }
}

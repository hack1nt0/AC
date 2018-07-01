package main;

public class DiversePairsDiv1 {
    public int[] findMaxDiversePairs(int a, int b) {

        int maxLen = b - a + 1;
        int[][][] dp = new int[maxLen + 1][maxLen + 1][2];
        for (int len = 1; len <= maxLen; ++len) {
            for (int from = 0; from + len <= maxLen; ++from) {
                int to = from + len;
                for (int include = 0; include <= 1; ++include) {
                    int res = 0;
                    res = Math.max(res, dp[from][to - 1][1]);
                    res = Math.max(res, dp[from + 1][to][0]);
                    if (from + 1 <= to - 1) {
                        if (include == 1) {
                            res = Math.max(res, 1 + dp[from + 1][to - 1][0]);
                            res = Math.max(res, dp[from + 1][to - 1][1]);
                        }
                        else
                            res = Math.max(res, dp[from + 1][to - 1][0]);
                    }
                    dp[from][to][include] = res;
                }
            }
        }
        int max = dp[0][maxLen][0];
        return new int[]{max};
    }
}

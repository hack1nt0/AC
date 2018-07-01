package main;

public class Assemble {
    public int minCost(int[] connect) {
        int n = connect.length - 1;
        long[][] dp = new long[n + 1][n + 1];
        for (int o = 1; o <= n; ++o) {
            for (int s = 0; s + o <= n; ++s) {
                int t = s + o;
                long res = Long.MAX_VALUE;
                if (o == 1)
                    res = 0;
                else {
                    for (int mid = s + 1; mid < t; ++mid) {
                        long iA = connect[s];
                        long sA = mid - s;
                        long oA = connect[mid];
                        long iB = connect[mid];
                        long sB = t - mid;
                        long oB = connect[t];
                        res = Math.min(res, dp[s][mid] + dp[mid][t] + (iA + sA) * oA * (oB + sB));
                    }
                }
                dp[s][t] = res;
            }
        }
        return (int) dp[0][n];
    }
}

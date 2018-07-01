package main;

import template.debug.InputReader;
import template.debug.OutputWriter;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long MOD = 998244353;
        int n = in.readInt();
        int[] input = new int[n];
        for (int i = 0; i < n; ++i)
            input[i] = in.readInt();
        long[][] comb = new long[n][n];
        for (int i = 0; i < n; ++i)
            for (int j = 0; j <= i; ++j) {
                long res = -1;
                if (i == j || j == 0)
                    res = 1;
                else
                    res = (comb[i - 1][j - 1] + comb[i - 1][j]) % MOD;
                comb[i][j] = res;
            }
        long[] dp = new long[n + 1];
        for (int i = n - 1; i >= 0; --i) {
            long res = dp[i + 1];
            if (input[i] > 0) {
                for (int j = i + input[i]; j < n; ++j) {
                    res = (res + (comb[j - i - 1][input[i] - 1] * ((dp[j + 1] + 1L) % MOD)) % MOD) % MOD;
                }
            }
            dp[i] = res;
        }
        out.printLine(dp[0]);
    }
}

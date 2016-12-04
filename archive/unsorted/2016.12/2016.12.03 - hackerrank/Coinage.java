package main;

import java.util.Scanner;
import java.io.PrintWriter;

public class Coinage {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int N = in.nextInt();
        int[] coins = new int[]{1, 2, 5, 10};
        int[] K = new int[coins.length];
        for (int i = 0; i < coins.length; ++i) K[i] = in.nextInt();
        long[][] dp = new long[coins.length + 1][N + 1];
        for (int i = 0; i <= N; ++i) dp[1][i] = i <= K[0] ? 1 : 0;
        for (int i = 1; i < coins.length; ++i)
            for (int j = 0; j <= N; ++j) {
                long res = 0;
                for (int k = 0; k <= K[i]; ++k) if (j >= k * coins[i])
                    res += dp[i][j - k * coins[i]];

                dp[i + 1][j] = res;
            }
        long res = dp[coins.length][N];
        out.println(res);
    }
}

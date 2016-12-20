package main;

import java.util.Scanner;
import java.io.PrintWriter;

/**
 * listDp, but with greed on nodes' decision(policy).
 */
public class SherlockAndCost {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int N = in.nextInt();
        int[] B = new int[N];
        for (int i = 0; i < N; ++i) B[i] = in.nextInt();
        int[][] dp = new int[N][2];
        for (int i = 0; i < N - 1; ++i) {
            dp[i + 1][0] = Math.max(dp[i][0], dp[i][1] + B[i] - 1);
            dp[i + 1][1] = Math.max(dp[i][0] + B[i + 1] - 1, dp[i][1] + Math.abs(B[i + 1] - B[i]));
        }
        out.println(Math.max(dp[N - 1][0], dp[N - 1][1]));
    }
}

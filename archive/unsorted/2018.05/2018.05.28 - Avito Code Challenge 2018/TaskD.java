package main;

import scala.Array;

import java.util.Arrays;
import java.util.Scanner;
import java.io.PrintWriter;

public class TaskD {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int N = in.nextInt();
        int K = in.nextInt();
        int B = 60;
        long[] prices = new long[N];
        for (int i = 0; i < N; ++i)
            prices[i] = in.nextLong();
        int[][] dp = new int[K + 1][N + 1];
        long prefix = 0;
        for (int b = B - 1; b >= 0; --b) {
            dp[0][0] = 1;
            for (int i = 1; i <= K; ++i) {
                for (int j = i; j <= N; ++j) {
                    int res = 0;
                    long sum = 0;
                    for (int used = 1; used <= j; ++used) {
                        sum += prices[j - used];
                        if ((sum & prefix) == prefix) {
                            int bit = (int) (sum >> b & 1);
                            res |= dp[i - 1][j - used] & bit;
                        }
                    }
                    dp[i][j] = res;
                }
            }
            prefix |= (long)(dp[K][N]) << b;
        }
        long max = prefix;
        out.println(max);
    }
}

package main;

import java.util.Scanner;
import java.io.PrintWriter;
/*
 ID: hackint1
 LANG: JAVA
 TASK: subset
*/
public class SubsetSum {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int maxSum = n * (n + 1) / 2;
        int maxHalf = maxSum / 2;
        if (maxHalf * 2 != maxSum) {
            out.println(0);
            return;
        }
        long[][] dp = new long[n + 2][maxHalf + 1];
        dp[n + 1][maxHalf] = 1;
        for (int i = n; i > 0; --i) {
            int accSum = i * (i - 1) / 2;
            for (int s1 = 0; s1 <= Math.min(accSum, maxHalf); ++s1) {
                int s2 = accSum - s1;
                long res = 0;
                int ns1 = s1 + i;
                if (ns1 <= maxHalf && s2 <= maxHalf) {
                    res += dp[i + 1][ns1];
                }
                int ns2 = s2 + i;
                if (s1 <= maxHalf && ns2 <= maxHalf) {
                    res += dp[i + 1][s1];
                }
                dp[i][s1] = res;
            }
        }

        out.println(dp[1][0] / 2);
    }
}

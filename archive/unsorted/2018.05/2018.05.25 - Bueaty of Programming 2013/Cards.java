package main;

import java.util.Arrays;
import java.util.Scanner;
import java.io.PrintWriter;

public class Cards {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int nkind = in.nextInt();
        int N = nkind * 2;
        int K = in.nextInt();
        double[][] dp = new double[N + 1][K + 1];
        double oo = Double.MAX_VALUE;
        Arrays.fill(dp[0], oo);
        dp[0][0] = 0;
        for (int left = 2; left <= N; left += 2) {
            for (int mem = Math.min(left / 2, Math.min(nkind, K)); mem >= 0; --mem) {
                double res = 0;

                double prob = 1;
                int nmem = mem;
                if (nmem < K) {
                    prob *= (double)nmem / left; // TODO: 2018/5/25 BUG
                    nmem++;
                } else {
                    prob *= (double) (nmem - 1) / left;
                }
                nmem -= 2;
                if (nmem >= 0 && dp[left - 2][nmem] != oo)
                    res += prob * (2 + dp[left - 2][nmem]);

                nmem = mem;
                prob = 1;
                if (nmem < K) {
                    prob *= (double)(left - nmem) / left;
                    nmem++;
                } else
                    prob *= (double)(left - nmem + 1) / left;
                if (nmem < K) {
                    prob *= (double)nmem / left;
                    nmem++;
                } else
                    prob *= (double)(nmem - 1) / left;
                nmem -= 2;
                if (nmem >= 0 && dp[left - 2][nmem] != oo)
                    res += prob * (4 + dp[left - 2][nmem]);

                prob = 1;
                nmem = mem;
                if (nmem < K) {
                    prob *= (double)(left - nmem) / left;
                    nmem++;
                } else
                    prob *= (double)(left - nmem + 1) / left;
                prob *= 1. / left;
                nmem -= 2;
                if (nmem >= 0 && dp[left - 2][nmem] != oo)
                    res += prob * (2 + dp[left - 2][nmem]);

                prob = 1;
                nmem = mem;
                if (nmem < K) {
                    prob *= (double)(left - nmem) / left;
                    nmem++;
                } else
                    prob *= (double)(left - nmem + 1) / left;
                if (nmem < K) {
                    prob *= (double)(left - nmem) / left;
                    nmem++;
                } else
                    prob *= (double)(left - nmem + 1) / left;

                if (nmem == mem) {
                    res = (prob * 2 + res) / (1 - prob);
                    if (prob == 1)
                        res = oo;
                } else {
                    if (nmem < mem) throw new RuntimeException();
                    if (dp[left][nmem] != oo)
                        res += prob * (2 + dp[left][nmem]);
                }

                if (res == 0)
                    res = oo;
                dp[left][mem] = res;
            }
        }

        double ans = dp[N][0];
        out.println("Case #" + testNumber + ": " + ans);
    }
}

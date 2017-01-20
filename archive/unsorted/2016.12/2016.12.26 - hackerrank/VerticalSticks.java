package main;

import java.util.Arrays;
import java.util.Scanner;
import java.io.PrintWriter;

/**
 *  Linearity of Expectation: group with every yi. When calculate E[vi], there is a formulation.
 *
 *  WA: dont figure it out.
 */
public class VerticalSticks {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int N = in.nextInt();
        int[] y = new int[N];
        for (int i = 0; i < N; ++i) y[i] = -in.nextInt();
        Arrays.sort(y);
        for (int i = 0; i < N; ++i) y[i] = -y[i];

        double[] dp = new double[N + 1];
        for (int i = 0; i < N; ++i)
            dp[i + 1] = dp[i] + ((i + 1) * i / 2 + (i + 1)) / (i + 1.0);

        out.printf("%.2f\n", dp[N]);
    }
}

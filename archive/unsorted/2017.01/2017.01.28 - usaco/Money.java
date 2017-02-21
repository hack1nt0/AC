package main;

import java.util.Arrays;
import java.util.Scanner;
import java.io.PrintWriter;
/*
 ID: hackint1
 LANG: JAVA
 TASK: money
*/
public class Money {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int v = in.nextInt();
        int n = in.nextInt();
        int[] coins = new int[v];
        for (int i = 0; i < v; ++i) coins[i] = in.nextInt();
        long[][] ways = new long[n + 1][v];
        Arrays.fill(ways[0], 1);
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j < v; ++j) {
                if (j >=        1) ways[i][j] += ways[i][j - 1];
                if (i >= coins[j]) ways[i][j] += ways[i - coins[j]][j];
            }
        }

        out.println(ways[n][v - 1]);
    }
}

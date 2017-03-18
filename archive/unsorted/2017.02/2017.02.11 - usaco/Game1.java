package main;

import java.util.Scanner;
import java.io.PrintWriter;

/*
 ID: hackint1
 LANG: JAVA
 TASK: game1
*/

public class Game1 {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; ++i) arr[i] = in.nextInt();

        long[][][] sum = new long[n + 1][n + 1][2];
        for (int len = 1; len <= n; ++len) {
            for (int from = 0; from + len <= n; ++from) {
                int to = from + len;
                if (sum[from + 1][to][0] < sum[from][to - 1][0]) {
                    sum[from][to][0] = sum[from + 1][to][1] + arr[from];
                    sum[from][to][1] = sum[from + 1][to][0];
                } else {
                    sum[from][to][0] = sum[from][to - 1][1] + arr[to - 1];
                    sum[from][to][1] = sum[from][to - 1][0];
                }
            }
        }
        long sum1 = sum[0][n][0];
        long sum2 = sum[0][n][1];
        out.println(sum1 + " " + sum2);
    }
}

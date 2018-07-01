package main;

import template.debug.InputReader;
import template.debug.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        int[] program = new int[n + 2];
        for (int i = 1; i <= n; ++i)
            program[i] = in.readInt();
        program[n + 1] = m;
        int[][] dp = new int[n + 2][2];
        for (int i = n; i >= 0; --i) {
            dp[i][0] = dp[i + 1][1];
            dp[i][1] = program[i + 1] - program[i] + dp[i + 1][0];
        }
        int max = 0;
        int prefix = 0;
        for (int i = 0; i <= n; ++i) {
            int os = (i % 2) ^ 1;
            if (program[i] + 1 < program[i + 1]) {
                if (os == 0)
                    max = Math.max(max, prefix + program[i + 1] - program[i] - 1 + dp[i + 1][0]);
                else
                    max = Math.max(max, prefix + program[i + 1] - program[i] - 1 + dp[i + 1][1]);
            }
            if (os == 1)
                prefix += program[i + 1] - program[i];
        }
        out.printLine(Math.max(max, prefix));
    }
}

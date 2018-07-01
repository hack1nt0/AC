package main;

import template.debug.InputReader;
import template.debug.OutputWriter;

import java.util.Arrays;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = 2;
        char[][] board = new char[n][];
        for (int i = 0; i < n; ++i)
            board[i] = in.readString().toCharArray();
        int m = board[0].length;
        int[][][] dp = new int[m][2][2];
        for (int i = m - 2; i >= 0; --i) {
            for (int f = 0; f < 2; ++f) {
                for (int s = 0; s < 2; ++s) {
                    int res = 0;
                    if (f == 0 && s == 0 && board[0][i] == '0' && board[1][i] == '0' && board[0][i + 1] == '0')
                        res = Math.max(res, 1 + dp[i + 1][1][0]);
                    if (f == 0 && board[0][i] == '0' && board[0][i + 1] == '0' && board[1][i + 1] == '0')
                        res = Math.max(res, 1 + dp[i + 1][1][1]);
                    if (s == 0 && board[1][i] == '0' && board[0][i + 1] == '0' && board[1][i + 1] == '0')
                        res = Math.max(res, 1 + dp[i + 1][1][1]);
                    if (f == 0 && s == 0 && board[0][i] == '0' && board[1][i] == '0' && board[1][i + 1] == '0')
                        res = Math.max(res, 1 + dp[i + 1][0][1]);
                    if (res == 0)
                        res = dp[i + 1][0][0];
                    dp[i][f][s] = res;
                }
            }
        }
        int max = dp[0][0][0];
        out.printLine(max);
    }
}

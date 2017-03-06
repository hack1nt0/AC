package main;

import template.collection.sequence.ArrayUtils;
import template.debug.InputReader;
import java.io.PrintWriter;
import java.util.Arrays;

/*
 ID: hackint1
 LANG: JAVA
 TASK: bigbrn
*/

public class Bigbrn {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.readInt();
        char[][] farm = new char[n][n];
        for (int i = 0; i < n; ++i) Arrays.fill(farm[i], '.');
        int trees = in.readInt();
        for (int i = 0; i < trees; ++i) {
            int r = in.readInt() - 1;
            int c = in.readInt() - 1;
            farm[r][c] = '#';
        }
        int[][] maxLen = new int[n][n];
        int[][] maxRow = new int[n][n];
        int[][] maxCol = new int[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (farm[i][j] == '.') {
                    maxRow[i][j] = 1;
                    if (j > 0) maxRow[i][j] += maxRow[i][j - 1];
                    maxCol[i][j] = 1;
                    if (i > 0) maxCol[i][j] += maxCol[i - 1][j];
                    maxLen[i][j] = 1;
                    if (i > 0 && j > 0) {
                        maxLen[i][j] = 1 + maxLen[i - 1][j - 1];
                        maxLen[i][j] = Math.min(maxLen[i][j], maxRow[i][j]);
                        maxLen[i][j] = Math.min(maxLen[i][j], maxCol[i][j]);
                    }
                }
            }
        }

        int max = 0;
        for (int i = 0; i < n; ++i) max = Math.max(max, ArrayUtils.max(maxLen[i]));
        out.println(max);
    }
}

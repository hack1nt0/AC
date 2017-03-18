package main;

import template.collection.sequence.ArrayUtils;
import template.debug.InputReader;
import java.io.PrintWriter;
import java.util.Arrays;

/*
 ID: hackint1
 LANG: JAVA
 TASK: rectbarn
*/

public class Rectbarn {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int R = in.readInt();
        int C = in.readInt();
        int bans = in.readInt();
        boolean[][] field = new boolean[R][C];
        for (int i = 0; i < R; ++i) Arrays.fill(field[i], true);
        for (int i = 0; i < bans; ++i) {
            int rr = in.readInt() - 1;
            int cc = in.readInt() - 1;
            field[rr][cc] = false;
        }

        int[][] height = new int[R][C];
        int maxArea = 0;
        int[] left = new int[C];
        left[0] = -1;
        int[] right = new int[C];
        right[C - 1] = C;
        for (int r = 0; r < R; ++r) {
            for (int c = 0; c < C; ++c) {
                if (field[r][c]) {
                    height[r][c] = 1;
                    if (r > 0) height[r][c] += height[r - 1][c];
                }
            }
            for (int c = 1; c < C; ++c) {
                if (height[r][c - 1] < height[r][c]) left[c] = c - 1;
                else {
                    int p = left[c - 1];
                    while (true) {
                        if (p < 0 || height[r][p] < height[r][c]) break;
                        p = left[p];
                    }
                    left[c] = p;
                }
            }
            for (int c = C - 2; c >= 0; --c) {
                if (height[r][c] > height[r][c + 1]) right[c] = c + 1;
                else {
                    int p = right[c + 1];
                    while (true) {
                        if (p >= C || height[r][c] > height[r][p]) break;
                        p = right[p];
                    }
                    right[c] = p;
                }
            }
            for (int c = 0; c < C; ++c) {
                int minHeight = height[r][c];
                int area = minHeight * (right[c] - left[c] - 1);
                maxArea = Math.max(maxArea, area);
            }
        }

        out.println(maxArea);
    }
}

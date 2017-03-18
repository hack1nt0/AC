package main;

import java.util.Scanner;
import java.io.PrintWriter;

/*
 ID: hackint1
 LANG: JAVA
 TASK: range
*/

public class Range {

    public void solve1(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int[][] land = new int[n][n];
        for (int i = 0; i < n; ++i) {
            char[] line = in.next().toCharArray();
            for (int j = 0; j < n; ++j) land[i][j] = line[j] - '0';
        }

        int[][] maxRight = new int[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = n - 1; j >= 0; --j) {
                if (land[i][j] == 1) {
                    maxRight[i][j] = 1;
                    if (j + 1 < n) maxRight[i][j] += maxRight[i][j + 1];
                }
            }
        }

        int[] nSquare =  new int[n + 1];

        for (int j = 0; j < n; ++j) {
            for (int i = 0; i < n; ++i) {
                int extend = maxRight[i][j];
                for (int k = i + 1; k < n; ++k) {
                    extend = Math.min(extend, maxRight[k][j]);
                    if (extend < k - i + 1) break;
                    nSquare[k - i + 1]++;
                }
            }
        }

        for (int i = 2; i <= n; ++i) {
            if (nSquare[i] == 0) continue;
            out.println(i + " " + nSquare[i]);
        }
    }


    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int[][] land = new int[n][n];
        for (int i = 0; i < n; ++i) {
            char[] line = in.next().toCharArray();
            for (int j = 0; j < n; ++j) land[i][j] = line[j] - '0';
        }

        int[][] maxSquare = new int[n][n];
        int[][] maxLeft = new int[n][n];
        int[][] maxUpper = new int[n][n];
        int[] nSquare =  new int[n + 1];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (land[i][j] == 1) {
                    maxLeft[i][j] = 1;
                    if (0 <= j - 1) maxLeft[i][j] += maxLeft[i][j - 1];
                }
                if (land[i][j] == 1) {
                    maxUpper[i][j] = 1;
                    if (0 <= i - 1) maxUpper[i][j] += maxUpper[i - 1][j];
                }
                maxSquare[i][j] = Math.min(maxLeft[i][j], maxUpper[i][j]);
                if (0 <= i - 1 && 0 <= j - 1) maxSquare[i][j] = Math.min(maxSquare[i][j], maxSquare[i - 1][j - 1] + 1);
                for (int k = 2; k <= maxSquare[i][j]; ++k) nSquare[k]++;
            }
        }

        for (int i = 2; i <= n; ++i) {
            if (nSquare[i] == 0) continue;
            out.println(i + " " + nSquare[i]);
        }
    }
}

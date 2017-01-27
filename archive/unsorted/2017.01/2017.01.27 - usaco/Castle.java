package main;

import template.collection.sequence.ArrayUtils;

import java.util.Scanner;
import java.io.PrintWriter;
/*
 ID: hackint1
 LANG: JAVA
 TASK: castle
*/
public class Castle {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int m = in.nextInt();
        int n = in.nextInt();
        int[][] map = new int[n][m];
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < m; ++j) map[i][j] = in.nextInt();
        int dn = 4;
        int[] di = new int[]{0, -1, 0, +1};
        int[] dj = new int[]{-1, 0, +1, 0};
        int[][] comp = new int[n][m];
        int compN = 0;
        int[] compSize = new int[n * m];
        ArrayUtils.fill(comp, -1);
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (comp[i][j] != -1) continue;

                comp[i][j] = compN;
                compSize[compN] = 1;
                while (true) {
                    boolean updated = false;
                    for (int k = 0; k < n; ++k)
                        for (int l = 0; l < m; ++l) {
                            if (comp[k][l] != compN) continue;
                            for (int d = 0; d < dn; ++d) {
                                if ((map[k][l] >> d & 1) != 0) continue;
                                int ni = k + di[d];
                                int nj = l + dj[d];
                                //if (!valid(ni, nj, n, m)) continue;
                                if (comp[ni][nj] == -1) {
                                    updated = true;
                                    comp[ni][nj] = compN;
                                    compSize[compN]++;
                                }
                            }
                        }
                    if (!updated) break;
                }
                compN++;
            }
        }

        int mergedLargest = 0;
        int removedi, removedj, removedWall;
        removedi = removedj = removedWall = -1;
        for (int j = 0; j < m; ++j) {
            for (int i = n - 1; i >= 0; --i) {
                for (int d = 1; d <= 2; ++d) {
                    //if ((map[i][j] >> d & 1) != 0) continue;
                    int ni = i + di[d];
                    int nj = j + dj[d];
                    if (!check(ni, 0, n) || !check(nj, 0, m)) continue;

                    int merged = compSize[comp[i][j]] + compSize[comp[ni][nj]];
                    if (comp[ni][nj] == comp[i][j]) merged /= 2;
                    if (merged > mergedLargest) {
                        removedi = i;
                        removedj = j;
                        removedWall = d;
                        mergedLargest = merged;
                    }
                }
            }
        }

        out.println(compN);
        out.println(ArrayUtils.max(compSize));
        out.println(mergedLargest);
        out.println((removedi + 1) + " " + (removedj + 1) + " " + "WNES".charAt(removedWall));
    }

    private boolean check(int i, int from, int to) {
        return from <= i && i < to;
    }
}

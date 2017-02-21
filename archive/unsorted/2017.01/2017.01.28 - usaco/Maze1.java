package main;

import template.collection.sequence.ArrayUtils;

import java.util.Scanner;
import java.io.PrintWriter;

/*
 ID: hackint1
 LANG: JAVA
 TASK: maze1
*/
public class Maze1 {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int w = in.nextInt();
        int h = in.nextInt();
        char[][] fence = new char[2 * h + 1][];
        in.nextLine();
        for (int i = 0; i < fence.length; ++i) fence[i] = in.nextLine().toCharArray();
        int dirN = 4;
        boolean[][][] maze = new boolean[h][w][dirN];
        int[][] minDist = new int[h][w];
        ArrayUtils.fill(minDist, 10000);
        int[] dh = new int[]{-1, 0, +1, 0};
        int[] dw = new int[]{0, +1, 0, -1};
        for (int hi = 0; hi < h; ++hi) {
            for (int wi = 0; wi < w; ++wi) {
                int fencehi = hi + hi + 1;
                int fencewi = wi + wi + 1;
                for (int d = 0; d < dirN; ++d) {
                    int nhi = fencehi + dh[d];
                    int nwi = fencewi + dw[d];
                    if (fence[nhi][nwi] == ' ') {
                        maze[hi][wi][d] = true;
                        if (nhi == 0 || nhi == 2 * h || nwi == 0 || nwi == 2 * w) minDist[hi][wi] = 1;
                    }
                }
            }
        }
        for (int starthi = 0; starthi < h; ++starthi) {
            for (int startwi = 0; startwi < w; ++startwi) {
                if (minDist[starthi][startwi] > 1) continue;

                while (true) {
                    boolean updated = false;
                    for (int hi : ArrayUtils.randomIndexIntArr(0, h)) {
                        for (int wi : ArrayUtils.randomIndexIntArr(0, w)) {
                            for (int d = 0; d < dirN; ++d) {
                                if (!maze[hi][wi][d]) continue;
                                int nhi = hi + dh[d];
                                int nwi = wi + dw[d];
                                if (!(0 <= nhi && nhi < h && 0 <= nwi && nwi < w)) continue;
                                int ndist = minDist[hi][wi] + 1;
                                if (minDist[nhi][nwi] > ndist) {
                                    minDist[nhi][nwi] = ndist;
                                    updated = true;
                                }
                            }
                        }
                    }
                    if (!updated) break;
                }
            }
        }

        int ans = ArrayUtils.max(minDist);
        out.println(ans);
    }
}

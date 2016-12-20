package main;


import template.SAT2;

import java.util.Scanner;
import java.io.PrintWriter;

/**
 * SAT(2-SAT, solved by SCC) and Consecutive search(Two Pointers).
 *
 * When doing Consecutive searching, beware the Invariable Variables.
 */
public class RecordingEpisodes {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int N = in.nextInt();
        int[][] time = new int[N][4];
        for (int i = 0; i < N; ++i)
            for (int j = 0; j < 4; ++j) time[i][j] = in.nextInt();
        int maxL = 0;
        int maxS = 0;
        SAT2 sat = new SAT2(N * 4);
        for (int i = 0; i < N; ++i) {
            int live = i * 2;
            int repeat = i * 2 + 1;
            for (int j = i + 1; j < N; ++j) {
                int livej = j * 2;
                int repeatj = j * 2 + 1;
                if (join(time[i][0], time[i][1], time[j][0], time[j][1])) sat.addClosure(live, true, livej, true);
                if (join(time[i][0], time[i][1], time[j][2], time[j][3])) sat.addClosure(live, true, repeatj, true);
                if (join(time[i][2], time[i][3], time[j][0], time[j][1])) sat.addClosure(repeat, true, livej, true);
                if (join(time[i][2], time[i][3], time[j][2], time[j][3])) sat.addClosure(repeat, true, repeatj, true);
            }
        }
        int S = 0, T = 0;
        while (true) {
            //System.err.println(S + " " + T + " " + N);
            if (T == N) break;
            if (S + maxL >= N) break;

            while (true) {
                while (T < S + maxL) {
                    int live = T * 2;
                    int repeat = T * 2 + 1;
                    sat.addClosure(live, true, repeat, true);
                    sat.addClosure(live, false, repeat, false);
                    T++;
                }
                int live = T * 2;
                int repeat = T * 2 + 1;
                sat.addClosure(live, true, repeat, true);
                sat.addClosure(live, false, repeat, false);
                if (!sat.check()) break;
                T++;
                if (T == N) break;
            }

            if (T - S > maxL) {
                maxL = T - S;
                maxS = S;
            }

            if (T == N) break;

            int live = S * 2;
            int repeat = S * 2 + 1;
            sat.removeClosure(live, true, repeat, true);
            sat.removeClosure(live, false, repeat, false);
            S++;
            T++;
        }

        out.println((maxS + 1) + " " + (maxS + 1 + maxL - 1));
        //System.err.println((maxS + 1) + " " + (maxS + 1 + maxL - 1));
    }

    private boolean join(int x1, int y1, int x2, int y2) {
        return !(y1 < x2 || y2 < x1);
    }
}

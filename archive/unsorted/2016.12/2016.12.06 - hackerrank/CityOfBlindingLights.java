package main;

import java.util.Arrays;
import java.util.Scanner;
import java.io.PrintWriter;

public class CityOfBlindingLights {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int N = in.nextInt();
        int M = in.nextInt();
        int[][] G = new int[N][N];
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) G[i][j] = Integer.MAX_VALUE;
            G[i][i] = 0;
        }
        for (int i = 0; i < M; ++i) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            int c = in.nextInt();
            if (a == b) continue;
            G[a][b] = c;
        }
        int[][] ret = new int[N][];
        for (int i = 0; i < N; ++i) ret[i] = Arrays.copyOf(G[i], N);
        for (int k = 0; k < N; ++k)
            for (int i = 0; i < N; ++i)
                for (int j = 0; j < N; ++j) {
                    if (ret[i][k] == Integer.MAX_VALUE || ret[k][j] == Integer.MAX_VALUE) continue;
                    ret[i][j] = Math.min(ret[i][j], ret[i][k] + ret[k][j]);
                }

        int Q = in.nextInt();
        for (int i = 0; i < Q; ++i) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            out.println(ret[a][b] == Integer.MAX_VALUE ? -1 : ret[a][b]);
        }

    }
}

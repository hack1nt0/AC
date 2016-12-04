package chelper;

import template.ShortestPath;

import java.util.Scanner;
import java.io.PrintWriter;

public class ShortestReach2 {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int N = in.nextInt();
        int M = in.nextInt();
        ShortestPath sp = new ShortestPath(N);
        for (int i = 0; i < M; ++i) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            int c = in.nextInt();
            sp.addE(a, b, c);
            sp.addE(b, a, c);
        }
        int S = in.nextInt() - 1;
        long[] ret = sp.shortestPath(S);
        for (int i = 0; i < N; ++i) if (i != S) out.print((ret[i] == Long.MAX_VALUE ? -1 : ret[i]) + " ");
        out.println();
    }
}

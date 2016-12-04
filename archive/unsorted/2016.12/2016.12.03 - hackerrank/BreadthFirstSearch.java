package main;

import template.ShortestPath;

import java.util.Scanner;
import java.io.PrintWriter;

public class BreadthFirstSearch {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int N = in.nextInt();
        int M = in.nextInt();
        ShortestPath sp = new ShortestPath(N);
        for (int i = 0; i < M; ++i) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            sp.addE(a, b, 6);
            sp.addE(b, a, 6);
        }
        int S = in.nextInt() - 1;
        long[] ret = sp.bfs(S);
        for (int i = 0; i < N; ++i) if (i != S) out.print((ret[i] == Long.MAX_VALUE ? -1 : ret[i]) + " ");
        out.println();
    }
}

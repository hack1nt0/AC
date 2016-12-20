package main;

import template.ShortestPath;

import java.util.Scanner;
import java.io.PrintWriter;

public class JackGoesToRapture {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int N = in.nextInt();
        int M = in.nextInt();
        ShortestPath sp = new ShortestPath(N);
        for (int i = 0; i < M; ++i) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            long c = in.nextLong();
            //System.err.println(i + " " + a + " " + b + " " + c);
            sp.addE(a, b, c);
            sp.addE(b, a, c);
        }
        int S = 0;
        int T = N - 1;
        long ret = sp.dijkstra1(S, T);
        out.println(ret == Long.MAX_VALUE ? "NO PATH EXISTS" : ret);

    }
}

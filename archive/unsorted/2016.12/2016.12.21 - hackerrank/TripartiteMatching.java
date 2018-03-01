package main;

import template.MatrixUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.io.PrintWriter;
import java.util.Set;

/**
 * Square Root Decomposition, dividing the [bA+bB](len of which is at most N) t [0, sqrt(N)] and (sqrt(N), N) two parts according its' value.
 * Not a common use of SQRT-decomp here, but more like an analysis of complexity.
 *
 * O(N*sqrt(N)) is of same performance of O(N^2*sqrt(N)) here, why?
 */
public class TripartiteMatching {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int N = in.nextInt();
        int M = 0;
        Set<Integer>[][] G = new HashSet[3][];
        for (int i = 0; i < G.length; ++i) {
            G[i] = new HashSet[N];
            for (int j = 0; j < N; ++j) G[i][j] = new HashSet<Integer>();
            int m = in.nextInt();
            if (i != 1) M += m;
            for (int e = 0; e < m; ++e) {
                int a = in.nextInt() - 1;
                int b = in.nextInt() - 1;
                G[i][a].add(b);
                G[i][b].add(a);
            }
        }
        long ret = 0;
        for (int a = 0; a < N; ++a) {
            Set<Integer> bA = G[0][a];
            Set<Integer> bB = G[2][a];

            if (bA.size() + bB.size() <= Math.sqrt(M * 2)) {
                /**
                 * N = M * 2;
                 * at most N loops here, every takes sqrt(N).
                 */
                for (int ba : bA)
                    for (int bb : bB) if (G[1][ba].contains(bb)) ret++;
            } else {
                /**
                 * N = E * 2;
                 * at most sqrt(N) loops here, each loop takes N.
                 * But if you use the same algo above inner, each loop take N^2 .
                 */
                for (int i = 0; i < N; ++i)
                    for (int j : G[1][i]) {
                    if (bA.contains(i) && bB.contains(j)) ret++;
                }
            }
        }
        out.println(ret);
    }
}

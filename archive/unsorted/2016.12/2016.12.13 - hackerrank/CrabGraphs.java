package main;

import template.MaxFlow;

import java.util.Scanner;
import java.io.PrintWriter;

/**
 * When two node in the same class, there's a loss. But the graph isnt bipartite.
 *
 * Split one node t two...
 * WHat problem(s) can reduced t max-flow prob?
 *
 */
public class CrabGraphs {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int N = in.nextInt();
        int K = in.nextInt();
        int M = in.nextInt();
        MaxFlow maxFlow = new MaxFlow(N * 2 + 2);
        int S = N * 2, T = S + 1;
        for (int i = 0; i < M; ++i) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            maxFlow.addE(a * 2, b * 2 + 1, 1);
            maxFlow.addE(b * 2, a * 2 + 1, 1);
        }
        for (int i = 0; i < N; ++i) {
            //maxFlow.addEdge(i * 2, i * 2 + 1, 1);
            maxFlow.addE(S, i * 2, K);
            maxFlow.addE(i * 2 + 1, T, 1);
        }
        int ret = maxFlow.maxFlow(S, T);
        out.println(ret);
    }
}

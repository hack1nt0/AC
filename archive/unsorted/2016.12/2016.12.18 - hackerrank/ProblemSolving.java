package main;

import template.MaxFlow;

import java.util.Scanner;
import java.io.PrintWriter;

/**
 * min paths coverage(Using paths t cover all nodes) of DAG
 */
public class ProblemSolving {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int N = in.nextInt();
        int K = in.nextInt();
        int[] V = new int[N];
        for (int i = 0; i < N; ++i) V[i] = in.nextInt();
        MaxFlow maxFlow = new MaxFlow(N * 2 + 2);
        int S = N * 2, T = S + 1;
        for (int i = 0; i < N; ++i) {
            maxFlow.addE(S, i * 2, 1);
            maxFlow.addE(i * 2 + 1, T, 1);
            for (int j = i + 1; j < N; ++j)
                if (Math.abs(V[i] - V[j]) >= K) {
                    maxFlow.addE(i * 2, j * 2 + 1, 1);
                }
        }
        int ans = N - maxFlow.maxFlow(S, T);
        out.println(ans);
    }
}

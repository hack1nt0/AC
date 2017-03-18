package main;

import template.graph_theory.BidirectionalGraph;
import template.operation.MaxFlow;

import java.util.Scanner;
import java.io.PrintWriter;

/*
 ID: hackint1
 LANG: JAVA
 TASK: ditch
*/

public class Ditch {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int m = in.nextInt();
        int n = in.nextInt();
        BidirectionalGraph graph = new BidirectionalGraph(n);
        for (int i = 0; i < m; ++i) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            int cap = in.nextInt();
            graph.addEdge(from, to, cap);
        }

        MaxFlow maxFlow = new MaxFlow(graph, 0, n - 1);
        out.println(maxFlow.getMinCuts());
    }
}

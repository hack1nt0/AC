package main;

import template.graph_theory.BidirectionalGraph;
import template.operation.MaxFlow;

import java.util.Scanner;
import java.io.PrintWriter;

/*
 ID: hackint1
 LANG: JAVA
 TASK: stall4
*/

public class Stall4 {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int source = n + m, sink = source + 1;
        BidirectionalGraph graph = new BidirectionalGraph(sink + 1);
        for (int i = 0; i < n; ++i) {
            //maxFlow.addEdge(source, i, 1);
            graph.addEdge(source, i, 1, 0);
            int stalls = in.nextInt();
            for (int j = 0; j < stalls; ++j) {
                int stall = in.nextInt() - 1;
                //maxFlow.addEdge(i, n + stall, 1);
                graph.addEdge(i, n + stall, 1, 0);
            }
        }
        for (int i = 0; i < m; ++i) graph.addEdge(n + i, sink, 1, 0);
        MaxFlow maxFlow = new MaxFlow(graph, source, sink);

        out.println(maxFlow.getMaxFlow());
    }
}

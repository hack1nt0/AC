package main;

import template.debug.InputReader;
import template.graph_theory.AbstractEdge;
import template.graph_theory.BidirectionalGraph;
import template.operation.MaxFlow;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/*
 ID: hackint1
 LANG: JAVA
 TASK: telecow
*/

/**
 Executing...
 Test 1: TEST OK [0.086 secs, -1194644 KB]
 Test 2: TEST OK [0.108 secs, -1194644 KB]
 Test 3: TEST OK [0.108 secs, -1194644 KB]
 Test 4: TEST OK [0.295 secs, -1194644 KB]
 Test 5: TEST OK [0.101 secs, -1194644 KB]
 Test 6: TEST OK [0.245 secs, -1194644 KB]
 Test 7: TEST OK [0.180 secs, -1194644 KB]
 Test 8: TEST OK [0.274 secs, -1194644 KB]
 Test 9: TEST OK [0.432 secs, -1194644 KB]
 Test 10: TEST OK [0.893 secs, -1194644 KB]
 Test 11: TEST OK [0.986 secs, -1194644 KB]

 All tests OK.
 */
public class Telecow {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        int source = (in.readInt() - 1) * 2;
        int sink = (in.readInt() - 1) * 2 + 1;
        BidirectionalGraph graph = new BidirectionalGraph(n * 2);
        int oo = Integer.MAX_VALUE;
        for (int i = 0; i < m; ++i) {
            int from = in.readInt() - 1;
            int to = in.readInt() - 1;
            graph.addEdge(MaxFlow.createEdge(from * 2 + 1, to * 2, oo, -1));
            graph.addEdge(MaxFlow.createEdge(to * 2 + 1, from * 2, oo, -1));
        }
        for (int i = 0; i < n; ++i) {
            int from = i * 2;
            int to = i * 2 + 1;
            graph.addEdge(MaxFlow.createEdge(from, to, (from == source || from == sink - 1) ? oo : 1, i + 1));
        }
        //graph.show();
        MaxFlow maxFlow = new MaxFlow(graph, source, sink, new ArrayList<AbstractEdge>());
        //maxFlow.show();
        //out.println(maxFlow.getMaxFlow());
        List<AbstractEdge> minCuts = maxFlow.getMinCuts();
        out.println(minCuts.size());
        if (minCuts.size() > 0) {
            for (int i = 0; i < minCuts.size(); ++i) {
                if (i > 0) out.print(' ');
                out.print(minCuts.get(i).getId());
            }
            out.println();
        }
    }
}

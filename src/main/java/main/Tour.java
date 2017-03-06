package main;

import template.collection.sequence.ArrayQueue;
import template.collection.tuple.Tuple2;
import template.debug.InputReader;
import template.graph_theory.BidirectionalGraph;
import template.graph_theory.UndirectionalGraph;
import template.operation.MinCostFlow;

import java.io.PrintWriter;
import java.util.*;

/*
 ID: hackint1
 LANG: JAVA
 TASK: tour
*/

public class Tour {
    public void solve2(int testNumber, InputReader in, PrintWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        Map<String, Integer> index = new HashMap<>();
        for (int i = 0; i < n; ++i) index.put(in.readString(), index.size());
        BidirectionalGraph graph = new BidirectionalGraph(n * 2);
        for (int i = 0; i < m; ++i) {
            int from = index.get(in.readString()) * 2;
            int to = index.get(in.readString()) * 2;
            if (from > to) {
                int swap = from;
                from = to;
                to = swap;
            }
            graph.addEdge(from + 1, to, 1, 0);
        }
        for (int i = 0; i < n; ++i) {
            if (i == 0 || i == n - 1) graph.addEdge(i * 2, i * 2 + 1, 2, 0);
            else graph.addEdge(i * 2, i * 2 + 1, 1, -1);
        }
        MinCostFlow minCostFlow = new MinCostFlow(graph, 0, (n - 1) * 2 + 1, 2);
        Tuple2<Integer, Integer> flowAndCost = minCostFlow.getFlowAndCost();
        int ans = -1;
        if (flowAndCost.getFirst() < 2) ans = 1;
        else ans = -flowAndCost.getSecond() + 2;
//        minCostFlow.getResidualGraph().show();
//        minCostFlow.flowGraph().show();
        out.println(ans);
    }

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        Map<String, Integer> index = new HashMap<>();
        for (int i = 0; i < n; ++i) index.put(in.readString(), index.size());
        Set<Integer>[]  adj = new HashSet[n];
        for (int i = 0; i < n; ++i) adj[i] = new HashSet<>();
        for (int i = 0; i < m; ++i) {
            int from = index.get(in.readString());
            int to = index.get(in.readString());
            adj[from].add(to);
            adj[to].add(from);
        }
        int[][] maxCities = new int[n][n];
        int oo = Integer.MIN_VALUE;
        for (int i = 0; i < n; ++i) Arrays.fill(maxCities[i], oo);
        for (int east = 1; east < n; ++east) {
            if (adj[east].contains(0)) maxCities[0][east] = 2;
            if (adj[0].contains(east)) maxCities[east][0] = 2;
        }
        for (int west = 0; west < n; ++west) {
            for (int east = west + 1; east < n; ++east) {
                for (int easter = Math.min(east + 1, n - 1); easter < n; ++easter) {
                    if (maxCities[west][east] != oo) {
                        if (adj[easter].contains(east))
                            maxCities[west][easter] = Math.max(maxCities[west][easter], maxCities[west][east] + 1);
                        if (adj[west].contains(easter))
                            maxCities[easter][east] = Math.max(maxCities[easter][east], maxCities[west][east] + 1);
                    }
                    if (maxCities[east][west] != oo) {
                        if (adj[east].contains(easter))
                            maxCities[easter][west] = Math.max(maxCities[easter][west], maxCities[east][west] + 1);
                        if (adj[easter].contains(west))
                            maxCities[east][easter] = Math.max(maxCities[east][easter], maxCities[east][west] + 1);
                    }
                }
            }
        }

        int ans = maxCities[n - 1][n - 1] == oo ? 1 : maxCities[n - 1][n - 1] - 1;
        out.println(ans);
    }
}

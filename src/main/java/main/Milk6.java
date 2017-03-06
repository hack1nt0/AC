package main;

import template.graph_theory.AbstractEdge;
import template.graph_theory.BidirectionalGraph;
import template.operation.MaxFlow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.PrintWriter;

/*
 ID: hackint1
 LANG: JAVA
 TASK: milk6
*/

public class Milk6 {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        BidirectionalGraph graph = new BidirectionalGraph(n);
        int source = 0, sink = n - 1;
        for (int i = 0; i < m; ++i) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            int cap = in.nextInt();
            int id = i + 1;
            graph.addEdge(new AbstractEdge() {
                @Override
                public int getCapacity() {
                    return cap;
                }

                @Override
                public int getId() {
                    return id;
                }

                @Override
                public int getFrom() {
                    return from;
                }

                @Override
                public int getTo() {
                    return to;
                }
            });
        }
        List<AbstractEdge> cuts = new ArrayList<>();
        MaxFlow maxFlow = new MaxFlow(graph, source, sink, cuts);

        out.print(maxFlow.getMaxFlow() + " ");

        out.println(cuts.size());
        if (cuts.size() > 0) {
            int[] cutIds = new int[cuts.size()];
            for (int i = 0; i < cuts.size(); ++i) cutIds[i] = cuts.get(i).getId();
            Arrays.sort(cutIds);
            for (int i = 0; i < cuts.size(); ++i) out.println(cutIds[i]);
        }
        //maxFlow.show();
    }
}

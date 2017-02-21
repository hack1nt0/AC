package main;

import template.graph_theory.AbstractEdge;
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
        MaxFlow maxFlow = new MaxFlow(n);
        int source = 0, sink = n - 1;
        for (int i = 0; i < m; ++i) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            int cost = in.nextInt();
            maxFlow.addE(from, to, cost);
        }

        List<AbstractEdge> cuts = new ArrayList<>();
        out.print(maxFlow.maxFlow(source, sink, cuts) + " ");

        out.println(cuts.size());
        if (cuts.size() > 0) {
            int[] cutIds = new int[cuts.size()];
            for (int i = 0; i < cuts.size(); ++i) cutIds[i] = cuts.get(i).getId() + 1;
            Arrays.sort(cutIds);
            for (int i = 0; i < cuts.size(); ++i) out.println(cutIds[i]);
        }
        maxFlow.show();
    }
}

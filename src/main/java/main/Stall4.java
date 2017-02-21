package main;

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
        MaxFlow maxFlow = new MaxFlow(sink + 1);
        for (int i = 0; i < n; ++i) {
            maxFlow.addE(source, i, 1);
            int stalls = in.nextInt();
            for (int j = 0; j < stalls; ++j) {
                int stall = in.nextInt() - 1;
                maxFlow.addE(i, n + stall, 1);
            }
        }
        for (int i = 0; i < m; ++i) maxFlow.addE(n + i, sink, 1);

        out.println(maxFlow.maxFlow(source, sink));
    }
}

package main;

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
        MaxFlow maxFlow = new MaxFlow(n);
        for (int i = 0; i < m; ++i) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            int cap = in.nextInt();
            maxFlow.addE(from, to, cap);
        }

        out.println(maxFlow.maxFlow(0, n - 1));
    }
}

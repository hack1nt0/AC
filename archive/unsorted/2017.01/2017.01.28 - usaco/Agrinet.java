package main;

import template.operation.MinSpanningTree;

import java.util.Scanner;
import java.io.PrintWriter;
/*
 ID: hackint1
 LANG: JAVA
 TASK: agrinet
*/
public class Agrinet {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        MinSpanningTree minSpanningTree = new MinSpanningTree(n);
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < n; ++j) minSpanningTree.addE(i, j, in.nextInt());

        long ans = minSpanningTree.kruskal();
        out.println(ans);
    }
}

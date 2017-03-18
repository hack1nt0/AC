package main;

import template.collection.sequence.ArrayUtils;
import template.graph_theory.AbstractEdge;
import template.operation.ShortestPath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.PrintWriter;

/*
 ID: hackint1
 LANG: JAVA
 TASK: fence6
*/

public class Fence6 {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int m = in.nextInt();
        class Edge {
            List<Integer> nodes = new ArrayList<>();
            int cost;
            public Edge() {};
        }
        Edge[] edges = new Edge[m];
        int n = 0;
        for (int i = 0; i < m; ++i) edges[i] = new Edge();
        for (int i = 0; i < m; ++i) {
            int ei = in.nextInt() - 1;
            int len = in.nextInt();
            int ins = in.nextInt();
            int outs = in.nextInt();
            edges[ei].cost = len;

            int[] ineis = new int[ins];
            for (int j = 0; j < ins; ++j) ineis[j] = in.nextInt() - 1;
            int[] outeis = new int[outs];
            for (int j = 0; j < outs; ++j) outeis[j] = in.nextInt() - 1;
            if (edges[ei].nodes.size() == 2) continue;
            if (edges[ei].nodes.size() == 0) {
                edges[ei].nodes.add(n);
                edges[ei].nodes.add(n + 1);
                for (int j = 0; j < ins; ++j) edges[ineis[j]].nodes.add(n);
                for (int j = 0; j < outs; ++j) edges[outeis[j]].nodes.add(n + 1);
                n += 2;
                continue;
            } else {
                edges[ei].nodes.add(n);
                int n1 = edges[ei].nodes.get(0);
                if (0 < ins && edges[ineis[0]].nodes.contains(n1)) {
                    for (int j = 0; j < outs; ++j) edges[outeis[j]].nodes.add(n);
                } else {
                    for (int j = 0; j < ins; ++j) edges[ineis[j]].nodes.add(n);
                }
                n++;
            }
        }
//        for (int i = 0; i < m; ++i) {
//            System.err.println((i + 1) + " " + edges[i].nodes.get(0) + " "  + edges[i].nodes.get(1) + " " + edges[i].cost);
//        }
        ShortestPath shortestPath = new ShortestPath(m * 2);
        for (int i = 0; i < m; ++i) {
            int from = edges[i].nodes.get(0);
            int to = edges[i].nodes.get(1);
            int cost = edges[i].cost;
            shortestPath.addE(from, to, cost);
            shortestPath.addE(to, from, cost);
        }

        out.println(shortestPath.minCycle());
        /*
        List<Integer> cycle = new ArrayList<>();
        out.println(shortestPath.minCycle(cycle));
        System.out.println(Arrays.deepToString(cycle.toArray()));
        */
    }
}

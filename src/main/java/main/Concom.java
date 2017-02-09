package main;

import template.collection.tuple.Tuple2;
import template.graph_theory.AbstractEdge;
import template.graph_theory.Graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.io.PrintWriter;
/*
 ID: hackint1
 LANG: JAVA
 TASK: concom
*/
public class Concom {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int en = in.nextInt();
        int maxn = 100;
        Graph graph = new Graph(maxn + 1);
        for (int i = 0; i < en; ++i) {
            graph.addEdge(new AbstractEdge() {
                int from = in.nextInt();
                int to = in.nextInt();
                int cost = in.nextInt();

                @Override
                public Integer getCost() {
                    return cost;
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

        List<Tuple2<Integer, Integer>> ans = new ArrayList<>();
        for (int start = 1; start <= maxn; ++start) {
            int[] shares = new int[maxn + 1];
            boolean[] visited = new boolean[maxn + 1];
            shares[start] = 100;
            while (true) {
                boolean updated = false;
                for (int from = 1; from <= maxn; ++from) {
                    if (visited[from] || shares[from] <= 50) continue;
                    if (start != from) ans.add(new Tuple2<>(start, from));
                    visited[from] = true;
                    for (AbstractEdge e : graph.adj(from)) {
                        int to = e.getTo();
                        int share = (int)e.getCost();
                        shares[to] += share;
                        if (!visited[to] && shares[to] > 50) {
                            updated = true;
                        }
                    }
                }
                if (!updated) break;
            }
        }

        Collections.sort(ans, Tuple2.FIRST_ELEMENT_ORDER);
        for (Tuple2<Integer, Integer> tuple2 : ans) out.println(tuple2.getFirst() + " " + tuple2.getSecond());
    }
}

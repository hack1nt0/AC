package main;

import template.graph_theory.AbstractEdge;
import template.graph_theory.UndirectionalGraph;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.io.PrintWriter;

/*
 ID: hackint1
 LANG: JAVA
 TASK: fence
*/

public class Fence {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int m = in.nextInt();
        int n = 500;
        UndirectionalGraph graph = new UndirectionalGraph(n);
        class Edge extends AbstractEdge {
            boolean visited;
            int from, to;
            public Edge (int from, int to) {
                this.from = from;
                this.to = to;
            }
            public int getFrom() {return from;}
            public int getTo() {return to;}
            @Override
            public boolean getVisited() {return visited;}
            public void setVisited() {visited = true;}

        }
        for (int i = 0; i < m; ++i) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            graph.addEdge(new Edge(from, to));
        }

//        graph.sortEdge(new Comparator<AbstractEdge>() {
//            @Override
//            public int compare(AbstractEdge o1, AbstractEdge o2) {
//                return o1.getT() - o2.getT();
//            }
//        });

        graph.sortEdge();

        if (graph.hasPath()) {
            List<Integer> ans = graph.path();
            for (int i = ans.size() - 1; i >= 0; --i) out.println(ans.get(i) + 1);
            return;
        }
        List<Integer> ans = graph.cycle();
        for (int i = ans.size() - 1; i >= 0; --i) out.println(ans.get(i) + 1);

    }
}

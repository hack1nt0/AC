package main;

import java.util.Arrays;
import java.util.PriorityQueue;

public class BiconnectedDiv2 {
    public int minimize(int[] w1, int[] w2) {
        int res = 0;
        for (int w: w1) res += w;
        for (int w: w2) res += w;

        int n = w1.length + 1;
        int[] deg = new int[n];
        Arrays.fill(deg, 2);
        PriorityQueue<Edge> edges = new PriorityQueue<Edge>();
        for (int i = 1; i < w1.length - 1; ++i)
            edges.add(new Edge(i, i + 1, w1[i]));

        for (Edge e : edges) {
            if (e.w <= 0) break;
            res -= e.w;
        }
        return res;
    }

    class Edge implements Comparable<Edge>{
        int from, to, w;

        public Edge(int from, int to, int w) {
            this.from = from;
            this.to = to;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return o.w - w;
        }
    }
}

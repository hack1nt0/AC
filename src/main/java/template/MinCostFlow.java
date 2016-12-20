package template;

import java.util.*;

/**
 * Created by dy on 16-12-6.
 */
public class MinCostFlow {
    public class Edge {
        public int b;
        public long cap;
        public double cost;
        public Edge rev;

        public Edge(int b, long cap, double cost) {
            this.b = b;
            this.cap = cap;
            this.cost = cost;
        }
    }
    public ArrayList<Edge>[] adj;

    public MinCostFlow(int N) {
        adj = new ArrayList[N];
        for (int i = 0; i < N; ++i) adj[i] = new ArrayList<Edge>();
    }

    public void addE(int a, int b, long cap, double cost) {
        Edge a2b = new Edge(b, cap, cost);
        Edge b2a = new Edge(a, 0, -cost);
        a2b.rev = b2a;
        b2a.rev = a2b;
        adj[a].add(a2b);
        adj[b].add(b2a);
    }

    public double minCost(int S, int T, long flow) {
        long lflow = flow;
        double costs = 0;
        int N = adj.length;
        double[] d = new double[N];
        boolean[] inque = new boolean[N];
        Edge[] edgeOnPath = new Edge[N];

        while (true) {
            if (lflow <= 0) break;

            Arrays.fill(d, Double.MAX_VALUE);
            Arrays.fill(inque, false);
            d[S] = 0;
            Queue<Integer> que = new LinkedList<Integer>();
            que.add(S);
            while (true) {
                if (que.isEmpty()) break;
                int a = que.poll();
                inque[a] = false;
                //System.out.println(Arrays.toString(d));
                for (Edge e : adj[a]) {
                    if (e.cap <= 0) continue;
                    int b = e.b;
                    double a2b = d[a] + e.cost;
                    if (Math.abs(a2b - d[b]) <= 1e-6) continue;
                    if (a2b < d[b]) {
                        edgeOnPath[b] = e;
                        d[b] = a2b;
                        if (!inque[b]) {
                            que.add(b);
                            inque[b] = true;
                        }
                    }
                }
            }
            if (d[T] == Double.MAX_VALUE) {
                throw new RuntimeException("Unsatisfied flow.");
            }

            long minflow = Long.MAX_VALUE;
            for (int cur = T; ; ) {
                Edge preE = edgeOnPath[cur];
                if (preE == null) break;
                minflow = Math.min(minflow, preE.cap);
                cur = preE.rev.b;
            }
            for (int cur = T; ; ) {
                Edge preE = edgeOnPath[cur];
                if (preE == null) break;
                preE.cap -= minflow;
                preE.rev.cap += minflow;
                cur = preE.rev.b;
            }
            costs += Math.min(minflow, lflow) * d[T];
            lflow -= minflow;
            //System.out.println(minflow + " " + d[T]);

        }

        return costs;
    }

    public double maxCost(int S, int T, int flow) {
        return -1;
    }

    public List<Edge> flowGraph() {
        return null;
    }

}

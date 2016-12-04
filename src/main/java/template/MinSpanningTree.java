package template;

import java.util.*;

/**
 * Created by dy on 16-12-3.
 */
public class MinSpanningTree {
    class Graph {
        class Edge implements Comparable<Edge> {
            int a, b; long cost;

            public Edge(int a, int b, long cost) {
                this.a = a;
                this.b = b;
                this.cost = cost;
            }

            @Override
            public int compareTo(Edge o) {
                if (cost > o.cost) return 1;
                if (cost < o.cost) return -1;
                return 0;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;

                Edge edge = (Edge) o;

                if (a != edge.a) return false;
                return b == edge.b;

            }

            @Override
            public int hashCode() {
                int result = a;
                result = 31 * result + b;
                return result;
            }

            @Override
            public String toString() {
                return "Edge{" +
                        "a=" + a +
                        ", b=" + b +
                        ", cost=" + cost +
                        '}';
            }
        }

        int N, M;
        public List<Edge>[] adj;
        public Map<Edge, Long> edges;
        public boolean needUpd = false;

        public Graph(int N) {
            this.N = N;
            adj = new List[N];
            for (int i = 0; i < N; ++i) adj[i] = new ArrayList<Edge>();
            edges = new HashMap<Edge, Long>();
        }

        public void addE (int a, int b, long cost) {
            needUpd = true;
            Edge edge = new Edge(a, b, cost);
            if (edges.containsKey(edge) && edges.get(edge) <= cost) return;
            edges.put(edge, cost);

            M++;
        }

        public void recreateAdj() {
            for (List<Edge> elist : adj) elist.clear();
            for (Edge e : edges.keySet()) {
                e.cost = edges.get(e);
                adj[e.a].add(e);
            }
            needUpd = false;
        }
    }
    Graph G;

    public MinSpanningTree(int N) {
        G = new Graph(N);
    }

    public void addE (int a, int b, int cost) {
        G.addE(a, b, cost);
    }

    public long cost() {
        if (G.needUpd) G.recreateAdj();

        boolean[] vis = new boolean[G.N];
        vis[0] = true;
        int nvis = 1;
        long ret = 0;
        PriorityQueue<Graph.Edge> que = new PriorityQueue<Graph.Edge>();
        for (Graph.Edge e : G.adj[0]) que.add(e);
        while (true) {
            if (nvis == G.N) break;
            if (que.isEmpty()) break;
            Graph.Edge cure = que.poll();
            if (vis[cure.b]) continue;
            vis[cure.b] = true;
            ret += cure.cost;
            nvis++;
            for (Graph.Edge e : G.adj[cure.b]) if (e.b != cure.a) que.add(e);
        }

        if (nvis != G.N) throw new RuntimeException("Graph isnt connected");
        return ret;
    }
}

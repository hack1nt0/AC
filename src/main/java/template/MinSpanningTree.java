package template;

import java.util.*;

/**
 * Created by dy on 16-12-3.
 */
public class MinSpanningTree {
    public class Edge implements Comparable<Edge> {
        public int a, b;
        public int cost;

        public Edge(int a, int b, int cost) {
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
    public Map<Edge, Integer> edges;
    public boolean needUpd = false;

    public MinSpanningTree(int N) {
        this.N = N;
        adj = new List[N];
        for (int i = 0; i < N; ++i) adj[i] = new ArrayList<Edge>();
        edges = new HashMap<Edge, Integer>();
    }

    public void addE (int a, int b, int cost) {
        if (a == b) return;
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

    public long cost() {
        if (needUpd) {
            needUpd = false;
            recreateAdj();
        }

        boolean[] vis = new boolean[N];
        vis[0] = true;
        int nvis = 1;
        long ret = 0;
        PriorityQueue<Edge> que = new PriorityQueue<Edge>();
        for (Edge e : adj[0]) que.add(e);
        while (true) {
            if (nvis == N) break;
            if (que.isEmpty()) break;
            Edge cure = que.poll();
            if (vis[cure.b]) continue;
            vis[cure.b] = true;
            ret += cure.cost;
            nvis++;
            for (Edge e : adj[cure.b]) if (!vis[e.b]) que.add(e);
        }

        if (nvis != N) throw new RuntimeException("Graph isnt connected");
        return ret;
    }

    public template.Graph tree() {
        if (needUpd) {
            needUpd = false;
            recreateAdj();
        }

        Graph ret = new Graph(N);
        boolean[] vis = new boolean[N];
        vis[0] = true;
        int nvis = 1;
        PriorityQueue<Edge> que = new PriorityQueue<Edge>();
        for (Edge e : adj[0]) que.add(e);
        while (true) {
            if (nvis == N) break;
            if (que.isEmpty()) break;
            Edge cure = que.poll();
            if (vis[cure.b]) continue;
            vis[cure.b] = true;
            ret.addE(cure.a, cure.b, cure.cost);
            ret.addE(cure.b, cure.a, cure.cost);
            nvis++;
            for (Edge e : adj[cure.b]) if (!vis[e.b]) que.add(e);
        }

        if (nvis != N) throw new RuntimeException("Graph isnt connected");
        return ret;
    }
}

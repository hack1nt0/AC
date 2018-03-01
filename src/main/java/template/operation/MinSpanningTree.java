package template.operation;

import template.collection.sets.UnionFind;
import template.graph_theory.AbstractEdge;
import template.graph_theory.BidirectionalGraph;
import template.graph_theory.GraphUtils;

import java.util.*;

/**
 * Created by dy on 16-12-3.
 */
public class MinSpanningTree {

    public static class Edge extends AbstractEdge implements Comparable<Edge> {
        public int s, t;
        public int c;

        public Edge(int s, int t, int c) {
            this.s = s;
            this.t = t;
            this.c = c;
        }

        public Edge(Edge o) {
            this.s = o.s;
            this.t = o.t;
            this.c = o.c;
        }

        @Override
        public int compareTo(Edge o) {
            if (c > o.c) return 1;
            if (c < o.c) return -1;
            return 0;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Edge edge = (Edge) o;

            if (s != edge.s) return false;
            return t == edge.t;

        }

        @Override
        public int hashCode() {
            int result = s;
            result = 31 * result + t;
            return result;
        }

        public int getC() {
            return c;
        }

        public int getS() {
            return s;
        }

        public int getT() {
            return t;
        }

        @Override
        public String toString() {
            return "" + s + "-->" + t + "(" + c + ")";
        }
    }

    int N, E;
    public List<Edge>[] adj;
    public Map<Edge, Integer> edges;
    public boolean needUpd = false;

    public MinSpanningTree(int N) {
        this.N = N;
        adj = new List[N];
        for (int i = 0; i < N; ++i) adj[i] = new ArrayList<Edge>();
        edges = new HashMap<Edge, Integer>();
    }

    public void addEdge (int a, int b, int cost) {
        if (a == b) return;
        needUpd = true;
        Edge edge = new Edge(a, b, cost);
        if (edges.containsKey(edge) && edges.get(edge) <= cost) return;
        adj[a].add(edge);
        edges.put(edge, cost);
        E++;
    }

    public void recreateAdj() {
        for (List<Edge> elist : adj) elist.clear();
        for (Edge e : edges.keySet()) {
            e.c = edges.get(e);
            adj[e.s].add(e);
        }
        needUpd = false;
    }

    public long kruskal() {
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
            if (vis[cure.t]) continue;
            vis[cure.t] = true;
            ret += cure.c;
            nvis++;
            for (Edge e : adj[cure.t]) if (!vis[e.t]) que.add(e);
        }

        if (nvis != N) throw new RuntimeException("BidirectionalGraph isnt connected");
        return ret;
    }

    public BidirectionalGraph tree() {
        if (needUpd) {
            needUpd = false;
            recreateAdj();
        }

        BidirectionalGraph ret = new BidirectionalGraph(N);
        boolean[] vis = new boolean[N];
        vis[0] = true;
        int nvis = 1;
        PriorityQueue<Edge> que = new PriorityQueue<Edge>();
        for (Edge e : adj[0]) que.add(e);
        while (true) {
            if (nvis == N) break;
            if (que.isEmpty()) break;
            Edge cure = que.poll();
            if (vis[cure.t]) continue;
            vis[cure.t] = true;
            ret.addEdge(cure.s, cure.t, cure.c);
            ret.addEdge(cure.t, cure.s, cure.c);
            nvis++;
            for (Edge e : adj[cure.t]) if (!vis[e.t]) que.add(e);
        }

        if (nvis != N) throw new RuntimeException("BidirectionalGraph isnt connected");
        return ret;
    }

    public int mst(int s) {
        int r = 0;
        PriorityQueue<Edge>[] ins = new PriorityQueue[N];
        for (int i = 0; i < N; ++i) ins[i] = new PriorityQueue<>();
        for (int i = 0; i < N; ++i) for (Edge e : adj[i]) ins[e.t].add(new Edge(e));
        Edge[] in = new Edge[N];
        for (int i = 0; i < N; ++i) if (i != s) {
            in[i] = ins[i].poll();
            r += in[i].c;
        }
        UnionFind uf = new UnionFind(N);
        while (true) {
            int[] visited = new int[N]; visited[s] = +1;
            boolean found = false;
            for (int i = 0; i < N; ++i) if (visited[uf.find(i)] == 0) {
                int cp = cyclePoint(uf.find(i), in, visited, uf);
                if (cp != -1) {
                    found = true;
                    int k = cp;
                    PriorityQueue<Edge> cins = new PriorityQueue<>();
                    ArrayList<Integer> cps = new ArrayList<>();
                    while (true) {
                        cps.add(k);
                        k = uf.find(in[k].s);
                        if (k == uf.find(cp)) break;
                        uf.union(cp, k);
                    }
                    int np = uf.find(cp);
                    for (int cpp : cps) {
                        for (Edge e : ins[cpp]) if (uf.find(e.s) != np) { e.c -= in[cpp].c; cins.add(e); }
                    }
                    ins[np] = cins;
                    in[np] = cins.poll();
                    r += in[np].c;
//                    break;
                }
            }
            if (!found) break;
        }
        return r;
    }

    private int cyclePoint(int cur, Edge[] in, int[] visited, UnionFind uf) {
        if (visited[cur] == -1) return cur;
        if (visited[cur] == +1) return -1;
        visited[cur] = -1;
        int p = -1;
        try {
            p = cyclePoint(uf.find(in[cur].s), in, visited, uf);
        } catch (Exception e) {
            e.printStackTrace();
        }
        visited[cur] = 1;
        return p;
    }

    public void viz(Edge[] tree) {
        String[] nodes = new String[N];
        String[] edges = new String[E + N - 1];
        for (int i = 0; i < N; ++i) nodes[i] = "" + i;
        int c = 0;
        for (int i = 0; i < N; ++i) for (Edge e : adj[i]) edges[c++] = "" + e.s + " -> " + e.t + " [label = " + e.c + "]";
        if (tree != null)
            for (Edge e : tree) if (e != null) edges[c++] = "" + e.s + " -> " + e.t + " [color = blue, label = " + e.c + "]";
        GraphUtils.viz(true, true, false, nodes, edges);
    }

}

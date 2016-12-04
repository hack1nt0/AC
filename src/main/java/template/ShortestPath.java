package template;

import java.util.*;

/**
 * Created by dy on 16-12-1.
 */
public class ShortestPath {
    public class Graph {

        int N, M;
        public HashMap<Integer, Long> [] adj;

        public Graph(int N) {
            this.N = N;
            adj = new HashMap[N];
            for (int i = 0; i < N; ++i) adj[i] = new HashMap<Integer, Long>();
        }

        public void addE (int a, int b, long cost) {
            if (adj[a].containsKey(b) && adj[a].get(b) > cost) adj[a].put(b, cost);
            else adj[a].put(b, cost);
            M++;
        }
    }
    Graph G;

    public ShortestPath(int N) {
        G = new Graph(N);
    }

    public void addE (int a, int b, int cost) {
        G.addE(a, b, cost);
    }

    public long[] bfs(int S) {
        long[] d = new long[G.N];
        Arrays.fill(d, Long.MAX_VALUE);
        d[S] = 0;
        Queue<Integer> que = new LinkedList<Integer>();
        que.add(S);
        while (!que.isEmpty()) {
            int a = que.poll();
            for (int b : G.adj[a].keySet()) {
                long a2b = d[a] + G.adj[a].get(b);
                if (a2b < d[b]) {
                    d[b] = a2b;
                    que.add(b);
                }
            }
        }

        return d;
    }

    public long[] shortestPath(int S) {
        long[] d = new long[G.N];
        Arrays.fill(d, Long.MAX_VALUE);
        long[] pred = new long[G.N];
        Arrays.fill(pred, Long.MAX_VALUE);
        d[S] = 0;
        Queue<Integer> que = new LinkedList<Integer>();
        que.add(S);
        while (!que.isEmpty()) {
            int a = que.poll();
            if (d[a] >= pred[a]) continue;
            pred[a] = d[a];
            for (int b : G.adj[a].keySet()) {
                long a2b = d[a] + G.adj[a].get(b);
                if (a2b < d[b]) {
                    d[b] = a2b;
                    que.add(b);
                }
            }
        }
        return d;
    }

    public long[][] floyd() {
        long[][] ret = new long[G.N][G.N];
        for (int i = 0; i < G.N; ++i)
            for (int j = 0; j < G.N; ++j) ret[i][j] = G.adj[i].containsKey(j) ? G.adj[i].get(j) : Long.MAX_VALUE;
        for (int k = 0; k < G.N; ++k)
            for (int i = 0; i < G.N; ++i)
                for (int j = 0; j < G.N; ++j) {
                    if (ret[i][k] == Long.MAX_VALUE || ret[k][j] == Long.MAX_VALUE) continue;
                    ret[i][j] = Math.min(ret[i][j], ret[i][k] + ret[k][j]);
                }
        return ret;
    }

    public boolean hasCycle() {
        return false;
    }

    public List<List<Integer>> findCycles() {
        return null;
    }
}

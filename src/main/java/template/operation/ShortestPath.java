package template.operation;

import template.collection.tuple.Tuple2;
import template.graph_theory.Graph;

import java.util.*;

/**
 * Created by dy on 16-12-1.
 */
public class ShortestPath {

    int N, M;
    public HashMap<Integer, Long> [] adj;

    public ShortestPath(int N) {
        this.N = N;
        adj = new HashMap[N];
        for (int i = 0; i < N; ++i) adj[i] = new HashMap<Integer, Long>();
    }

    public boolean addE (int a, int b, long cost) {
        if (a == b) {
            if (cost < 0) {
                //throw new RuntimeException("Negative loops exist.");
                return false;
            }
        } else {
            if (!adj[a].containsKey(b) || adj[a].get(b) > cost) adj[a].put(b, cost);
            M++;
        }
        return true;
    }

    public long[] bfs(int S) {
        long[] d = new long[N];
        Arrays.fill(d, Long.MAX_VALUE);
        d[S] = 0;
        Queue<Integer> que = new LinkedList<Integer>();
        que.add(S);
        while (!que.isEmpty()) {
            int a = que.poll();
            for (int b : adj[a].keySet()) {
                long a2b = d[a] + adj[a].get(b);
                if (a2b < d[b]) {
                    d[b] = a2b;
                    que.add(b);
                }
            }
        }

        return d;
    }

    //bellman-ford
    public long[] bellmanford(int S) {
        long[] d = new long[N];
        Arrays.fill(d, Long.MAX_VALUE);
        boolean[] inque = new boolean[N];
        d[S] = 0;
        Queue<Integer> que = new LinkedList<Integer>();
        que.add(S);
        while (true) {
            if (que.isEmpty()) break;
            int a = que.poll();
            inque[a] = false;
            for (int b : adj[a].keySet()) {
                long a2b = d[a] + adj[a].get(b);
                if (a2b >= d[b]) continue;
                d[b] = a2b;
                if (!inque[b]) {
                    que.add(b);
                    inque[b] = true;
                }
            }
        }
        return d;
    }

    public long[] dijkstra(int S) {
        return dijkstra(S, -1);
    }

    public long[] dijkstra(int S, int T) {
        final long[] d = new long[N];
        Arrays.fill(d, Long.MAX_VALUE);
        PriorityQueue<Tuple2<Integer, Long>> pq = new PriorityQueue<>(Tuple2.SENCOND_ELEMENT_ORDER);
        pq.add(new Tuple2(S, 0L));
        while (true) {
            if (pq.isEmpty()) break;

            Tuple2<Integer, Long> cur = pq.poll();
            int from = cur.getFirst();
            if (d[from] < Long.MAX_VALUE) continue;
            long cost = cur.getSecond();
            d[from] = cost;
            if (from == T) break;

            for (int b : adj[from].keySet()) {
                if (d[b] < Long.MAX_VALUE) continue;
                long ndb = d[from] + adj[from].get(b);
                pq.add(new Tuple2(b, ndb));
            }
        }
        return d;
    }

    public long[][] floyd() {
        long[][] ret = new long[N][N];
        for (int i = 0; i < N; ++i)
            for (int j = 0; j < N; ++j) ret[i][j] = adj[i].containsKey(j) ? adj[i].get(j) : Long.MAX_VALUE;
        for (int k = 0; k < N; ++k)
            for (int i = 0; i < N; ++i)
                for (int j = 0; j < N; ++j) {
                    if (ret[i][k] == Long.MAX_VALUE || ret[k][j] == Long.MAX_VALUE) continue;
                    ret[i][j] = Math.min(ret[i][j], ret[i][k] + ret[k][j]);
                }
        return ret;
    }

    public Graph graph() {
        // TODO: 16-12-11
        return null;
    }

    public Graph minPath(int S, int T) {
        // TODO: 16-12-11

        return null;
    }

    public boolean hasCycle(int S, int T) {
        return false;
    }

    public boolean hasNegativeCycle() {
        return false;
    }

    public List<List<Integer>> cycles() {
        // TODO: 16-12-11 How to effeciently hashing cycles(Lists)?
        return null;
    }

    public List<List<Integer>> negativeCycles() {
        return null;
    }

    public List<Integer> minCycle() {
        // TODO: 16-12-11
        return null;
    }

}

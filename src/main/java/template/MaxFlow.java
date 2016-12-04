package template;

import java.util.*;

/**
 * Created by dy on 16-10-1.
 */
public class MaxFlow {

    int N;
    List<Edge>[] adj;
    int[] dist;
    int[] curE;

    public MaxFlow(int N) {
        this.N = N;
        adj = new ArrayList[N];
        for (int i = 0; i < adj.length; ++i) adj[i] = new ArrayList<Edge>();
        dist = new int[N];
        curE = new int[N];
    }

    public void addE(int u, int v, int cap) {
        Edge e = new Edge(v, cap);
        Edge erev = new Edge(u, 0);
        e.rev = erev;
        erev.rev = e;
        adj[u].add(e);
        adj[v].add(erev);

    }

    public int maxFlow(int s, int t) {
        int res = 0;
        while (bfs(s, t) >= 0) {
            Arrays.fill(curE, 0);
            while (true) {
                int f = dfs(s, t, Integer.MAX_VALUE);
                if (f <= 0) break;
                res += f;
                //System.out.println("cur flow: " + res);
            }
        }
        return res;
    }

    public int dfs(int s, int t, int curMinc) {
        if (s == t) return curMinc;

        while (curE[s] < adj[s].size()) {
            Edge e = adj[s].get(curE[s]);
            int chd = e.v;
            if (dist[chd] != dist[s] + 1) {
                curE[s]++;
                continue;
            }
            int minc = dfs(chd, t, Math.min(curMinc, e.cap));
            if (minc > 0) {
                e.cap -= minc;
                e.rev.cap += minc;
                return minc;
            }
            curE[s]++;
        }

        return 0;
    }

    public int bfs(int s, int t) {
        Arrays.fill(dist, -1);
        dist[s] = 0;
        Queue<Integer> que = new LinkedList<Integer>();
        que.add(s);
        while (!que.isEmpty()) {
            int cur = que.poll();
            if (cur == t) break;
            for (Edge e : adj[cur]) {
                int chd = e.v;
                if (dist[chd] != -1 || e.cap <= 0) continue;
                dist[chd] = dist[cur] + 1;
                que.add(chd);
            }
        }
        return dist[t];
    }

    static class Edge {
        int v, cap;
        Edge rev;

        public Edge(int v, int cap) {
            this.v = v;
            this.cap = cap;
        }
    }
}

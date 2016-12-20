package main;

import template.Graph;

import java.util.*;
import java.io.PrintWriter;

public class Matrix {
    long[] f, g;
    Graph G;
    boolean[] occupied;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int N = in.nextInt();
        int K = in.nextInt();
        G = new Graph(N);
        for (int i = 0; i < N - 1; ++i) {
            int a = in.nextInt();
            int b = in.nextInt();
            int c = in.nextInt();
            G.addE(a, b, c);
            G.addE(b, a, c);
        }
        occupied = new boolean[N];
        f = new long[N];
        g = new long[N];
        for (int i = 0; i < K; ++i) occupied[in.nextInt()] = true;
        dfs(0, -1);
        out.println(Math.min(f[0], g[0]));
    }

    private void dfs(int cur, int fa) {
        f[cur] = g[cur] = 0;
        for (Graph.Edge e: G.adj[cur]) if (e.b != fa) {
            dfs(e.b, cur);
        }


        if (occupied[cur]) {
            f[cur] = Long.MAX_VALUE;
            for (Graph.Edge e: G.adj[cur]) if (e.b != fa) g[cur] += Math.min(f[e.b], g[e.b] + e.cost);
        } else {
            for (Graph.Edge e: G.adj[cur]) if (e.b != fa) f[cur] += Math.min(f[e.b], g[e.b] + e.cost);

            List<Long> arr = new ArrayList<Long>();
            for (Graph.Edge e: G.adj[cur]) if (e.b != fa) {
                arr.add(-Math.min(f[e.b], g[e.b] + e.cost) + g[e.b]);
            }
            Collections.sort(arr);
            if (arr.size() > 0) g[cur] = f[cur] + arr.get(0);
        }
    }
}

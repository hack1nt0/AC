package main;

import java.util.ArrayList;
import java.util.Arrays;

public class PowerOutage {
    static class Edge {
        int to, cost;

        public Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }
    ArrayList<Edge>[] adj;
    int MAXN = 50;
    int[] back = new int[MAXN];
    int[] nback = new int[MAXN];

    public int estimateTimeOut(int[] fromJunction, int[] toJunction, int[] ductLength) {
        adj = new ArrayList[50];
        for (int i = 0; i < adj.length; ++i) adj[i] = new ArrayList<Edge>();
        for (int i = 0; i < fromJunction.length; ++i) {
            adj[fromJunction[i]].add(new Edge(toJunction[i], ductLength[i]));
            adj[toJunction[i]].add(new Edge(fromJunction[i], ductLength[i]));
        }
        treeDp(0, -1);
        return nback[0];
    }

    private void treeDp(int cur, int fa) {
        int chdc = 0;
        for (Edge e : adj[cur]) {
            if (e.to == fa) continue;
            chdc++;
            treeDp(e.to, cur);
        }

        if (chdc == 0) {
            return;
        }

        int[] chdback = new int[MAXN];
        int[] chdnback = new int[MAXN];
        for (Edge e : adj[cur]) {
            if (e.to == fa) continue;
            chdback[e.to] = back[e.to] + e.cost * 2;
            chdnback[e.to] = nback[e.to] + e.cost;
        }
        for (int i = 0; i < chdback.length; ++i) back[cur] += chdback[i];

        nback[cur] = back[cur];
        for (Edge e : adj[cur]) {
            if (e.to == fa) continue;
            nback[cur] = Math.min(nback[cur], back[cur] - chdback[e.to] + chdnback[e.to]);
        }

        return;
    }
}

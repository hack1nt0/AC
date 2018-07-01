package main;

import template.collection.CollectionUtils;
import template.collection.Sorter;
import template.collection.sequence.ArrayUtils;
import template.graph_theory.BidirectionalGraph;
import template.graph_theory.Graph;
import template.graph_theory.GraphUtils;
import template.graph_theory.UndirectionalGraph;
import template.operation.MinSpanningTree;

import java.util.*;

public class UndergroundVault {
    class Edge {
        int from, to;
        boolean vis;

        public Edge(int from, int to) {
            this.from = from;
            this.to = to;
        }
    }

    public int[] sealOrder(String[] rooms) {
        int n = rooms.length;
        List<Edge>[] out = new ArrayList[n];
        List<Edge>[] in = new ArrayList[n];
        for (int i = 0; i < n; ++i) {
            in[i] = new ArrayList<>();
            out[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; ++i) {
            if (rooms[i].trim().length() == 0)
                continue;
            String[] adji = rooms[i].trim().split(",");
            for (int j = 0; j < adji.length; ++j) {
                int jj = Integer.valueOf(adji[j]);
                Edge e = new Edge(i, jj);
                out[i].add(e);
                in[jj].add(e);
//                adj[jj].add(e);
            }
        }
        for (int i = 0; i < n; ++i)
            Collections.sort(in[i], new Comparator<Edge>() {
                @Override
                public int compare(Edge o1, Edge o2) {
                    return o1.from - o2.from;
                }
            });
        int[] ans = new int[n];
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n - 1; ++i) {
            for (int to = 1; to < n; ++to) {
                if (visited[to])
                    continue;
                visited[to] = true;
                boolean good = false;
                for (Edge e : in[to]) {
                    e.vis = true;
                    boolean[] visited2 = visited.clone();
                    dfs(0, out, visited2);
                    if (all(visited2)) {
                        good = true;
                        ans[i] = to;
                        break;
                    }
                    e.vis = false;
                }
                if (good)
                    break;
                else
                    visited[to] = false;
            }
        }
        return ans;
    }

    private boolean all(boolean[] visited2) {
        for (boolean v : visited2)
            if (!v) return false;
        return true;
    }

    private void dfs(int cur, List<Edge>[] out, boolean[] visited2) {
        visited2[cur] = true;
        for (Edge e : out[cur]) {
            if (e.vis || visited2[e.to])
                continue;
            dfs(e.to, out, visited2);
        }
    }
}

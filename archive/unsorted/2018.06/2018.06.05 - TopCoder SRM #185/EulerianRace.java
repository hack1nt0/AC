package main;

import template.collection.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class EulerianRace {
    public int[] planRoute(String[] bridges) {
        int n = bridges.length;
        List<Edge>[] adj = new ArrayList[n];
        for (int i = 0; i < n; ++i)
            adj[i] = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j)
                if (bridges[i].charAt(j) == '1') {
                    Edge e = new Edge(i, j);
                    adj[i].add(e);
                    adj[j].add(e);
                }
        }
        List<Integer> cycle = find(0, adj);
        while (true) {
            int k = -1;
            for (int o : cycle) {
                boolean found = false;
                for (Edge e : adj[o])
                    if (!e.visited) {
                        k = o;
                        found = true;
                        break;
                    }
                if (found)
                    break;
            }
            if (k == -1)
                break;
            List<Integer> newcycle = find(k, adj);
            List<Integer> ncycle = new ArrayList<>();
            int pk = cycle.indexOf(k);
            for (int i = 0; i < cycle.size(); ++i) {
                if (i == pk)
                    for (int j : newcycle) ncycle.add(j);
                else
                    ncycle.add(cycle.get(i));
            }
            cycle = ncycle;
        }
        int[] ans = new int[cycle.size()];
        for (int i = 0; i < ans.length; ++i)
            ans[i] = cycle.get(i);
        return ans;
    }

    private List<Integer> find(int k, List<Edge>[] adj) {
        List<Integer> cycle = new ArrayList<>();
        int cur = k;
        while (true) {
            cycle.add(cur);
            boolean found = false;
            for (Edge e : adj[cur])
                if (!e.visited) {
                    e.visited = true;
                    found = true;
                    cur = e.other(cur);
                    break;
                }
            if (!found)
                throw new RuntimeException();
            if (cur == k) {
                break;
            }
        }
        cycle.add(k);
        return cycle;
    }

    class Edge {
        int s, t;
        boolean visited;

        public Edge(int s, int t) {
            this.s = s;
            this.t = t;
        }

        public int other(int v) {
            return v == s ? t : s;
        }
    }
}

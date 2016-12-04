package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

public class Clicountingd2 {
    static class Edge {
        int a, b;

        public Edge(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }
    static class Clique {
        int S;
        int size;

        public Clique(int s, int size) {
            S = s;
            this.size = size;
        }
    }

    int N = 0;
    ArrayList<Clicountingd2.Edge> edges = new ArrayList<Clicountingd2.Edge>();
    int[] degs;
    int maxSize;
    public int count(String[] g) {
        this.N = g.length;
        int MAXN = 20;
        edges = new ArrayList<Clicountingd2.Edge>();
        degs = new int[N];
        int[][] d = new int[N][N];
        for (int i = 0; i < N; ++i)
            for (int j = i; j < N; ++j) {
                if (g[i].charAt(j) != '0') {
                    d[i][j] = d[j][i] = 1;
                    degs[i]++;
                    degs[j]++;
                }
                else d[i][j] = d[j][i] = MAXN;
                d[i][i] = 0;

                if (g[i].charAt(j) == '?')
                    edges.add(new Clicountingd2.Edge(i, j));
            }

        int ans = 0;
        for (int es = 0; es < (1 << edges.size()); ++es) {
            ArrayList<Integer> eids = new ArrayList<Integer>();
            for (int j = 0; j < edges.size(); ++j) if (((es >> j) & 1) > 0)
                eids.add(j);

            for (int eid: eids) {
                Clicountingd2.Edge e = edges.get(eid);
                d[e.a][e.b] = d[e.b][e.a] = MAXN;
                degs[e.a]--;
                degs[e.b]--;
            }

            Integer[] nids = new Integer[N];
            for (int i = 0; i < nids.length; ++i) nids[i] = i;
            Arrays.sort(nids, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return degs[o1] - degs[o2];
                }
            });
            maxSize = 0;
            findClique(0, nids, d, new ArrayList<Integer>());

            for (int eid: eids) {
                Clicountingd2.Edge e = edges.get(eid);
                d[e.a][e.b] = d[e.b][e.a] = 1;
                degs[e.a]++;
                degs[e.b]++;
            }

            ans += maxSize;
        }
        return ans;
    }

    private void findClique(int curN, Integer[] nids, int[][] d, ArrayList<Integer> nodes) {
        if (curN == N) {
            maxSize = Math.max(maxSize, nodes.size());
            return;
        }
        if (nodes.size() + nids.length - curN <= maxSize) return;

        int res = 0;
        findClique(curN + 1, nids, d, nodes);

        boolean ok = true;
        for (int i = 0; i < nodes.size(); ++i)
            if (d[nodes.get(i)][nids[curN]] > 1) {
                ok = false;
                break;
            }

        if (ok) {
            nodes.add(nids[curN]);
            findClique(curN + 1, nids, d, nodes);
            nodes.remove(nodes.size() - 1);
        }

        return;
    }

}

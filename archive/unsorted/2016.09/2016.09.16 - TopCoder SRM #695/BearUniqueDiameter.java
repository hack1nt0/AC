package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class BearUniqueDiameter {
    int N;
    boolean[][] adj;
    List<Integer>[] adjList;
    int MOD = (int)(1e9 + 7);
    long[][][] treeCache;

    public int countSubtrees(int[] a, int[] b) {
        this.N = a.length + 1;
        adj = new boolean[N][N];
        adjList = new ArrayList[N];
        for (int i = 0; i < adjList.length; ++i)
            adjList[i] = new ArrayList<Integer>();
        int[][] dist = new int[N][N];
        int[][] nxt = new int[N][N];
        for (int i = 0; i < dist.length; ++i) {
            Arrays.fill(dist[i], N);
            dist[i][i] = 0;
            nxt[i][i] = i;
        }
        for (int i = 0; i < a.length; ++i) {
            adj[a[i]][b[i]] = adj[b[i]][a[i]] = true;
            dist[a[i]][b[i]] = dist[b[i]][a[i]] = 1;
            nxt[a[i]][b[i]] = b[i]; nxt[b[i]][a[i]] = a[i];
            adjList[a[i]].add(b[i]);
            adjList[b[i]].add(a[i]);
        }
        for (int k = 0; k < N; ++k)
            for (int i = 0; i < N; ++i)
                for (int j = 0; j < N; ++j) {
                    int tmp = dist[i][k] + dist[k][j];
                    if (tmp < dist[i][j]) {
                        dist[i][j] = tmp;
                        nxt[i][j] = nxt[i][k];
                    }
                }

        long ans = 0;
        for (int from = 0; from < N; ++from)
            for (int to = from; to < N; ++to) {
                List<Integer> path = new ArrayList<Integer>();
                for (int i = nxt[from][to];; i = nxt[i][to]) {
                    if (i == to) break;
                    //System.out.println(i);
                    path.add(i);
                }
                path.add(0, from);
                path.add(to);
                List<Pair> items = new ArrayList<Pair>();
                for (int i = 2; i < path.size() - 2; ++i) {
                    int br = path.get(i);
                    for (int j = 0; j < N; ++j) {
                        if (!adj[br][j] || j == path.get(i - 1) || j == path.get(i + 1)) continue;
                        items.add(new Pair(j, br, Math.min(i, path.size() - 1 - i) - 2));
                    }
                }
                long res = 1;
                for (int i = 0; i < items.size(); ++i) {
                    Pair thing = items.get(i);
                    res = (getValue(thing) + 1) % MOD * res % MOD;
                }
                ans = (ans + res) % MOD;
            }
        return (int)ans;
    }

    long getValue(Pair thing) {
        return treeDp(thing.cur, thing.fa, thing.cost);
    }
    // return comp size
    private long treeDp(int cur, int fa, int maxDep) {
        int chdNum = 0;
        for (int chd: adjList[cur]) if (chd != fa) chdNum++;
        if (chdNum == 0 || maxDep == 0) {
            return 1;
        }
        long res = 1;
        for (int chd: adjList[cur]) {
            if (chd == fa) continue;
            res = res * treeDp(chd, cur, maxDep - 1) % MOD;
        }
        return (res + 1) % MOD;
    }

    class Pair {
        int cur, fa;
        int cost;

        public Pair(int cur, int fa, int cost) {
            this.cur = cur;
            this.fa = fa;
            this.cost = cost;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (cur != pair.cur) return false;
            if (fa != pair.fa) return false;
            return cost == pair.cost;

        }

        @Override
        public int hashCode() {
            int result = cur;
            result = 31 * result + fa;
            result = 31 * result + cost;
            return result;
        }
    }

}

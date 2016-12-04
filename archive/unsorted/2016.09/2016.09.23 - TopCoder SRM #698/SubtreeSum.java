package main;

import java.util.ArrayList;

public class SubtreeSum {
    int MAXB = 30;
    int[] x;
    ArrayList<Integer>[] adj;
    long[][] f;
    long[][] g;
    long[] h;
    long MOD = (long)1e9 + 7;
    int N;
    public int getSum(int[] p, int[] x) {
        this.x = x;
        this.N = p.length + 1;
        adj = new ArrayList[N];
        for (int i = 0; i < adj.length; ++i) adj[i] = new ArrayList<Integer>();
        for (int i = 0; i < p.length; ++i) {
            adj[p[i]].add(i + 1);
            adj[i + 1].add(p[i]);
        }
        f = new long[N][MAXB];
        g = new long[N][MAXB];
        h = new long[N];
        treeDp(0, -1);
        long ans = 0;
        for (int i = 0; i < MAXB; ++i)
            ans = (ans + g[0][i] * (1 << i) % MOD) % MOD;

        return (int)ans;
    }

    private void treeDp(int cur, int fa) {
        int chdN = 0;
        for (int chd: adj[cur]) {
            if (chd == fa) continue;
            treeDp(chd, cur);
            ++chdN;
        }
        if (chdN == 0) {
            for (int i = 0; i < MAXB; ++i)
                if ((x[cur] >> i & 1) > 0) f[cur][i] = g[cur][i] = 1;
            h[cur] = 1;
            return;
        }

        //f
        for (int i = 0; i < MAXB; ++i) {
            long res = -1;
            if ((x[cur] >> i & 1) > 0) {
                res = 1;
                for (int chd: adj[cur]) {
                    if (chd == fa) continue;
                    res = res * (h[chd] + 1) % MOD;
                }
            } else {
                long tot = 1;
                long exctot = 1;
                for (int chd : adj[cur]) {
                    if (chd == fa) continue;
                    tot = tot * (h[chd] + 1) % MOD;
                    exctot = exctot * (h[chd] + 1 - f[chd][i]) % MOD;
                }
                res = tot - exctot;
            }
            if (res == -1) throw new RuntimeException();
            f[cur][i] = res;
        }

        //g
        for (int i = 0; i < MAXB; ++i) {
            long res = 0;
            for (int chd: adj[cur]) {
                if (chd == fa) continue;
                res = (res + g[chd][i]) % MOD;
            }
            res = (res + f[cur][i]) % MOD;
            g[cur][i] = res;
        }
        //h
        long res = 1;
        for (int chd: adj[cur]) {
            if (chd == fa) continue;
            res = res * (h[chd] + 1) % MOD;
        }
        h[cur] = res;

        return;
    }
}

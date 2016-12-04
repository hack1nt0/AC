package main;

import java.util.ArrayList;
import java.util.Arrays;

public class Treestrat {
    ArrayList<Integer>[] adj;
    boolean[] A;
    boolean[] B;
    int N;

    public int roundcnt(int[] tree, int[] A, int[] B) {
        this.N = tree.length + 1;
        adj = new ArrayList[N];
        for (int i = 0; i < adj.length; ++i) adj[i] = new ArrayList<Integer>();
        for (int i = 1; i < N; ++i) {
            adj[i].add(tree[i - 1]);
            adj[tree[i - 1]].add(i);
        }
        this.A = new boolean[N];
        this.B = new boolean[N];
        for (int i = 0; i < A.length; ++i) this.A[A[i]] = true;
        for (int i = 0; i < B.length; ++i) this.B[B[i]] = true;

        int ans = 0;
        boolean ok = false;
        while (true) {
            for (int i = 0; i < N; ++i) {
                if (this.A[i] && this.B[i]) ok = true;
            }
            if (ok) break;
            ans++;

            for (int i = 0; i < N; ++i) {
                if (!this.A[i]) continue;

                int okchd = -1;
                int minB = N;
                for (int chd: adj[i]) {
                    if (okchd == -1 || minB > dfsB(chd, i)) okchd = chd;
                }
                this.A[i] = false;
                this.A[okchd] = true;
            }

            for (int i = 0; i < N; ++i) {
                if (this.A[i] && this.B[i]) ok = true;
            }
            if (ok) break;

            for (int i = 0; i < N; ++i) {
                if (!this.B[i]) continue;
                int okchd = -1;
                int minS = N;
                for (int chd: adj[i]) {
                    if (okchd == -1 || minS > dfsAB(chd, i, 0, false, false)) okchd = chd;
                }
                //if (okchd != -1) {
                    this.B[okchd] = true;
                    this.B[i] = false;
                //}
            }
        }
        return ans;
    }

    private int dfsB(int cur, int fa) {
        int minB = N;
        int chdc = 0;
        for (int chd: adj[cur]) {
            if (chd == fa) continue;
            chdc++;
            minB = Math.min(minB, dfsB(chd, cur) + 1);
        }
        if (this.B[cur]) minB = 0;
        if (chdc == 0) minB = 0;
        return minB;
    }

    private int dfsAB(int cur, int fa, int dep, boolean hasA, boolean hasB) {
        if (this.A[cur]) hasA = true;
        if (this.B[cur]) hasB = true;

        if (hasA && hasB) return dep;

        int res = N;
        for (int chd: adj[cur]) {
            if (chd == fa) continue;
            res = Math.min(res, dfsAB(chd, cur, dep + 1, hasA, hasB));
        }
        return res;
    }
}

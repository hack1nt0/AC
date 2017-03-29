package main;

import template.Graph;

import java.util.Arrays;
import java.util.Scanner;
import java.io.PrintWriter;
/*
Not exists optimal sub-structure, so DP is not is here.
But you can expand d[node] to d[node][result] = bool

I used Binary search here, yet not proved correct.
 */
public class MinimumPenaltyPath {
    int MAXC = 1024;
    int MINC = 1;
    Graph G;
    boolean[] vis;//dfs

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int N = in.nextInt();
        int M = in.nextInt();
        G = new Graph(N);
        for (int i = 0; i < M; ++i) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            int c = in.nextInt();
            if (a == b) continue;
            G.addE(a, b, c);
            G.addE(b, a, c);
        }
        int S = in.nextInt() - 1;
        int T = in.nextInt() - 1;
        int L = MINC - 1;
        vis = new boolean[N];
        Arrays.fill(vis, false);
        int R = dfs(S, T);
        if (R == -1) {
            out.println(-1);
            return;
        }

        while (true) {
            if (L + 1 >= R) break;
            int penalty = L + (R - L) / 2;
            Arrays.fill(vis, false);
            if (C(S, T, penalty)) R = penalty;
            else L = penalty;
        }

        out.println(R);
    }

    private boolean C(int cur, int T, int penalty) {
        if (cur == T) return true;
        if (vis[cur]) return false;
        vis[cur] = true;
        for (Graph.Edge e : G.adj[cur]) {
            if ((e.cost | penalty) != penalty) continue;
            boolean chdres = C(e.b, T, penalty);
            if (chdres) return true;
        }
        return false;
    }

    private int dfs(int cur, int T) {
        if (cur == T) return 0;
        if (vis[cur]) return -1;
        vis[cur] = true;
        for (Graph.Edge e : G.adj[cur]) {
            int chdres = dfs(e.b, T);
            if (chdres != -1) {
                return e.cost | chdres;
            }
        }
        return -1;
    }
}

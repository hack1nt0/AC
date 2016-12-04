package main;

import java.util.ArrayList;
import java.util.Arrays;

public class TreeAndVertex {
    boolean[][] adj;
    public int get(int[] tree) {
        int n = tree.length + 1;
        int m = n - 1;
        adj = new boolean[n][n];
        for (int i = 0; i < m; ++i)
            adj[i + 1][tree[i]] = adj[tree[i]][i + 1] = true;
        int maxCP = 0;
        for (int i = 0; i < n; ++i)
            maxCP = Math.max(maxCP, findCP(i));
        return maxCP;
    }

    private int findCP(int invalidVetex) {
        int n = adj.length;
        boolean[] vis = new boolean[n];
        int numCP = 0;
        for (int i = 0; i < n; ++i) {
            if (vis[i] || i == invalidVetex) continue;
            numCP++;
            findCPHelper(i, invalidVetex, vis);
        }
        return numCP;
    }

    private void findCPHelper(int cur, int invalidVetex, boolean[] vis) {
        for (int i = 0; i < adj.length; ++i) {
            if (vis[i] || !adj[cur][i] || i == invalidVetex) continue;
            vis[i] = true;
            findCPHelper(i, invalidVetex, vis);
        }
    }
}

package main;

public class TriangleEasy {
    public int find(int n, int[] x, int[] y) {
        int[][] adj = new int[n][n];
        for (int i = 0; i < x.length; ++i)
            adj[x[i]][y[i]] = adj[y[i]][x[i]] = 1;
        int maxEdgeNum = 0;
        for (int i = 0; i < n; ++i)
            for (int j = i + 1; j < n; ++j)
                for (int k = j + 1; k < n; ++k) {
                    maxEdgeNum = Math.max(maxEdgeNum, adj[i][j] + adj[j][k] + adj[k][i]);
                }
        return 3 - maxEdgeNum;
    }
}

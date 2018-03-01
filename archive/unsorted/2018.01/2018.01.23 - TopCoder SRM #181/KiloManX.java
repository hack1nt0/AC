package main;

import template.debug.RandomUtils;
import template.operation.MinSpanningTree;

public class KiloManX {
    MinSpanningTree G;
    MinSpanningTree.Edge[] tree;
    boolean debug;

    public int leastShots(String[] damageChart, int[] bossHealth) {
        int n = bossHealth.length;
        G = new MinSpanningTree(n + 1);
        for (int i = 0; i < n; ++i) {
            G.addEdge(n, i, bossHealth[i]);
            for (int j = 0; j < n; ++j) if (i != j) {
                int w = damageChart[i].charAt(j) - '0';
                if (w != 0) G.addEdge(i, j, (bossHealth[j] + w - 1) / w);
            }
        }
        int r = G.mst(n);
        return r;
    }

    int mi = Integer.MAX_VALUE;
    MinSpanningTree.Edge[] mst;
    public int leastShots2(String[] damageChart, int[] bossHealth) {
        int n = damageChart.length;
        int[][] C = new int[n + 1][n + 1];
        for (int i = 0; i < n; ++i) C[n][i] = (bossHealth[i]);
        for (int i = 0; i < n; ++i) for (int j = 0; j < n; ++j) {
            int pd = damageChart[i].charAt(j) - '0';
            if (pd > 0) C[i][j] = (bossHealth[j] + pd - 1) / pd;
        }
        boolean[] visited = new boolean[n];
        MinSpanningTree.Edge[] tree = new MinSpanningTree.Edge[n];
        permutate(0, visited, C, 0, tree);
        return mi;
    }

    private void permutate(int cur, boolean[] visited, int[][] C, int c, MinSpanningTree.Edge[] tree) {
        int n = visited.length;
        if (cur == n) {
            if (c < mi) {
                mi = c;
//                if (mst == null) mst = new MinSpanningTree.Edge[n];
//                for (int i = 0; i < n; ++i) mst[i] = tree[i];
                mst = tree.clone();
            }
            return;
        }
        for (int i = 0; i < n; ++i) if (!visited[i]) {
            visited[i] = true;
            if (cur == 0) {
                tree[cur] = new MinSpanningTree.Edge(n, i, C[n][i]);
                permutate(cur + 1, visited, C, c + C[n][i], tree);
            } else {
                int fa = n;
                for (int j = 0; j < n; ++j) if (j != i && visited[j] && C[j][i] > 0 && C[fa][i] > C[j][i]) fa = j;
                tree[cur] = new MinSpanningTree.Edge(fa, i, C[fa][i]);
                permutate(cur + 1, visited, C, c + C[fa][i], tree);
            }
            visited[i] = false;
        }
    }

    public static void main(String[] args) {
        RandomUtils.setSeed(1);
        int n = 10;
        while (true) {
            String[] damageChart = new String[n];
            int[] bossHealth = new int[n];
            for (int i = 0; i < n; ++i) {
                bossHealth[i] = RandomUtils.uniform(1, 10);
                damageChart[i] = "";
                for (int j = 0; j < n; ++j) damageChart[i] += RandomUtils.uniform(0, 10);
            }
            KiloManX obj = new KiloManX();
            int mst1 = obj.leastShots(damageChart, bossHealth);
            int mst2 = obj.leastShots2(damageChart, bossHealth);
            if (mst1 != mst2) {
                obj.debug = true;
                obj.leastShots(damageChart, bossHealth);
                obj.G.viz(obj.tree);
                obj.G.viz(obj.mst);
                throw new RuntimeException("E");
            }
        }
    }
}

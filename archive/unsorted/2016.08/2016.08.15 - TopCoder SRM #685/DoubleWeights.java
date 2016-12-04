package main;

public class DoubleWeights {
    int minLen = Integer.MAX_VALUE;
    String[] weight1;
    String[] weight2;
    public int minimalCost(String[] weight1, String[] weight2) {
        int n = weight1.length;
        boolean[] vis = new boolean[n];
        this.weight1 = weight1;
        this.weight2 = weight2;
        dfs(0, 0, 0, vis);
        return minLen == Integer.MAX_VALUE ? -1 : minLen;
    }

    private void dfs(int cur, int w1, int w2, boolean[] vis) {
        if (cur == 1) {
            minLen = Math.min(minLen, w1 * w2);
            return;
        }

        if (w1 * w2 >= minLen) return;

        for (int i = 0; i < vis.length; ++i) {
            if (vis[i] || weight1[cur].charAt(i) == '.') continue;
            int d1 = weight1[cur].charAt(i) - '0';
            int d2 = weight2[cur].charAt(i) - '0';
            vis[i] = true;
            dfs(i, w1 + d1, w2 + d2, vis);
            vis[i] = false;
        }
    }
}

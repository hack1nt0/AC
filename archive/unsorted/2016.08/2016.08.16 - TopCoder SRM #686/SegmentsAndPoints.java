package main;

import java.util.Arrays;
import java.util.Comparator;

public class SegmentsAndPoints {
    boolean[][] adj;
    public String isPossible(int[] p, final int[] l, final int[] r) {
        int n = p.length;
        adj = new boolean[n][n];
        for (int v = 0; v < n; ++v)
            for (int i = 0; i < n; ++i)
                if (l[i] <= p[v] && p[v] <= r[i]) adj[v][i] = true;
        int[] invAdj = new int[n];
        Arrays.fill(invAdj, -1);
        String POSSIBLE = "Possible";
        String IMPOSSIBLE = "Impossible";
        boolean[] vis = new boolean[n];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(vis, false);
            if (!findSegment(i, vis, invAdj)) return IMPOSSIBLE;
        }
        return POSSIBLE;
    }

    private boolean findSegment(int cur, boolean[] vis, int[] invAdj) {
        vis[cur] = true;
        for (int i = 0; i < invAdj.length; ++i)
            if (invAdj[i] == -1 && adj[cur][i]) {
                invAdj[i] = cur;
                return true;
            }
        for (int i = 0; i < invAdj.length; ++i) {
            if (!adj[cur][i] || vis[invAdj[i]]) continue;
            int nxt = invAdj[i];
            invAdj[i] = cur;
            if (findSegment(nxt, vis, invAdj)) return true;
            invAdj[i] = nxt;
        }
        return false;
    }

    public String isPossibleGreed(int[] p, final int[] l, final int[] r) {
        int n = p.length;
        Integer[] segments = new Integer[n];
        for (int i = 0; i < n; ++i) segments[i] = i;
        Arrays.sort(segments, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (r[o1] != r[o2]) return r[o1] - r[o2];
                return l[o1] - l[o2];
            }
        });
        Arrays.sort(p);
        String POSSIBLE = "Possible";
        String IMPOSSIBLE = "Impossible";
        for (int i = 0; i < n; ++i) {
            if (p[i] < l[segments[i]] || r[segments[i]] < p[i]) return IMPOSSIBLE;
        }
        return POSSIBLE;
    }
}

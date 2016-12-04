package main;

import java.util.ArrayList;
import java.util.List;

public class TreeAndCycle {
    int[] inner, bound;
    List<Edge>[] adj;
    int[] costV;
    class Edge {
        int to, w;

        public Edge(int to, int w) {
            this.to = to;
            this.w = w;
        }
    }

    public int minimize(int[] costV, int[] pre, int[] costE) {
        this.costV = costV;
        int n = costV.length;
        adj = new List[n];
        for (int i = 0; i < adj.length; ++i) adj[i] = new ArrayList<Edge>();
        for (int i = 0; i < pre.length; ++i) {
            adj[i + 1].add(new Edge(pre[i], costE[i]));
            adj[pre[i]].add(new Edge(i + 1, costE[i]));
        }
        inner = new int[n];
        bound = new int[n];
        treeDP(0, -1);

        int res = 0;
        for (int ew: costE) res += ew;
        res += Math.min(inner[0], bound[0]);
        return res;
    }

    private void treeDP(int cur, int fa) {
        int leafNum = 0;
        for (Edge e: adj[cur]) {
            int chd = e.to;
            if (chd == fa) continue;
            ++leafNum;
            treeDP(chd, cur);
        }
        if (leafNum == 0) {
            inner[cur] = 2 * costV[cur];
            bound[cur] = 2 * costV[cur];
            return;
        }
        int curInner = Integer.MAX_VALUE;
        int curBound = Integer.MAX_VALUE;

        int chdAcc = 0;
        for (Edge e: adj[cur]) {
            int chd = e.to;
            if (chd == fa) continue;
            chdAcc += Math.min(inner[chd], bound[chd]);
        }
        for (Edge e1: adj[cur])
            for (Edge e2: adj[cur]) {
                int chd1 = e1.to;
                int chd2 = e2.to;
                if (chd1 == fa || chd2 == fa) continue;
                if (chd1 == chd2) {
                    curBound = Math.min(curBound, chdAcc - Math.min(inner[chd1], bound[chd1]) + bound[chd1] + costV[cur] - e1.w - costV[chd1]);
                } else {
                    curInner = Math.min(curInner, chdAcc - Math.min(inner[chd1], bound[chd1]) - Math.min(inner[chd2], bound[chd2]) + bound[chd1] - costV[chd1] - e1.w + bound[chd2] - costV[chd2] - e2.w);
                }
            }
        curBound = Math.min(curBound, chdAcc + 2 * costV[cur]);
        inner[cur] = curInner;
        bound[cur] = curBound;
    }
}

package main;

import template.Graph;

import java.util.*;
import java.io.PrintWriter;

/**
 * treeDP
 * For arbitrary node A, its farthest node B lies on the bound of longest path of Tree(how t prove?)
 *
 * timeout with hashmap, e.t. dp[][] timeout, prefer dp[].
 */
public class JourneyScheduling {
    List<Integer>[] adj;
    long maxAll;
    Map<Integer, Integer>[] max;
    int[] a, b;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int N = in.nextInt();
        int M = in.nextInt();
        adj = new ArrayList[N];
        for (int i = 0; i < N; ++i) adj[i] = new ArrayList<Integer>();
        for (int i = 0; i < N - 1; ++i) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            adj[a].add(b);
            adj[b].add(a);
        }
        max = new HashMap[N];
        for (int i = 0; i < N; ++i) max[i] = new HashMap<Integer, Integer>();
        a = new int[N];
        b = new int[N];
        getMaxAll(0, -1);
        for (int i = 0; i < M; ++i) {
            int V = in.nextInt() - 1;
            int K = in.nextInt();
            long res = 0;
            for (int b : adj[V]) res = Math.max(res, getMax(b, V) + 1);
            res += maxAll * (K - 1);
            out.println(res);
        }

    }

    private int getMax(int cur, int from) {
        if (max[cur].containsKey(from))
            return max[cur].get(from);

        int res = 0;
        for (int chd : adj[cur]) if (chd != from) {
            res = Math.max(res, getMax(chd, cur) + 1);
        }

        max[cur].put(from, res);
        return res;
    }

    private void getMaxAll(int cur, int fa) {
        a[cur] = b[cur] = 0;
        List<Integer> bs = new ArrayList<Integer>();
        for (int chd : adj[cur]) if (chd != fa) {
            getMaxAll(chd, cur);
            a[cur] = Math.max(a[cur], a[chd]);
            bs.add(b[chd]);
        }
        if (bs.size() > 0) {
            Collections.sort(bs);
            b[cur] = bs.get(bs.size() - 1) + 1;
            if (bs.size() > 0) a[cur] = Math.max(a[cur], b[cur]);
            if (bs.size() > 1) a[cur] = Math.max(a[cur], bs.get(bs.size() - 2) + 1 + bs.get(bs.size() - 1) + 1);
        }
        maxAll = Math.max(maxAll, a[cur]);
    }
}

package main;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class FromToDivisibleDiv2 {
    public int shortest(int N, int S, int T, int[] a, int[] b) {

        int[] dist = new int[N + 1];
        Arrays.fill(dist, -1);
        Queue<Integer> que = new LinkedList<Integer>();
        que.add(S); dist[S] = 0;
        boolean[] vis = new boolean[a.length];
        while (!que.isEmpty()) {

            int cur = que.poll();
            if (cur == T) return dist[T];

            //boolean ok = false;
            for (int i = 0; i < a.length; ++i) {
                if (vis[i]) continue;

                if (cur % a[i] != 0) continue;

                vis[i] = true;

                for (int j = b[i]; j <= N; j += b[i]) {
//                for (int j = 1; j <= N; ++j) {
//                    if (j % b[i] != 0) continue;
                    if (dist[j] != -1) continue;
                    dist[j] = dist[cur] + 1;
                    que.add(j);
                }
                //ok = true;
            }
            //if (cur == S && !ok) return -1;
        }
        //int ans = dfs(S, 0, T, vis, divA, divB, N);

        return -1;
    }

    private int dfs(int cur, int dist, int t, boolean[] vis, boolean[] divA, boolean[] divB, int n) {
        if (cur == n) {
            return dist;
        }
        if (!divA[cur]) {
            return -1;
        }

        for (int i = 0; i < n; ++i) {
            if (vis[i]) continue;
            if (!divB[i]) continue;
            vis[i] = true;
            int res = dfs(i, dist + 1, t, vis, divA, divB, n);
            if (res > -1) return res;
        }
        return -1;
    }
}

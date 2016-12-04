package main;

import java.util.Arrays;

public class Sunnygraphs2 {

    public long count(int[] a) {
        int n = a.length;
        int[][] adj = new int[n][n];
        int INF = 100;
        for (int i = 0; i < n; ++i) {
            Arrays.fill(adj[i], INF);
            adj[i][a[i]] = 1;
            adj[i][i] = 0;
        }
        for (int k = 0; k < n; ++k)
            for (int i = 0; i < n; ++i)
                for (int j = 0; j < n; ++j)
                    adj[i][j] = Math.min(adj[i][j], adj[i][k] + adj[k][j]);

        long res = 1;
        boolean[] vis = new boolean[n];
        int compCnt = 0;
        for (int i = 0; i < n; ++i) {
            if (vis[i]) continue;
            if (adj[a[i]][i] == INF) continue;
            //i is a root
            ++compCnt;
            long compSize = 0;
            for (int j = 0; j < n; ++j) {
                if (adj[j][i] == INF) continue;
                ++compSize;
                vis[j] = true;
            }
            //System.out.println(compSize);
            res *= Math.pow(2, compSize - 1) * 2 - Math.pow(2, compSize - 1 - adj[a[i]][i]);
        }
        //System.out.println(compCnt);
        if (compCnt == 1) res += 1;
        return res;
    }

    enum WeekDayEnum { Mon, Tue, Wed, Thu, Fri, Sat, Sun }
    public static void main(String[] args) {
        int[] arr = new int[2];
        System.out.println(WeekDayEnum.values());
    }
}

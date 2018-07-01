package main;

import java.util.*;

public class DistanceZeroAndOne {
    public String[] construct(int[] dist0, int[] dist1) {
        String[] BAD = new String[0];
        if (!(dist0[1] > 0 && dist0[1] == dist1[0]))
            return BAD;
        int n = dist0.length;
        int[][] input = {dist0, dist1};
        boolean[][] graph = new boolean[n][n];
        for (int cur = 0; cur < n; ++cur) {
            for (int nxt = 0; nxt < n; ++nxt) {
                graph[cur][nxt] = Math.abs(dist0[cur] - dist0[nxt]) <= 1 && Math.abs(dist1[cur] - dist1[nxt]) <= 1;
            }
        }
        int[][] dist = new int[2][n];
        Integer oo = Integer.MAX_VALUE;
        for (int from = 0; from < 2; ++from) {
            Arrays.fill(dist[from], oo);
            dist[from][from] = 0;
            while (true) {
                boolean updated = false;
                for (int i = 0; i < n; ++i)
                    if (dist[from][i] != oo) {
                        for (int j = 0; j < n; ++j)
                            if (graph[i][j] && dist[from][i] + 1 < dist[from][j]) {
                                updated = true;
                                dist[from][j] = dist[from][i] + 1;
                            }
                    }
                if (!updated)
                    break;
            }
            if (!Arrays.equals(dist[from], input[from]))
                return BAD;
        }

        String[] ans = new String[n];
        for (int i = 0; i < n; ++i) {
            graph[i][i] = false;
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; ++j)
                sb.append(graph[i][j] ? 'Y' : 'N');
            ans[i] = sb.toString();
        }
        return ans;
    }
}

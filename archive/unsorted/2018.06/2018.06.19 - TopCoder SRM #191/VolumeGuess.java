package main;

import java.util.Arrays;

public class VolumeGuess {
    public int correctVolume(String[] queries, int numberOfBoxes, int ithBox) {
        int n = numberOfBoxes;
        int[][] adj = new int[n][n];
        for (String query : queries) {
            String[] t = query.split(",");
            int u = Integer.parseInt(t[0]) - 1;
            int v = Integer.parseInt(t[1]) - 1;
            int s = Integer.parseInt(t[2]);
            adj[u][v] = adj[v][u] = s;
        }
        int oo = Integer.MAX_VALUE;
        int[] xs = new int[n];
        Arrays.fill(xs, oo);
        for (int l = 0; l < n; ++l)
            for (int m = l + 1; m < n; ++m)
                for (int r = m + 1; r < n; ++r) {
                    if (adj[l][m] == adj[m][r]) {
                        xs[m] = adj[l][m];
                    }
                    if (adj[l][m] == adj[l][r]) {
                        xs[l] = adj[l][m];
                    }
                    if (adj[l][r] == adj[m][r]) {
                        xs[r] = adj[l][r];
                    }
                }
        while (true) {
            boolean updated = false;
            for (int l = 0; l < n; ++l)
                for (int r = l + 1; r < n; ++r) {
                    if (xs[l] != oo && adj[l][r] != xs[l] && xs[r] == oo) {
                        updated = true;
                        xs[r] = adj[l][r];
                    }
                    if (xs[r] != oo && adj[l][r] != xs[r] && xs[l] == oo) {
                        updated = true;
                        xs[l] = adj[l][r];
                    }
                }
            if (!updated)
                break;
        }
//        for (int l = 0; l < n; ++l)
//            for (int r = l + 1; r < n; ++r)
//                if (xs[l] == oo && xs[r] == oo) {
//                    xs[l] = adj[l][r];
//                }
//        Arrays.sort(xs);
//        int noo = 0;
//        for (int i = 0; i < xs.length; ++i)
//            if (xs[i] == oo)
//                ++noo;
//        if (noo > 1)
//            throw new RuntimeException();
        return xs[ithBox - 1];
    }
}

package main;

public class TeamBuilder {
    public int[] specialLocations(String[] paths) {
        int n = paths.length;
        int[] ans = new int[2];
        for (int kind = 0; kind < 2; ++kind) {
            boolean[][] dist = new boolean[n][n];
            for (int i = 0; i < n; ++i)
                for (int j = 0; j < n; ++j)
                    if (paths[i].charAt(j) == '1' || i == j) {
                        if (kind == 0)
                            dist[i][j] = true;
                        else
                            dist[j][i] = true;
                    }
            for (int k = 0; k < n; ++k)
                for (int i = 0; i < n; ++i)
                    for (int j = 0; j < n; ++j)
                        dist[i][j] |= dist[i][k] & dist[k][j];
            for (int i = 0; i < n; ++i) {
                boolean all = true;
                for (int j = 0; j < n; ++j)
                    if (!dist[i][j]) all = false;
                if (all)
                    ans[kind]++;
            }
        }
        return ans;
    }
}

package main;

import java.util.Arrays;

//WA
public class TeleportationMaze {
    public int pathLength(String[] a, int r1, int c1, int r2, int c2) {
        int n = a.length;
        int m = a[0].length();
        int[][] cost = new int[n][m];
        Integer oo = Integer.MAX_VALUE;
        for (int i = 0; i < n; ++i)
            Arrays.fill(cost[i], oo);
        cost[r1][c1] = 0;
        int[] dr = {-1, +1, 0, 0};
        int[] dc = {0, 0, -1, +1};
        while (true) {
            boolean upd = false;
            for (int r = 0; r < n; ++r)
                for (int c = 0; c < m; ++c)
                    if (cost[r][c] != oo) {
                        for (int d = 0; d < dr.length; ++d) {
                            int nr = r + dr[d];
                            int nc = c + dc[d];
                            if (0 <= nr && nr < n && 0 <= nc && nc < m && a[nr].charAt(nc) == '.' && cost[nr][nc] > cost[r][c] + 1) {
                                upd = true;
                                cost[nr][nc] = cost[r][c] + 1;
                            }
                        }
                        for (int d = 0; d < dr.length; ++d) {
                            for (int k = 2; ; ++k) {
                                int nr = r + dr[d] * k;
                                int nc = c + dc[d] * k;
                                if (!(0 <= nr && nr < n && 0 <= nc && nc < m))
                                    break;
                                if (a[nr].charAt(nc) == '.') {
                                    if (cost[nr][nc] > cost[r][c] + 2) {
                                        upd = true;
                                        cost[nr][nc] = cost[r][c] + 2;
                                    }
                                    break;
                                }
                            }
                        }
                    }
            if (!upd)
                break;
        }
        int ans = cost[r2][c2];
        if (ans == oo) ans = -1;
        return ans;
    }
}

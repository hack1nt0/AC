package main;

import java.util.Arrays;

public class LumberjackHack {
    public int timeToShore(String[] riverMap) {
        int sr = -1, sc = -1;
        int nrow = riverMap.length, ncol = riverMap[0].length();
        for (int r = 0; r < nrow; ++r)
            for (int c = 0; c < ncol; ++c)
                if (riverMap[r].charAt(c) == '+') {
                    sr = r;
                    sc = c;
                }
        int[][][] minDist = new int[nrow][ncol][2];
        Integer oo = Integer.MAX_VALUE;
        for (int r = 0; r < nrow; ++r)
            for (int c = 0; c < ncol; ++c)
                Arrays.fill(minDist[r][c], oo);
        minDist[sr][sc][0] = 0;
        int[] dr = {+1, -1, 0, 0};
        int[] dc = {0, 0, -1, +1};
        while (true) {
            boolean updated = false;
            for (int r = 0; r < nrow; ++r) {
                for (int c = 0; c < ncol; ++c) {
                    int[] res = {oo, oo};
                    for (int d = 0; d < dr.length; ++d) {
                        int nr = r + dr[d], nc = c + dc[d];
                        if (0 <= nr && nr < nrow && 0 <= nc && nc < ncol) {
                            if (riverMap[r].charAt(c) == '.') {
                                if (minDist[nr][nc][0] != oo)
                                    res[1] = Math.min(res[1], minDist[nr][nc][0] + 3);
                            } else {
                                if (minDist[nr][nc][0] != oo)
                                    res[0] = Math.min(res[0], minDist[nr][nc][0] + (d < 2 ? 1 : 2));
                                if (minDist[nr][nc][1] != oo)
                                    res[1] = Math.min(res[1], minDist[nr][nc][1] + (d < 2 ? 1 : 2));
                            }
                        }
                    }
                    for (int water = 0; water < 2; ++water)
                        if (res[water] < minDist[r][c][water]) {
                            minDist[r][c][water] = res[water];
                            updated = true;
                        }
                }
            }
            if (!updated)
                break;
        }
        int min = oo;
        for (int r = 0; r < nrow; ++r)
            for (int water = 0; water < 2; ++water) {
                min = Math.min(min, minDist[r][0][water]);
                min = Math.min(min, minDist[r][ncol - 1][water]);
            }
        return min == oo ? -1 : min;
    }
}

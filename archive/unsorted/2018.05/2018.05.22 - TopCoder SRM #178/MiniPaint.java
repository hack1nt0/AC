package main;


public class MiniPaint {

    public int leastBad(String[] picture, int maxStrokes) {
        int nrow = picture.length;
        int ncol = picture[0].length();
        maxStrokes = Math.min(maxStrokes, nrow * ncol);
        int[][] dp = new int[nrow + 1][maxStrokes + 1];
        for (int r = 0; r < nrow; ++r) {
            int[][] dpRow = new int[ncol + 1][ncol + 1];
            for (int c = ncol - 1; c >= 0; --c) {
                for (int s = 0; s <= ncol; ++s) {
                    int res = Integer.MAX_VALUE;
                    if (s == 0) {
                        res = ncol - c;
                    } else {
                        for (int nc = c + 1; nc <= ncol; ++nc) {
                            int nb = 0, nw = 0;
                            for (int cc = c; cc < nc; ++cc)
                                if (picture[r].charAt(cc) == 'W') nw++;
                                else nb++;
                            res = Math.min(res, dpRow[nc][s - 1] + nc - c - Math.max(nw, nb));
                        }
                    }
                    dpRow[c][s] = res;
                }
            }
            for (int s = 0; s <= maxStrokes; ++s) {
                int res = Integer.MAX_VALUE;
                for (int used = 0; used <= Math.min(s, ncol); ++used) {
                    res = Math.min(res, dp[r][s - used] + dpRow[0][used]);
                }
                dp[r + 1][s] = res;
            }
        }
        return dp[nrow][maxStrokes];
    }

}

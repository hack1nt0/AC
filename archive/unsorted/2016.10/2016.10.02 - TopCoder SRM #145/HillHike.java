package main;

import java.util.Arrays;

public class HillHike {
    int D, H;
    int[] L;
    long[][][][] dp;


    public long numPaths(int distance, int maxHeight, int[] landmarks) {
        D = distance;
        H = maxHeight;
        L = landmarks;
        dp = new long[D + 1][H + 1][L.length + 1][2];
        for (int i = 0; i <= D; ++i)
            for (int j = 0; j <= H; ++j)
                for (int k = 0; k <= L.length; ++k)
                    Arrays.fill(dp[i][j][k], -1);

        long ans = dfs(0, 0, 0, 0);
        return ans;
    }

    private long dfs(int preD, int preH, int curL, int hasTop) {
        if (preD == D - 1) {
            if (preH == 1 && curL >= L.length && hasTop == 1) return 1;
            return 0;
        }

        long res = dp[preD][preH][curL][hasTop];
        if (res != -1) return res;
        res = 0;

        int[] dx = {-1, 0, +1};
        for (int d = 0; d < dx.length; ++d) {
            int curH = preH + dx[d];
            if (curH <= 0 || H < curH) continue;

            int nxtL = curL;
            if (curL < L.length && L[curL] == curH) nxtL++;

            int nHasTop = hasTop;
            if (curH == H) nHasTop = 1;

            res += dfs(preD + 1, curH, nxtL, nHasTop);

        }

        dp[preD][preH][curL][hasTop] = res;
        return res;
    }
}

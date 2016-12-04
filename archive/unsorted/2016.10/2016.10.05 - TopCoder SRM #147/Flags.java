package main;

import template.MatrixUtils;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Flags {
    long[][] M;
    
    long numFlags;

    long[][] init;


    public long numStripes(String numFlags1, String[] forbidden) {
        numFlags = Long.valueOf(numFlags1);
        int N = forbidden.length;
        M = new long[N + 1][N + 1];
        for (int i = 0; i < forbidden.length; ++i) {
            Arrays.fill(M[i], 1);
            String[] tmp = forbidden[i].split("[ ]");
            for (int j = 0; j < tmp.length; ++j) {
                int fb = Integer.valueOf(tmp[j]);
                M[i][fb] = 0;
            }
            M[i][N] = 0;
        }
        Arrays.fill(M[N], 1);
        init = new long[N + 1][1];
        for (int i = 0; i < N; ++i) init[i][0] = 1;
        MatrixUtils.setOverflowBound(BigInteger.valueOf(numFlags));

        long L = 0, R = Long.MAX_VALUE;
        while (L + 1 < R) {
            long mid = (L + R) / 2;
            if (C(mid)) R = mid;
            else L = mid;
        }
        long ans = R;
        return ans;
    }

    private boolean C(long mid) {

        long[][] cnt = MatrixUtils.mul(MatrixUtils.pow(M, mid - 1), init);
        if (cnt == null) {
            return true;
        }

        Long tot = MatrixUtils.sum(cnt);

        return tot == null;
    }

}

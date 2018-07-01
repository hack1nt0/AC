package main;

import java.util.Arrays;

public class BagOfHolding {
    public double oddsReachable(int[] sizes, int item) {
        int n = sizes.length;
        int less = 0;
        for (int i = 0; i < n; ++i)
            if (sizes[i] < sizes[item])
                ++less;
        int[][] comb = new int[n][n];
        Arrays.fill(comb[0], 0);
        comb[0][0] = 1;
        for (int i = 1; i < n; ++i)
            for (int j = 0; j <= i; ++j)
                if (i - 1 >= 0) {
                    comb[i][j] += comb[i - 1][j];
                    if (j - 1 >= 0)
                        comb[i][j] += comb[i - 1][j - 1];
                }
        int numer = 0;
        for (int pos = 0; pos < n; ++pos) {
            numer += comb[pos][n - less - 1] * fact(n - less - 1) * fact(less);
        }
        return (double) numer / fact(sizes.length);
    }

    private int fact(int n) {
        int res = 1;
        for (int i = 1; i <= n; ++i)
            res *= i;
        return res;
    }
}

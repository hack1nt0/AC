package main;

import java.util.Arrays;

public class ClapLight {
    public int threshold(int[] background) {
        int MAXV = 1000, n = background.length;
        boolean[] lh = new boolean[n];
        for (int min = 0; min <= MAXV + 1; ++min) {
            boolean good = true;
            int nl = 0;
            for (int i = n - 1; i >= 0; --i) {
                lh[i] = background[i] < min;
                if (lh[i])
                    nl++;
                if (i + 4 <= n && lh[i] && !lh[i + 1] && !lh[i + 2] && lh[i + 3])
                    good = false;
            }
            if (good && nl > n / 2)
                return min;
        }
        throw new RuntimeException();
    }
}

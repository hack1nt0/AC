package main;

import java.util.Arrays;

public class LastDigit {
    public long findX(long S) {
        int maxsig = 0;
        long tmps = S;
        while (tmps > 0) {
            maxsig++;
            tmps /= 10;
        }
        int[] d = new int[maxsig];
        tmps = S;
        int cnt = 0;
        while (tmps > 0) {
            d[cnt++] = (int) (tmps % 10);
            tmps /= 10;
        }
        String ans = "";
        for (int i = d.length - 1; i >= 0; --i) {

            if (i != d.length - 1 && greater(d, 9, i + 1)) {
                minus(d, 9, i + 1);
                ans += "9";
            } else if (d[i] == 0) {
                ans += "0";
            }
            else if (greater(d, d[i], i + 1)) {
                int rept = d[i];
                minus(d, rept, i + 1);
                ans += rept;
            } else {
                int rept = d[i] - 1;
                //if (rept == 0) continue;
                minus(d, rept, i + 1);
                ans += rept;
            }
        }

        if (greater(d, 1, 1)) return -1;
        return Long.valueOf(ans);
    }

    private void minus(int[] d, int rept, int reptN) {
        long a = 0, b = 0;
        for (int i = d.length - 1; i >= 0; --i) a = a * 10 + d[i];
        for (int i = reptN - 1; i >= 0; --i) {
            b = b * 10L + rept;
        }

        long res = a - b;
        if (res < 0) {
            throw new RuntimeException();
        }

        int cnt = 0;
        Arrays.fill(d, 0);
        while (res > 0) {
            d[cnt++] = (int) (res % 10);
            res /= 10;
        }
    }

    private boolean greater(int[] d, int rept, int reptN) {
        long a = 0, b = 0;
        for (int i = d.length - 1; i >= 0; --i) a = a * 10 + d[i];
        for (int i = reptN - 1; i >= 0; --i) b = b * 10 + rept;
        return a >= b;
    }
}

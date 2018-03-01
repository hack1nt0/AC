package main;

public class EllysCode01 {
    public long getOnes(long L, long R) {
        long[] ones = new long[61];
        for (int i = 1; i < ones.length; ++i) ones[i] = (1L << i - 1) - ones[i - 1] + ones[i - 1];
        long sumL = count(L, ones);
        long sumR = count(R + 1, ones);
        return sumR - sumL;
    }

    long count(long len, long[] ones) {
        if (len <= 1) return 0;
        long c = 0;
        int sign = +1;
        while (len > 1) {
            int p = (int)(Math.log(len - 1) / Math.log(2));
            c += sign > 0 ? ones[p] : (1L << p) - ones[p];
            sign = -sign;
            len -= 1L << p;
        }
        if (len == 1) c += sign > 0 ? 0 : 1;
        return c;
    }
}

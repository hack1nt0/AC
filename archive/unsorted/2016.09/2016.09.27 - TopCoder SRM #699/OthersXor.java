package main;

import java.math.BigDecimal;
import java.math.BigInteger;

public class OthersXor {
    public long minSum(int[] x) {

        System.out.println(Math.pow(2, -100));
        boolean find = false;
        for (int i = 0; i < x.length; ++i) if (x[i] == -1) find = true;
        if (find) {
            long ans = 0;
            long[] a = new long[x.length];
            long xsum = 0;
            for (int j = 30 - 1; j >= 0; --j) {
                int c0 = 0, c1 = 0;
                for (int i = 0; i < x.length; ++i) {
                    if (x[i] == -1) continue;
                    if ((x[i] >> j & 1) > 0) c0++;
                    else c1++;
                }

                if (c0 % 2 != 0) c0++;
                if (c1 % 2 == 0) c1++;

                if (c0 <= c1) {
                    //ans += (1L << j) * c0;
                    for (int i = 0; i < a.length; ++i) {
                        if (x[i] == -1) continue;
                        if ((x[i] >> j & 1) > 0) a[i] |= 1L << j;
                    }
                } else {
                    //ans += (1L << j) * c1;
                    xsum |= 1L << j;
                    for (int i = 0; i < a.length; ++i) {
                        if (x[i] == -1) continue;
                        if ((x[i] >> j & 1) == 0) a[i] |= 1L << j;
                    }
                }
            }

//            long ans = 0;
            long txs = xsum;
            for (int i = 0; i < a.length; ++i) {
                ans += a[i];
                txs ^= a[i];
            }
            for (int i = 30 - 1; i >= 0; --i)
                if ((txs >> i & 1) > 0)
                    ans += 1L << i;

            return ans;
        }

        //no -1
        long[] a = new long[x.length];
        long xsum = 0;
        for (int j = 30 - 1; j >= 0; --j) {
            int c0 = 0, c1 = 0;
            for (int i = 0; i < x.length; ++i) {
                if (x[i] == -1) continue;
                if ((x[i] >> j & 1) > 0) c0++;
                else c1++;
            }

            if (c0 <= c1 && c0 % 2 == 0 || c1 % 2 == 0) {
                for (int i = 0; i < a.length; ++i) {
                    if (x[i] == -1) continue;
                    if ((x[i] >> j & 1) > 0) a[i] |= 1L << j;
                }
            } else if (c1 % 2 > 0) {
                xsum |= 1L << j;
                for (int i = 0; i < a.length; ++i) {
                    if (x[i] == -1) continue;
                    if ((x[i] >> j & 1) == 0) a[i] |= 1L << j;
                }
            } else {
                return -1;
            }
        }

        long txs = 0;
        for (int i = 0; i < a.length; ++i) txs ^= a[i];
        if (txs != xsum) return -1;

        long ans = 0;
        for (int i = 0; i < a.length; ++i) {
            ans += a[i];
        }
        return ans;
    }
}

package main;

import template.string.StringUtils;

import java.math.BigInteger;

public class LongNumber {
    public int findDigit(int k) {
        long[] accLength = new long[2];
        long[] which = new long[2];
        long[] kInWhich = new long[2];
        for (long i = 1;; ++i) {
            long howMany = (long)(9 * Math.pow(10, i - 1));
            if (accLength[0] < k && k <= howMany * i + accLength[0]) {
                long whichOfLengthI = (k - accLength[0] + i - 1) / i;
                which[0] = (long)Math.pow(10, i - 1) + whichOfLengthI - 1;
                kInWhich[0] = (k - accLength[0] + i - 1) % i;
                break;
            }
            accLength[0] += howMany * i;
        }
        for (int i = 1;; ++i) {
            which[1] = (long)Math.sqrt(Math.pow(9, i));
            long last = (long)Math.sqrt(Math.pow(9, i - 1));
            if (last == 1) last = 0;
            if (accLength[1] < k && k <= (which[1] - last) * i + accLength[1]) {
                long whichOfLengthI = (k - accLength[1] + i - 1) / i;
                which[1] = last + whichOfLengthI;
                kInWhich[1] = (k - accLength[1] + i - 1) % i;
                break;
            }
            accLength[1] += (which[1] - last) * i;
        }
        StringBuffer[] x = new StringBuffer[2];
        for (int i = 0; i < 2; ++i) x[i] = new StringBuffer();
        x[0].append(String.valueOf(which[0]).substring((int) kInWhich[0]));
        x[1].append(String.valueOf((which[1]) * (which[1])).substring((int)kInWhich[1]));
        for (int i = 1; i < 10; ++i) {
            x[0].append(String.valueOf(which[0] + i));
            x[1].append(String.valueOf((which[1] + i) * (which[1] + i)));
        }
        for (int i = 0; i < 2; ++i)
            x[i].append(StringUtils.repeat("0", Math.max(x[0].length(), x[1].length()) - x[i].length()));
        BigInteger sum = new BigInteger(x[0].toString()).add(new BigInteger(x[1].toString()));
        char ans = sum.toString().charAt(0);
        if ((x[0].charAt(0) - '0') + (x[1].charAt(0) - '0') > 9)
            ans = sum.toString().charAt(1);
        return Integer.valueOf("" + ans);
    }
}

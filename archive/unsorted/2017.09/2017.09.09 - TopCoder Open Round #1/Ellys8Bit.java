package main;

public class Ellys8Bit {
    public long getNumber(int N) {
        long res = 0;
        int h = 63;
        int ones = 8;
        long[][] count = new long[h + 1][ones + 1];
        for (int i = 0; i <= h; ++i) {
            for (int j = 0; j <= ones; ++j) {
                if (j == 0) {
                    count[i][j] = 1;
                } else if (i == 0) {
                    count[i][j] = 0;
                } else {
                    count[i][j] = count[i - 1][j] + count[i - 1][j - 1];
                }
            }
        }
        int ith = N;
        for (int i = h; i >= 0; --i) {
            if (ones >= 0 && count[i][ones] < ith) {
                res |= 1;
                ith -= count[i][ones];
                ones--;
            }
            if (i > 0) res <<= 1;
        }
        if (ith > 1) throw new RuntimeException();
        return res;
    }
}

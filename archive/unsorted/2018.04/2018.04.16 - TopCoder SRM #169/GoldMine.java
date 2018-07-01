package main;

public class GoldMine {
    public int[] getAllocation(String[] mines, int miners) {
        long MIN = (long)(-1e20);
        int[][] MINES = new int[mines.length][7];
        for (int i = 0; i < mines.length; ++i) {
            String[] tmp = mines[i].split(", ");
            for (int j = 0; j < 7; ++j)
                MINES[i][j] = Integer.valueOf(tmp[j]);
        }
        int MAX = 6;
        long[][] maxE = new long[mines.length][miners + 1];
        int[][] maxA = new int[mines.length][miners + 1];

        for (int i = mines.length - 1; i >= 0; --i) {
            for (int j = 0; j <= miners; ++j) {
                long res = MIN;
                if (i == mines.length - 1) {
                    res = j <= MAX ? expect(MINES[i], j) : MIN;
                    maxA[i][j] = j;
                } else {
                    for (int k = 0; k <= Math.min(j, MAX); ++k) {
                        if (maxE[i + 1][j - k] == MIN) continue;
                        long resk = expect(MINES[i], k) + maxE[i + 1][j - k];
                        if (resk > res || resk == res && k > maxA[i][j]) {
                            res = resk;
                            maxA[i][j] = k;
                        }
                    }
                }
                maxE[i][j] = res;
            }
        }
        int i = 0, j = miners;
        int[] ans = new int[mines.length];
        for (int k = 0; k < mines.length; ++k) {
            ans[k] = maxA[i][j];
            j -= maxA[i][j];
            ++i;
            if (j == 0)
                break;
            if (j < 0)
                throw new RuntimeException();
        }
        return ans;
    }

    long expect(int[] mine, int na) {
        long res = 0;
        for (int deposit = 0; deposit < mine.length; ++deposit) {
            double v = 0;
            if (deposit > na)
                v += na * 60;
            if (deposit == na)
                v += na * 50;
            if (deposit < na) {
                v += deposit * 50 - (na - deposit) * 20;
            }
            res += v * mine[deposit];
        }
        return res;
    }
}

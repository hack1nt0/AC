package main;

import template.collection.Sorter;

public class PermutationValues {
    public int[] getValues(int[] lows, int[] highs, String lexPos, String[] retInts) {
        int[] rangeIndices = new int[lows.length];
        for (int i = 0; i < rangeIndices.length; ++i) rangeIndices[i] = i;
        Sorter.sort(rangeIndices, (i, j) -> {
            if (lows[i] == lows[j]) return 0;
            return lows[i] > lows[j] ? +1 : -1;
        });
        long len = 0;
        for (int i = 0; i < lows.length; ++i) len += (long)highs[i] - lows[i] + 1;
        int n = 20 + 1;
        long[] pow = new long[n];
        pow[0] = 1;
        for (int i = 1; i < pow.length; ++i) pow[i] = pow[i - 1] * i;
        long[] resultIdx = new long[retInts.length];
        if (len >= n) {
            int size = n;
            long[] lastDigit = new long[size];
            long order = Long.valueOf(lexPos);
            boolean[] visited = new boolean[size];
            for (int i = 0; i < size; ++i) {
                long ii = order / pow[size - 1 - i] + 1;
                for (int j = 0; j < size; ++j) {
                    if (visited[j]) continue;
                    ii--;
                    if (ii == 0) {
                        lastDigit[i] = j;
                        visited[j] = true;
                        break;
                    }
                }
                order %= pow[size - 1 - i];
            }
            for (int i = 0; i < size; ++i) {
                lastDigit[i] += len - n;
            }
            for (int i = 0; i < resultIdx.length; ++i) {
                long d = Long.valueOf(retInts[i]);
                if (d < len - n) resultIdx[i] = d;
                else resultIdx[i] = lastDigit[(int) (d - (len - n))];
            }
        } else {
            int size = (int)len;
            long[] lastDigit = new long[size];
            long order = Long.valueOf(lexPos);
            order %= pow[size];
            boolean[] visited = new boolean[size];
            for (int i = 0; i < size; ++i) {
                long ii = order / pow[size - 1 - i] + 1;
                for (int j = 0; j < size; ++j) {
                    if (visited[j]) continue;
                    ii--;
                    if (ii == 0) {
                        lastDigit[i] = j;
                        visited[j] = true;
                        break;
                    }
                }
                order %= pow[size - 1 - i];
            }
            for (int i = 0; i < resultIdx.length; ++i) {
                long d = Long.valueOf(retInts[i]);
                resultIdx[i] = lastDigit[(int)d];
            }
        }
        int[] result = new int[retInts.length];
        for (int i = 0; i < result.length; ++i) {
            long idx = resultIdx[i];
            for (int j = 0; j < rangeIndices.length; ++j) {
                long span = (long)highs[rangeIndices[j]] - lows[rangeIndices[j]] + 1;
                if (span <= idx) {
                    idx -= span;
                } else {
                    result[i] = lows[rangeIndices[j]] + (int) idx;
                    break;
                }
            }
        }
        return result;
    }
}

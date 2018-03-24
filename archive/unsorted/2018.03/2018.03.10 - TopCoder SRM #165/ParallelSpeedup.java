package main;

public class ParallelSpeedup {
    public int numProcessors2(int k, int overhead) {
        int ans = 1;
        long minCost = k;
        for (int i = 2; i <= k; ++i) {
            long curCost = (long)i * (i - 1) / 2 * overhead + (k + i - 1) / i;
            if (curCost < minCost) {
                minCost = curCost;
                ans = i;
            }
        }
        return ans;
    }

    int k, overhead;

    public int numProcessors(int k, int overhead) {
        this.k = k;
        this.overhead = overhead;
        int ans = 1;
        long minCost = k;
        long[] c = new long[k];
        c[0] = k;
        for (int p = 2; p <= k; ++p) c[p - 1] = cost(p);
        long[] g = new long[k - 1];
        for (int p = 1; p < k; ++p) g[p - 1] = c[p] - c[p - 1];
        for (int p = 1; p < k - 1; ++p)
            if (g[p] < g[p - 1]) {
                throw new RuntimeException();
            }

        int left = 2, right = k + 1;
        while (right - left >= 10) {
            int mid = left + (right - left) / 2;
            int mid2 = mid + (right - mid) / 2;
            if (cost(mid) < cost(mid2))
                right = mid2;
            else
                left = mid;
        }
        for (int p = left; p < right; ++p) {
            long curCost = cost(p);
            if (curCost < minCost) {
                minCost = curCost;
                ans = p;
            }
        }
        return ans;
    }

    long cost(int p) {
        return (long) p * (p - 1) / 2 * overhead + (k + p - 1) / p;
    }
}

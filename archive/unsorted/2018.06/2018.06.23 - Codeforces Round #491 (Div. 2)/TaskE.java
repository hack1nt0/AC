package main;

import template.debug.InputReader;
import template.debug.OutputWriter;
import template.numbers.IntUtils;

public class TaskE {
    long tot = 0;
    long[] fact;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long n = in.readLong();
        int[] count = new int[10];
        for (char c : ("" + n).toCharArray())
            count[c - '0']++;
        fact = new long[21];
        fact[0] = 1;
        for (int i = 1; i < fact.length; ++i)
            fact[i] = fact[i - 1] * i;
        int[] use = new int[10];
        dfs(0, use, count);
        out.printLine(tot);
    }

    private void dfs(int cur, int[] use, int[] count) {
        if (cur == 10) {
            long denom = 1, numer = 0;
            for (int d = 0; d < 10; ++d) {
                numer += use[d];
                denom *= fact[use[d]];
            }
            long delta = fact[(int) numer] / denom;
            if (use[0] > 0) {
                numer = 0;
                denom = 1L;
                for (int d = 1; d < 10; ++d) {
                    numer += use[d];
                    denom *= fact[use[d]];
                }
                numer += use[0] - 1;
                denom *= fact[use[0] - 1];
                delta -= fact[(int) numer] / denom;
            }
            tot += delta;
            return;
        }
        if (count[cur] > 0) {
            for (int nu = 1; nu <= count[cur]; ++nu) {
                use[cur] = nu;
                dfs(cur + 1, use, count);
            }
        } else
            dfs(cur + 1, use, count);
    }
}

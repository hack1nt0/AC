package main;

import template.numbers.IntUtils;

import java.util.Arrays;

public class NCMultiplication {
    long min, aa;

    public long findFactors(int[] c) {
        int n = c.length;
        int[] a = new int[n];
        int[] b = new int[n];
        for (int i = 0; i < n / 2; ++i) {
            int t = c[i];
            c[i] = c[n - i - 1];
            c[n - i - 1] = t;
        }
        min = Long.MAX_VALUE;
        aa = -1;
        dfs(0, a, b, c);
        return aa;
    }

    private void dfs(int cur, int[] a, int[] b, int[] c) {
        if (cur == c.length) {
            for (int i = cur; i <= (cur - 1) * 2; ++i) {
                int d = 0;
                for (int j = 0; j < cur; ++j) {
                    int k = i - j;
                    if (0 <= k && k < cur)
                        d += a[j] * b[k];
                }
                if (d != 0)
                    return;
            }
            long aa = IntUtils.fromArray(a);
            long bb = IntUtils.fromArray(b);
            if (aa < bb) {
                long t = aa;
                aa = bb;
                bb = t;
            }
            if (aa - bb < min) {
                min = aa - bb;
                this.aa = aa;
            }
//            System.out.println(aa + " " + bb);
            return;
        }
        for (int da = 0; da < 10; ++da) {
            a[cur] = da;
            int db = -1;
            if (cur == 0 && da != 0 && c[cur] % da == 0 && c[cur] / da < 10)
                db = c[cur] / da;
            else if (cur > 0) {
                db = c[cur];
                for (int i = cur; i > 0; --i)
                    db -= a[i] * b[cur - i];
                if (a[0] == 0)
                    throw new RuntimeException();
                if (db >= 0 && db % a[0] == 0 && db / a[0] < 10)
                    db /= a[0];
                else
                    db = -1;
            }
            if (db != -1) {
                a[cur] = da;
                b[cur] = db;
                dfs(cur + 1, a, b, c);
            }
        }
    }
}

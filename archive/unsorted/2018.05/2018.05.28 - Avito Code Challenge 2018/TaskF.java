package main;

import java.util.Arrays;
import java.util.Scanner;
import java.io.PrintWriter;

public class TaskF {
    long[] girls, boys;
    long L; int N;
    int[] from, to;
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        N = in.nextInt();
        L = in.nextLong();
        girls = new long[N];
        for (int i = 0; i < N; ++i) girls[i] = in.nextLong();
        boys = new long[N];
        for (int i = 0; i < N; ++i) boys[i] = in.nextLong();
        from = new int[N];
        to = new int[N];
        long l = -1, r = L;
        while (l + 1 < r) {
            long mid = r - (r - l) / 2;
            Arrays.fill(from, -1);
            if (sat(mid, 0))
                r = mid;
            else
                l = mid;
            System.out.println(l + " " + r + " " + mid);

        }
        out.println(r);
    }

    private boolean sat(long mid, int s) {
        if (s >= N)
            return true;
        for (int t = 0; t < N; ++t)
            if (dist(boys[s], girls[t]) <= mid) {
                if (from[t] == -1) {
                    from[t] = s;
                    if (sat(mid, s + 1))
                        return true;
                    from[t] = -1;
                } else if (from[t] < s) {
                    int tmp = from[t];
                    from[t] = s;
                    if (sat(mid, tmp))
                        return true;
                    from[t] = tmp;
                }
            }
        return false;
    }

    long dist(long from, long to) {
        long d = Math.abs(to - from);
        d = Math.min(d, L - d);
        return d;
    }
}

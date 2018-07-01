package main;

import template.debug.InputReader;
import template.debug.OutputWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long n = in.readLong();
        long l = 0, r = n;
        while (l + 1 < r) {
            long mid = r - (r - l) / 2;
            long left = n, pick = 0;
            while (true) {
                if (left <= mid) {
                    pick += left;
                    break;
                }
                pick += mid;
                left -= mid;
                left -= (long) ((double) left * 0.1);
                if (left < 0)
                    throw new RuntimeException();
            }
            if (pick >= n - pick)
                r = mid;
            else
                l = mid;
        }
        out.printLine(r);
    }
}

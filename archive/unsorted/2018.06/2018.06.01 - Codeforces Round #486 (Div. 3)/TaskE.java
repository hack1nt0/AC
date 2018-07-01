package main;

import template.debug.InputReader;
import template.debug.OutputWriter;

public class TaskE {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long n = in.readLong();
        int[] digits = new int[Long.toString(n).length()];
        long nn = n;
        for (int i = 0; i < digits.length; ++i) {
            if (nn == 0)
                throw new RuntimeException();
            digits[i] = (int)(nn % 10);
            nn /= 10;
        }
        if (digits.length < 2) {
            out.printLine(-1);
            return;
        }
        int oo = Integer.MAX_VALUE;
        int minMoves = oo;
        for (int t : new int[]{00, 25, 50, 75}) {
            int[] tarray = {t % 10, t / 10};
            int[] ii = {0, 1};
            boolean good = true;
            int moves = 0;
            int[] d = digits.clone();
            for (int i : ii) {
                boolean found = false;
                for (int j = i; j < d.length; ++j) {
                    if (d[j] == tarray[i] && (j != d.length - 1 || d[j - 1] != 0)) {
                        found = true;
                        for (int k = j; k > i; --k)
                            d[k] = d[k - 1];
                        d[i] = tarray[i];
                        moves += j - i;
                        break;
                    }
                }
                if (!found) {
                    good = false;
                    break;
                }
            }
            if (good) {
                minMoves = Math.min(minMoves, moves);
            }
        }
        out.printLine(minMoves == oo ? -1 : minMoves);
    }
}

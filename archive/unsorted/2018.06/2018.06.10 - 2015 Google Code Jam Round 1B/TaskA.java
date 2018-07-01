package main;

import template.debug.InputReader;
import template.debug.OutputWriter;
import template.numbers.IntUtils;

import static template.numbers.IntUtils.reverse;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long N = in.readLong();
        long min = 0;
        if (N <= 10) {
            min = N;
        } else {
            min += 10;
            for (int ndigit = 2; ndigit <= IntUtils.digitCount(N); ++ndigit) {
                long from = (long) Math.pow(10, ndigit - 1);
                long to = ndigit == IntUtils.digitCount(N) ? N : (long) Math.pow(10, ndigit + 1 - 1);
                if (from == to)
                    break;
                int[] digits = IntUtils.digits(to);
                if (to % 10 == 0) {
                    digits = IntUtils.digits(to - 1);
                }
                long half = 0;
                for (int i = ndigit / 2; i < digits.length; ++i) {
                    half = half * 10 + digits[i];
                }
                min += half;
                if (from + half != reverse(from + half)) {
                    min += 1;
                }
                min += to - reverse(from + half);
            }
        }
        out.printLine("Case #" + testNumber + ": " + min);

    }

}

package main;

import template.debug.InputReader;
import template.debug.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int N = in.readInt();
        int P = in.readInt();
        char[] s = in.readString().toCharArray();
        char ONE = '1', ZERO = '0', DOT = '.';
        boolean ok = false;
        for (int offset = 0; offset < P; ++offset) {
            int pos = offset;
            int[] count = new int[128];
            while (pos < s.length) {
                count[s[pos]]++;
                pos += P;
            }
            boolean sat1 = count[ZERO] > 0 && count[ONE] > 0;
            boolean sat2 = count[ZERO] == 0 && count[ONE] == 0 && count[DOT] > 1;
            boolean sat3 = (count[ZERO] > 0 || count[ONE] > 0) && count[DOT] > 0;
            ok |= sat1 || sat2 || sat3;
            if (sat2) {
                pos = offset;
                int c = 0;
                while (pos < s.length) {
                    if (s[pos] == DOT)
                        s[pos] = c++ < 1 ? ZERO : ONE;
                    pos += P;
                }
                if (c != count[DOT])
                    throw new RuntimeException();
            } else {
                pos = offset;
                while (pos < s.length) {
                    if (s[pos] == DOT)
                        s[pos] = count[ZERO] > 0 ? ONE : ZERO;
                    pos += P;
                }
            }
        }
        if (ok) {
            out.printLine(s);
        } else {
            out.printLine("No");
        }
    }
}

package main;

import template.debug.InputReader;
import template.debug.OutputWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        long ans = 0;
        for (int i = 0; i < n; ++i) {
            int d = in.readInt();
            int ptwo = 0;
            while (d % 2 == 0) {
                ptwo++;
                d /= 2;
            }
            ans += ptwo;
        }
        out.printLine(ans);
    }
}

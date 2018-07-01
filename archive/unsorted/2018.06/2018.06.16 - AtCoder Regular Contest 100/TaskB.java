package main;

import template.debug.InputReader;
import template.debug.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int d = in.readInt();
        int n = in.readInt();
        int ans = 1;
        for (int i = 1; i <= d; ++i)
            ans *= 100;
        ans *= n < 100 ? n : 101;
        out.printLine(ans);
    }
}

package main;

import template.debug.InputReader;
import template.debug.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int a = in.readInt();
        int b = in.readInt();
        int c = in.readInt();
        int n = in.readInt();
        int ans = -1;
        if (a >= c && b >= c && a + b - c >= 0 && a + b - c < n)
            ans = n - (a + b - c);
        if (!(1 <= ans && ans <= n))
            ans = -1;
        out.printLine(ans);
    }
}

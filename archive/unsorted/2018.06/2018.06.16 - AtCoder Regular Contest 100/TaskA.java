package main;

import template.debug.InputReader;
import template.debug.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int a = in.readInt();
        int b = in.readInt();
        String ans = a <= 8 && b <= 8 ? "Yay!" : ":(";
        out.printLine(ans);
    }
}

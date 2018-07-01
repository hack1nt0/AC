package main;

import template.debug.InputReader;
import template.debug.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int min = 0;
        for (int d : new int[] {100, 20, 10, 5, 1}) {
            while (n >= d) {
                min++;
                n -= d;
            }
        }
        if (n > 0)
            throw new RuntimeException();
        out.printLine(min);
    }
}

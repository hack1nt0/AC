package main;

import template.debug.InputReader;
import template.debug.OutputWriter;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt() * 2;
        int[] line = new int[n];
        for (int i = 0; i < n; ++i)
            line[i] = in.readInt();
        int min = 0;
        for (int i = 0; i < n; i += 2) {
            int j = i + 1;
            while (line[j] != line[i]) ++j;
            min += j - i - 1;
            for (int k = j; k > i; --k)
                line[k] = line[k - 1];
        }
        out.printLine(min);
    }
}

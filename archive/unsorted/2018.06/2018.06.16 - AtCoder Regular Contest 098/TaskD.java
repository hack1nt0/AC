package main;

import template.debug.InputReader;
import template.debug.OutputWriter;

import java.util.Arrays;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] xs = new int[n];
        for (int i = 0; i < n; ++i)
            xs[i] = in.readInt();
        int[] bitCount = new int[20];
        long ans = 0;
        for (int from = 0, to = -1; from < n; ++from) {
            if (to < n) {
                while (true) {
                    boolean end = false;
                    for (int i = 0; i < bitCount.length; ++i) {
                        if (bitCount[i] == 2)
                            end = true;
                    }
                    if (end)
                        break;
                    to++;
                    if (to == n)
                        break;
                    for (int i = 0; i < bitCount.length; ++i) {
                        bitCount[i] += xs[to] >> i & 1;
                    }
                }
            }
            ans += to - from;
            for (int i = 0; i < bitCount.length; ++i) {
                bitCount[i] -= xs[from] >> i & 1;
            }
        }
        out.printLine(ans);
    }
}

package main;

import template.collection.sequence.ArrayUtils;
import template.debug.InputReader;
import template.debug.OutputWriter;

import java.util.Arrays;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] lab = new int[n];
        for (int i = 0; i < n; ++i)
            lab[i] = in.readInt();
        double tot = ArrayUtils.sum(lab);
        Arrays.sort(lab);
        int min = 0;
        for (int i = 0; i < n; ++i) {
            if (Math.round(tot / n) >= 5L)
                break;
            if (lab[i] < 5) {
                min++;
                tot += 5 - lab[i];
            }
        }
        out.printLine(min);
    }
}

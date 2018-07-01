package main;

import template.collection.sequence.ArrayUtils;
import template.debug.InputReader;
import template.debug.OutputWriter;

//WA
public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] people = new int[n];
        for (int i = 0; i < n; ++i)
            people[i] = in.readInt();
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; ++i)
            min = Math.min(min, people[i]);
        int sp = min % n;
        for (int p = sp;; p = (p + 1) % n) {
            if (people[p] - min - (p - sp) <= 0) {
                out.printLine(p + 1);
                return;
            }
        }
    }
}

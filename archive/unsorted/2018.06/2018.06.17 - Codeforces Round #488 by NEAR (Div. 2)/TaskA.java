package main;

import template.debug.InputReader;
import template.debug.OutputWriter;

import java.util.HashSet;
import java.util.Set;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        int[] a = new int[n];
        for (int i = 0; i < n; ++i)
            a[i] = in.readInt();
        Set<Integer> valid = new HashSet<>();
        for (int i = 0; i < m; ++i)
            valid.add(in.readInt());
        String ans = "";
        for (int i = 0; i < n; ++i)
            if (valid.contains(a[i]))
                ans += (ans.length() > 0 ? " " : "") + a[i];
        out.printLine(ans);
    }
}

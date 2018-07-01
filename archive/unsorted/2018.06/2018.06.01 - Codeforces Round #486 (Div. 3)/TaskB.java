package main;

import template.debug.InputReader;
import template.debug.OutputWriter;

import java.util.Arrays;
import java.util.Comparator;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int N = in.readInt();
        String[] strings = new String[N];
        for (int i = 0; i < N; ++i) strings[i] = in.next();
        Arrays.sort(strings, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });
        for (int i = 1; i < N; ++i) {
            for (int j = 0; j < i; ++j) {
                if (!strings[i].contains(strings[j])) {
                    out.printLine("NO");
                    return;
                }
            }
        }
        out.printLine("YES");
        for (int i = 0; i < N; ++i)
            out.printLine(strings[i]);
    }
}

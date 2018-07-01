package main;

import template.debug.InputReader;
import template.debug.OutputWriter;

import java.util.Arrays;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String s = in.readString();
        for (int i = 0; i + 3 <= s.length(); ++i) {
            char[] chars = s.substring(i, i + 3).toCharArray();
            Arrays.sort(chars);
            if (new String(chars).equals("ABC")) {
                out.printLine("Yes");
                return;
            }
        }
        out.printLine("No");
    }
}

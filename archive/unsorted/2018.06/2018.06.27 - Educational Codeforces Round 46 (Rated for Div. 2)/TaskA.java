package main;

import template.debug.InputReader;
import template.debug.OutputWriter;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Set;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        String[][] tshirt = new String[2][n];
        for (int k = 0; k < 2; ++k) {
            for (int i = 0; i < n; ++i)
                tshirt[k][i] = in.readString();
        }
        int ans = 0;
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; ++i) {
            boolean dup = false;
            for (int j = 0; j < n; ++j)
                if (!visited[j] && tshirt[1][i].equals(tshirt[0][j])) {
                    visited[j] = true;
                    dup = true;
                    break;
                }
            if (!dup)
                ans++;
        }
        out.printLine(ans);
    }
}

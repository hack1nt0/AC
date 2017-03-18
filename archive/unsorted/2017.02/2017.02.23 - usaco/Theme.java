package main;

import java.util.Arrays;
import java.util.Scanner;
import java.io.PrintWriter;

/*
 ID: hackint1
 LANG: JAVA
 TASK: theme
*/

public class Theme {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int[] notes = new int[n];
        for (int i = 0; i < n; ++i) notes[i] = in.nextInt();
        int[][] theme = new int[2][n];
        Arrays.fill(theme[0], 1);

        int longest = 0;
        for (int i = 1; i < n; ++i) {
            int cur = i % 2, pre = (cur - 1 + 2) % 2;
            for (int j = i + 1; j < n; ++j) {
                int res = 1;
                if (notes[j] - notes[i] == notes[j - 1] - notes[i - 1])
                    res += theme[pre][j - 1];
                theme[cur][j] = res;
                longest = Math.max(longest, Math.min(j - i, theme[cur][j]));
            }
        }

        longest = longest >= 5 ? longest : 0;
        out.println(longest);
    }
}

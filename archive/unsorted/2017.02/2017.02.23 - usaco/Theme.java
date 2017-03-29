package main;



import template.string.SuffixArray;

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
        solve1(testNumber, in, out);
    }

    /**
     *
     Executing...
     Test 1: TEST OK [0.180 secs, -1194644 KB]
     Test 2: TEST OK [0.173 secs, -1194644 KB]
     Test 3: TEST OK [0.158 secs, -1194644 KB]
     Test 4: TEST OK [0.166 secs, -1194644 KB]
     Test 5: TEST OK [0.187 secs, -1194644 KB]
     Test 6: TEST OK [0.209 secs, -1194644 KB]
     Test 7: TEST OK [0.223 secs, -1194644 KB]
     Test 8: TEST OK [0.288 secs, -1194644 KB]
     Test 9: TEST OK [0.331 secs, -1194644 KB]
     Test 10: TEST OK [0.180 secs, -1194644 KB]
     Test 11: TEST OK [0.605 secs, -1194644 KB]
     Test 12: TEST OK [0.504 secs, -1194644 KB]
     Test 13: TEST OK [0.504 secs, -1194644 KB]
     Test 14: TEST OK [0.540 secs, -1194644 KB]
     Test 15: TEST OK [0.562 secs, -1194644 KB]

     All tests OK.
     */
    public void solve1(int testNumber, Scanner in, PrintWriter out) {
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

    /**
     * Executing...
     Test 1: TEST OK [0.173 secs, -1194644 KB]
     Test 2: TEST OK [0.216 secs, -1194644 KB]
     Test 3: TEST OK [0.216 secs, -1194644 KB]
     Test 4: TEST OK [0.173 secs, -1194644 KB]
     Test 5: TEST OK [0.194 secs, -1194644 KB]
     Test 6: TEST OK [0.187 secs, -1228444 KB]
     Test 7: TEST OK [0.238 secs, -1194644 KB]
     Test 8: TEST OK [0.346 secs, -1194644 KB]
     Test 9: TEST OK [0.396 secs, -1194644 KB]
     Test 10: TEST OK [0.194 secs, -1194644 KB]
     Test 11: TEST OK [0.475 secs, -1194644 KB]
     Test 12: TEST OK [0.533 secs, -1194644 KB]
     Test 13: TEST OK [0.490 secs, -1194644 KB]
     Test 14: TEST OK [0.504 secs, -1194644 KB]
     Test 15: TEST OK [0.562 secs, -1194644 KB]
     All tests OK.
     */
    public void solve2(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int[] notes = new int[n];
        for (int i = 0; i < n; ++i) notes[i] = in.nextInt();
        int[] diff = new int[n]; diff[n - 1] = notes[n - 1];
        for (int i = 0; i < n - 1; ++i) diff[i] = notes[i] - notes[i + 1];
        SuffixArray sa = new SuffixArray(diff);
        int[] height = sa.getHeight();
        int[] index = sa.getIndex();
        int from = 0, to = n - 1;
        while (from + 1 < to) {
            int mid = from + (to - from) / 2;
            boolean ok = false;
            int p = 0, q = 1;
            while (q < n) {
                int minIndex = Math.min(index[p], index[q]);
                int maxIndex = Math.max(index[p], index[q]);
                while (q < n && height[q] >= mid) {
                    minIndex = Math.min(minIndex, index[q]);
                    maxIndex = Math.max(maxIndex, index[q]);
                    q++;
                }
                if (p + 1 < q && maxIndex - minIndex > mid) {
                    ok = true; break;
                }
                p = q;
                q++;
            }
            if (ok) from = mid;
            else to = mid;
        }

        int ans = from + 1;
        out.println(ans >= 5 ? ans : 0);
    }

}

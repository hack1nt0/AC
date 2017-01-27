package main;

import java.util.Scanner;
import java.io.PrintWriter;
/*
 ID: hackint1
 LANG: JAVA
 TASK: hamming
*/
public class Hamming {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int b = in.nextInt();
        int d = in.nextInt();
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            for (int s = i > 0 ? ans[i - 1] : 0; s < 1 << b; ++s) {
                boolean ok = true;
                for (int j = 0; j < i; ++j) {
                    if (Integer.bitCount(s ^ ans[j]) < d) {ok = false; break;}
                }
                if (ok) {
                    ans[i] = s;
                    break;
                }
            }
        }

        for (int i = 0; i < n; ++i) {
            if (i / 10 > 0 && i % 10 == 0) out.println();
            if (i % 10 != 0) out.print(" ");
            out.print(ans[i]);
        }
        out.println();
    }
}

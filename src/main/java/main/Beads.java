package main;

import java.util.Scanner;
import java.io.PrintWriter;

/*
 ID: hackint1
 LANG: JAVA
 TASK: beads
*/

public class Beads {
    int N;
    String necklace;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        N = in.nextInt();
        necklace = in.next();
        necklace += necklace;

        int maxb = 0;
        boolean singleColor = true;
        for (int i = 0; i < necklace.length() - 1; ++i) {
            if (necklace.charAt(i) ==  necklace.charAt(nxt(i))) continue;
            singleColor = false;
            int l = pre(i);
            char lc = necklace.charAt(i);
            while (true) {
                if (l < 0) break;
                char cur = necklace.charAt(l);
                if (lc == 'w') lc = cur;
                if (lc != cur && cur != 'w') break;
                l = pre(l);
            }

            int r = nxt(nxt(i));
            char rc = necklace.charAt(nxt(i));
            while (true) {
                if (r >= necklace.length()) break;
                char cur = necklace.charAt(r);
                if (rc == 'w') rc = cur;
                if (rc != cur && cur != 'w') break;
                r = nxt(r);
            }

            maxb = Math.max(maxb, Math.min(N, r - l - 1));
        }

        if (singleColor) maxb = N;
        out.println(maxb);
    }

    private boolean same(int i, int j) {
        int a = necklace.charAt(i);
        int b = necklace.charAt(j);
        return a == b;
    }

    private int pre(int i) {
        return i - 1;
    }

    private int nxt(int i) {
        return i + 1;
    }
}

package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.PrintWriter;

/*
 ID: hackint1
 LANG: JAVA
 TASK: dualpal
*/

public class Dualpal {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int N = in.nextInt();
        int S = in.nextInt();
        List<Integer> ans = new ArrayList<>();
        while (true) {
            if (ans.size() >= N) break;
            S++;
            int cb = 0;
            for (int base = 2; base <= 10; ++base) {
                if (cb >= 2) break;
                if (isPal(Integer.toString(S, base))) cb++;
            }

            if (cb >= 2) ans.add(S);
            //System.out.println(S);
        }

        for (int r : ans) out.println(r);
    }

    private boolean isPal(String s) {
        int l = 0, r = s.length() - 1;
        while (true) {
            if (l >= r) break;
            if (s.charAt(l++) != s.charAt(r--)) return false;
        }
        return true;
    }
}

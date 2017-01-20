package main;

import edu.princeton.cs.algs4.Quick3way;

import java.util.Scanner;
import java.io.PrintWriter;

/*
 ID: hackint1
 LANG: JAVA
 TASK: palsquare
*/

public class PalSquare {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int B = in.nextInt();
        for (int n = 1; n <= 300; ++n) {
            String nn = Integer.toString(n, B).toUpperCase();
            String sq = Integer.toString(n * n, B).toUpperCase();
            if (isPal(sq)) {
                out.println(nn + " " + sq);
            }
        }
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

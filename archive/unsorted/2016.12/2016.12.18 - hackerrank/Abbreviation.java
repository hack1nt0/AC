package main;

import java.util.Scanner;
import java.io.PrintWriter;

public class Abbreviation {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        String a = in.next();
        String b = in.next();
        boolean[][] dp = new boolean[a.length() + 1][b.length() + 1];
        for (int i = 0; i <= a.length(); ++i) dp[i][0] = true;
        for (int i = 0; i < a.length(); ++i)
            for (int j = 0; j < b.length(); ++j) {
                char ca = a.charAt(i), cb = b.charAt(j);
                boolean res = false;
                if (!Character.isLowerCase(ca) && ca != cb) {
                    res = false;
                } else if (ca == cb) {
                    res = dp[i][j];
                } else if (Character.toUpperCase(ca) == cb) {
                    res = dp[i][j] || dp[i][j + 1];
                } else {
                    res = dp[i][j + 1];
                }
                dp[i + 1][j + 1] = res;
            }
        out.println(dp[a.length()][b.length()] ? "YES" : "NO");
    }
}

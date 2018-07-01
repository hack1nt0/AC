package main;

import java.util.Scanner;
import java.io.PrintWriter;

public class TaskA {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        String string = in.next();
        int max = 0;
        for (int len = string.length(); len > 0; --len) {
            for (int i = 0; i + len <= string.length(); ++i) {
                boolean good = true;
                int l = i, r = i + len - 1;
                while (l < r) {
                    if (string.charAt(l) != string.charAt(r))
                        good = false;
                    l++;
                    r--;
                }
                if (!good) {
                    max = Math.max(max, len);
                    break;
                }
            }
        }
        out.println(max);
    }
}

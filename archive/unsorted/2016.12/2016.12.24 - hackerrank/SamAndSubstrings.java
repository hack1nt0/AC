package main;

import template.Numbers;

import java.util.Scanner;
import java.io.PrintWriter;
import static template.Numbers.*;

public class SamAndSubstrings {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        String balls = in.next();
        int N = balls.length();
        Numbers.MODULUS = (long)1e9 + 7;
        long ret = 0, suffix = 0;
        for (int i = 0; i < N; ++i) {
            int d = balls.charAt(i) - '0';
            suffix = add(mul(suffix, 10), i * d + d);
            ret = add(ret, suffix);

        }
        out.println(ret);
    }
}

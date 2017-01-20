package main;

import java.util.Scanner;
import java.io.PrintWriter;

public class Ride {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int MODULUS = 47;
        int a = 1;
        String A = in.next();
        for (int i = 0; i < A.length(); ++i) a = (a * (A.charAt(i) - 'A' + 1)) % MODULUS;

        int b = 1;
        String B = in.next();
        for (int i = 0; i < B.length(); ++i) b = (b * (B.charAt(i) - 'A' + 1)) % MODULUS;

        out.println(a == b ? "GO" : "STAY");
    }
}

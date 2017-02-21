package main;

import template.numbers.Rational;

import java.util.Scanner;
import java.io.PrintWriter;
/*
 ID: hackint1
 LANG: JAVA
 TASK: fracdec
*/
public class Fracdec {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int numerator = in.nextInt();
        int denominator = in.nextInt();
        String decimal = new Rational(numerator, denominator).toDecimal();
        for (int i = 0; i < decimal.length(); ++i) {
            if (i > 0 && i % 76 == 0) out.println();
            out.print(decimal.charAt(i));
        }
        out.println();
    }
}

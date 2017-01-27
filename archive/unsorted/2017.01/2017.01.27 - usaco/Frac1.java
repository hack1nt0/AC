package main;

import template.numbers.IntegerUtils;
import template.numbers.Rational;

import java.util.*;
import java.io.PrintWriter;
/*
 ID: hackint1
 LANG: JAVA
 TASK: frac1
*/
public class Frac1 {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        Set<Rational> set = new HashSet<>();
        for (int i = 0; i <= n; ++i)
            for (int j = i; j <= n; ++j) {
                if (j == 0) continue;
                if (IntegerUtils.gcd(i, j) != 1) continue;
                set.add(new Rational(i, j));
            }

        Rational[] ans = set.toArray(new Rational[0]);
        Arrays.sort(ans);
        for (Rational rational : ans) out.println(rational);
    }
}

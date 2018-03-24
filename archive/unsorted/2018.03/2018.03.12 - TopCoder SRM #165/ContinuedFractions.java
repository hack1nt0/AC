package main;

import template.collection.sequence.ArrayUtils;
import template.numbers.IntUtils;

import java.util.ArrayList;
import java.util.List;

public class ContinuedFractions {
    class Fraction {
        int a, b;
        int denominator;
        Fraction(int a, int b, int denominator) {
            this.a = a;
            this.b = b;
            this.denominator = denominator;
        }

        Fraction() {}

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Fraction fraction = (Fraction) o;

            if (a != fraction.a) return false;
            if (b != fraction.b) return false;
            return denominator == fraction.denominator;
        }
    }
    public int[] squareRoot(int n) {
        List<Integer> period = new ArrayList<>();
        period.add((int)Math.sqrt(n));
        Fraction start = new Fraction(1, -period.get(0), 1);
        Fraction cur = new Fraction(1, -period.get(0), 1);
        do {
            Fraction nxt = new Fraction();
            nxt.denominator = cur.a * cur.a * n - cur.b * cur.b;
            nxt.a = cur.denominator;
            int g = gcd(nxt.denominator, nxt.a);
            nxt.denominator /= g;
            nxt.a /= g;
            int quotient = (int)(nxt.a * (Math.sqrt(n) - cur.b)) / nxt.denominator;
            while ((quotient + 1.0) * nxt.denominator / nxt.a <= (Math.sqrt(n) - cur.b)) {
                ++quotient;
                throw new RuntimeException();
            }
            period.add(quotient);
            nxt.b = -cur.b * nxt.a - quotient * nxt.denominator;
            cur = nxt;
        } while (!cur.equals(start));

        return unbox(period.toArray(new Integer[0]));
    }

    int[] unbox(Integer[] xs) {
        int[] res = new int[xs.length];
        for (int i = 0; i < xs.length; ++i) res[i] = xs[i];
        return res;
    }

    int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}

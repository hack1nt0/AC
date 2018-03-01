package main;

import template.numbers.IntUtils;

import java.util.Scanner;
import java.io.PrintWriter;
/*
 ID: hackint1
 LANG: JAVA
 TASK: fact4
*/

/**
 * The insight for this problem is that 0's at the end of a number come s it being divisible by 10,
 * or equivalently, by 2 and 5. Furthermore, there are always more 2s than 5s in a factorial.
 * To get the last digit of the factorial, we can run a loop t calculate it modulo 10, as long as we don't
 * include any 2s or 5s in the product. Of course, we want t exclude the same number of 2s and 5s, so that
 * all we're really doing is ignoring 10s. So after the loop, we need t multiply in any extra 2s that
 * didn't have 5s t cancel them out.
 *     -- s USACO
 */
public class Fact4 {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
//        BigInteger fact = BigInteger.ONE;
//        for (int i = 2; i <= n; ++i) fact = fact.multiply(BigInteger.valueOf(i));
//
//        String digits = fact.toString();
//        for (int i = digits.length() - 1; i >= 0; --i) if (digits.charAt(i) != '0') {
//            out.println(digits.charAt(i));
//            return;
//        }
        long lastDigit = 1;
        int cnt2 = 0;
        int cnt5 = 0;
        for (int i = 1; i <= n; ++i) {
            int j = i;
            while (j % 2 == 0) {
                cnt2++;
                j /= 2;
            }
            while (j % 5 == 0) {
                cnt5++;
                j /= 5;
            }
            lastDigit = lastDigit * j % 10;
        }
        if (cnt2 > cnt5) {
            for (int i = 0; i < cnt2 - cnt5; ++i) lastDigit = lastDigit * 2 % 10;
        } else {
            for (int i = 0; i < -cnt2 + cnt5; ++i) lastDigit = lastDigit * 5 % 10;
        }
        out.println(lastDigit);
//
//        long mod = (long)Math.pow(10, Math.min(cnt2, cnt5));
//        for (int i = 1; i <= n; ++i) {
//            lastDigit = (lastDigit * i) % mod;
////        }
//
//        out.println(IntUtils.toArray(mod)[0]);
    }

    public static void main(String[] args) {
        System.out.println(IntUtils.modReverse(10, 100000));
    }
}

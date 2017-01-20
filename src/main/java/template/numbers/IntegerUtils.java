package template.numbers;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by dy on 16-10-8.
 *
 * Number Theory(NT) utils.
 *
 * This is a world of mod, all values return are no-negative.
 *
 */
public class IntegerUtils {

    public static long modulus = Long.MAX_VALUE;

    public static long ensurePositive(long a) {
        if (a < 0) return (a % modulus + modulus) % modulus;
        return a;
    }

    // what about a < 0 or b < 0 ?
    public static long lcm(long a, long b) {
        long d = gcd(a, b);
        if (d == 0) return 0;
        BigInteger ans = BigInteger.valueOf(a).divide(BigInteger.valueOf(d)).multiply(BigInteger.valueOf(b));
        if (ans.compareTo(BigInteger.valueOf(Long.MAX_VALUE)) > 0) {
            System.err.println("lcm is out of bound!");
        }
        return ans.mod(BigInteger.valueOf(modulus)).longValue();
    }

    public static long gcd(long a, long b) {
        if (b == 0) return Math.abs(a);
        return gcd(b, a % b);
    }

    public static long mul(long a, long b) {
        a %= modulus;
        b %= modulus;
        return ensurePositive(a * b % modulus);
    }

    public static long mul(long a, long b, long c) {
        return mul(a, mul(b, c));
    }

    public static long mul(long a, long b, long c, long d) {
        return mul(a, mul(b, c, d));
    }

    public static long mul(long a, long b, long c, long d, long e) {
        return mul(a, mul(b, c, d, e));
    }

    public static long add(long a, long b) {
        a %= modulus;
        b %= modulus;
        return ensurePositive((a + b) % modulus);
    }

    public static long add(long a, long b, long c) {
        return add(a, add(b, c));
    }

    public static long add(long a, long b, long c, long d) {
        return add(a, add(b, c, d));
    }

    public static long add(long a, long b, long c, long d, long e) {
        return add(a, add(b, c, d, e));
    }

    public static long div(long a, long b) {
        return mul(a, inv(b));
    }

    public static long div(long a, long b, long c) {
        return mul(a, inv(b), inv(c));
    }

    public static long div(long a, long b, long c, long d) {
        return mul(a, inv(b), inv(c), inv(d));
    }

    public static long div(long a, long b, long c, long d, long e) {
        return mul(a, inv(b), inv(c), inv(d), inv(e));
    }

    public static long div(long a, long b, long[] inv) {
        return mul(a, inv[(int)b]);
    }

    public static long div(long a, long b, long c, long[] inv) {
        return mul(a, inv[(int)b], inv[(int)c]);
    }

    public static long div(long a, long b, long c, long d, long[] inv) {
        return mul(a, inv[(int)b], inv[(int)c], inv[(int)d]);
    }

    public static long div(long a, long b, long c, long d, long e, long[] inv) {
        return mul(a, inv[(int)b], inv[(int)c], inv[(int)d], inv[(int)e]);
    }

    //when modulus is a prime
    public static long inv(long a) {
        assert isPrime(a);
        a %= modulus;
        return pow(a, modulus - 2);
    }

    private static boolean isPrime(long a) {
        //approximate first, brute force second.
        if (!(BigInteger.valueOf(a).isProbablePrime(100)))
            return false;
        for (long d = 2; d * d <= a; ++d) {
            if (a % d == 0) return false;
        }
        return true;
    }

    //when modulus is not a prime
    public static long minv(long a, long MOD) {
        if (gcd(a, MOD) != 1) throw new RuntimeException("modInverse(" + a + "," + MOD + ") not exist.");
        long x = extgcd(a, MOD)[0];
        return (MOD + x % MOD) % MOD;
    }

    public static long[] extgcd(long a, long b) {
        if (b == 0) return new long[]{1, 0, a};
        long[] nxt = extgcd(b, a % b);
        long x, y, gcd;
        x = nxt[1];
        y = nxt[0] - a / b * nxt[1];
        gcd = nxt[2];

        return new long[]{x, y, gcd};
    }

    public static long pow(long a, long p) {
        long res = 1;
        long acc = a;
        while (p > 0) {
            if ((p & 1) != 0) res = (res * acc) % modulus;
            p >>= 1;
            acc = (acc * acc) % modulus;
        }
        return res;
    }

    public static long randPrime(int nbits) {
        return BigInteger.probablePrime(nbits, new Random()).longValue();
    }

    public static List<Integer> primes(int lessThan) {
        assert lessThan >= 2;
        List<Integer> res = new ArrayList<>();
        boolean[] isPrime = new boolean[lessThan];
        Arrays.fill(isPrime, true);
        // TODO: 17-1-18
        isPrime[2] = true;
        for (int i = 2; i < lessThan; ++i) {
            if (!isPrime[i]) continue;
            res.add(i);
            for (int j = i * 2; j < lessThan; j += i) isPrime[j] = false;
        }
        return res;
    }

    public static int[] randomInts(int W, int from, int to) {
        assert from < to;
        int[] res = new int[W];
        Random random = new Random();
        for (int i = 0; i < W; ++i) res[i] = from + random.nextInt(to - from);
        return res;
    }

    public static long[][] chooseTable(int n) {
        long[][] C = new long[n + 1][n + 1];
        for (int i = 0; i <= n; ++i) C[i][0] = 1;
        for (int i = 1; i <= n; ++i)
            for (int j = 1; j <= n; ++j) C[i][j] = (C[i - 1][j] + C[i - 1][j - 1]) % modulus;
        return C;
    }

    public static void main(String[] args) {
        long MOD = (long)1e9 + 7;
        IntegerUtils.modulus = MOD;
        for (int i = 1; i < MOD; ++i) {
            long a = inv(i), b = minv(i, MOD);
            if (a != b) System.err.println(i + ", " + a + ", " + b);
        }
    }
}

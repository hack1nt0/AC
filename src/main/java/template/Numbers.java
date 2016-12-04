package template;

import java.math.BigInteger;

/**
 * Created by dy on 16-10-8.
 */
public class Numbers {

    public static long MOD = Long.MAX_VALUE;

    // what about a < 0 or b < 0 ?
    public static long lcm(long a, long b) {
        long d = gcd(a, b);
        if (d == 0) return 0;
        BigInteger ans = BigInteger.valueOf(a).divide(BigInteger.valueOf(d)).multiply(BigInteger.valueOf(b));
        if (ans.compareTo(BigInteger.valueOf(Long.MAX_VALUE)) > 0) {
            System.err.println("lcm is out of bound!");
        }
        return ans.mod(BigInteger.valueOf(MOD)).longValue();
    }

    public static long gcd(long a, long b) {
        if (b == 0) return Math.abs(a);
        return gcd(b, a % b);
    }

    public static long div(long a, long b) {
        return a * inv(b) % MOD;
    }

    public static long mul(long a, long b) {
        a %= MOD;
        b %= MOD;
        return a * b % MOD;
    }

    public static long add(long a, long b) {
        a %= MOD;
        b %= MOD;
        return (a + b) % MOD;
    }

    public static long sub(long a, long b) {
        a %= MOD;
        b %= MOD;
        return (a - b + MOD) % MOD;
    }

    public static long inv(long a) {
        a %= MOD;
        return pow(a, MOD - 2);
    }

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
            if ((p & 1) != 0) res = (res * acc) % MOD;
            p >>= 1;
            acc = (acc * acc) % MOD;
        }
        return res;
    }

    public static void main(String[] args) {
        long MOD = (long)1e9 + 7;
        Numbers.MOD = MOD;
        for (int i = 1; i < MOD; ++i) {
            long a = inv(i), b = minv(i, MOD);
            if (a != b) System.err.println(i + ", " + a + ", " + b);
        }
    }
}

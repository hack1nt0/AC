package main;

import template.numbers.IntUtils;

import java.util.ArrayList;
import java.util.List;

public class RSABreaker {
    public int decrypt(int e, int n, int b) {
        long m = 0;
        int nn = n;
        List<Integer> factors = new ArrayList<>();
        for (int factor = 2; factor <= nn; ++factor) {
            if (nn % factor == 0) {
                while (nn % factor == 0)
                    nn /= factor;
                factors.add(factor);
            }
        }
        for (int set = 0; set < 1 << factors.size(); ++set) {
            int cd = 1;
            for (int i = 0; i < factors.size(); ++i)
                if ((set >> i & 1) == 1)
                    cd *= factors.get(i);
            m += (Integer.bitCount(set) % 2 == 1 ? -1 : +1) * (n - 1) / cd;
        }
        long d = 0;
        for (int x = 0; ; ++x) {
            long mx = m * x + 1;
            if (mx % e == 0) {
                d = mx / e;
                break;
            }
        }

        long ans = 1;
        long bp = b;
        for (int p = 0; (1L << p) <= d; ++p) {
            if ((d >> p & 1) == 1) {
                ans = ans * bp % n;
            }
            bp = bp * bp % n;
        }
        return (int)ans;
    }
}

package main;

import template.numbers.IntUtils;

public class TheRoundCityDiv1 {
    public long find(int r) {
        long ans = 0;
        for (int x = 1; x < r; ++x) {
            int y = (int)Math.sqrt((double)r * r - (double)x * x);
            if (x == 1) {
                ans += y;
            } else {
                long coPrimes = y;
                Integer[] factors = IntUtils.factors(x);
                for (int S = 1; S < 1 << factors.length; ++S) {
                    long prod = 1;
                    int bits = 0;
                    for (int i = 0; i < factors.length; ++i)
                        if ((S >> i & 1) != 0) {
                            prod *= factors[i];
                            bits++;
                        }
                    coPrimes += y / prod * (bits % 2 == 1 ? -1 : +1);
                }
                if (coPrimes < 0)
                    throw new RuntimeException();
                ans += coPrimes;
            }
        }
        ans = ans * 4 + 4;
        return ans;
    }
}

package main;

import template.numbers.IntUtils;

public class FoxAndCake2 {
    public String isPossible(int c, int s) {
        String OK = "Possible";
        String NO = "Impossible";
        if (c == 1 || s == 1)
            return NO;
        if (IntUtils.gcd(c, s) != 1)
            return OK;
        if (c % 2 == s % 2 && Math.min(c, s) > 3)
            return OK;
        if (c % 2 != s % 2) {
            long old = c % 2 == 1 ? c : s;
            long even = c + s - old;
            if (old == 3 && even == 6 || old > 3 && even > 6)
                return OK;
        }
        return NO;
    }
}

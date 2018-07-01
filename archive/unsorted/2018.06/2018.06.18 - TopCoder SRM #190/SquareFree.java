package main;

import template.numbers.IntUtils;

import java.util.List;

//?
public class SquareFree {
    public int getNumber(int n) {
        List<Integer> primes = IntUtils.primes((int) 1e5);
        while (Math.pow(primes.get(primes.size() - 1), 2) > 1e7)
            primes.remove(primes.size() - 1);
        long l = 0, r = 1L * Integer.MAX_VALUE;
        while (l + 1 < r) {
            long mid = l + (r - l) / 2;
            long count = mid - dfs(0, 0, 1L, mid, primes);
//            System.out.println(mid + " " + count);
            long cmp = count - n;
            if (cmp >= 0)
                r = mid;
            else
                l = mid;
        }
        return (int) r;
    }

    private long dfs(int cur, int count, long acc, long mid, List<Integer> primes) {
//        System.out.println(cur + " " + count + " " + acc);
        if (cur == primes.size())
            return 0L;
        if (acc > mid)
            return 0L;
        long res = 0L;
        long nacc = acc * primes.get(cur) * primes.get(cur);
        res += mid / nacc * ((count + 1) % 2 == 1 ? +1 : -1);
        res += dfs(cur + 1, count + 1, nacc, mid, primes);
        res += dfs(cur + 1, count, acc, mid, primes);
        return res;
    }
}

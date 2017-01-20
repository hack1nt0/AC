package template.collection.sets;

import java.util.*;

/**
 * Created by dy on 16-12-3.
 *
 *
 *Set union A | B
 *Set intersection A & B
 *Set subtraction A & ~B
 *Set negation ALL_BITS ^ A
 *Set bit A |= 1 << bit
 *Clear bit A &= ~(1 << bit)
 *Test bit (A & 1 << bit) != 0
 *
 *
 *
 *
 *
 *
 *
 */
public class BitUtils {

    private static final int nbits = 64;
    private static Map<Long, Integer> cacheLog2;

    //Long.MIN <= s <= Long.MAX
    public static List<Integer> elements(long s) {
        if (cacheLog2 == null) {
            cacheLog2 = new HashMap<Long, Integer>();
            for (int i = 0; i < nbits; ++i) cacheLog2.put(1L << i, i);
        }
        List<Integer> ret = new ArrayList<Integer>();
        while (true) {
            if (s == 0) break;
            long lowestBit = s & ~(s - 1);
            ret.add(cacheLog2.get(lowestBit));
            s &= ~lowestBit;
        }
        return ret;
    }

    //Long.MIN <= s <= Long.MAX
    public static List<Long> subset(long s) {
        long sub = s;
        List<Long> ret = new ArrayList<Long>();
        do {
            ret.add(sub);
            //System.err.println(Long.toBinaryString(sub));
            sub = (sub - 1) & s;
        } while (sub != s);
        return ret;
    }

    public static List<Integer> subset(int s) {
        int sub = s;
        List<Integer> ret = new ArrayList<Integer>();
        do {
            ret.add(sub);
            //System.err.println(Long.toBinaryString(sub));
            sub = (sub - 1) & s;
        } while (sub != s);
        return ret;
    }

    //0 <= k <= n <= 64
    public static List<Long> kSubset(long n, int k) {
        List<Long> ret = new ArrayList<Long>();
        long comb = (1L << k) - 1;
        while (comb < (1L << n)) {
            ret.add(comb);
            System.err.println(Long.toBinaryString(comb));
            long x = comb & -comb, y = comb + x;
            comb = ((comb & ~y) / x >> 1) | y;
        }
        return ret;
    }

    public static void main(String[] args) {
        long s = Long.MIN_VALUE;
        subset(s);
        System.out.println(Long.toBinaryString(Long.MIN_VALUE));
    }
}

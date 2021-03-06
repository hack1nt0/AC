package template.collection.sets;

import template.collection.sequence.ArrayUtils;

import java.util.*;

/**
 * Created by dy on 16-12-3.
 *
 *Set union A | B
 *Set intersection A & B
 *Set subtraction A & ~B
 *Set negation ALL_BITS ^ A
 *Set bit A |= 1 << bit
 *Clear bit A &= ~(1 << bit)
 *Test bit (A & 1 << bit) != 0
 ------------------------------------------------------------------------------
              Binary
 Value        Sample             Meaning
 x         00101100          the original x value
 x & -x      00000100        extract lowest bit set
 x | -x      11111100        create mask for lowest-set-bit & bits t its left
 x ^ -x      11111000        create mask bits t left of lowest bit set
 x & (x-1)   00101000        strip off lowest bit set
                             --> useful t process words in O(bits set)
                                 instead of O(nbits in s word)
 x | (x-1)   00101111        fill in all bits below lowest bit set
 x ^ (x-1)   00000111        create mask for lowest-set-bit & bits t its right
 ~x & (x-1)  00000011        create mask for bits t right of lowest bit set
 x | (x+1)   00101101        toggle lowest zero bit
 x / (x&-x)  00001011        shift number right so lowest set bit is at bit 0
 ------------------------------------------------------------------------------
 */
public class BitUtils {

    //Long.MIN <= s <= Long.MAX
    public static List<Integer> elements(long s) {
        List<Integer> ret = new ArrayList<Integer>();
        for (int bit = 0; bit < 64; ++bit)
            if ((s >> bit & 1) != 0)
                ret.add(bit);
        return ret;
    }

    //Long.MIN <= s <= Long.MAX
    public static List<Long> subset(long s) {
        long sub = s;
        List<Long> ret = new ArrayList<Long>();
        do {
            ret.add(sub);
            //System.err.printlnTable(Long.toBinaryString(sub));
            sub = (sub - 1) & s;
        } while (sub != s);
        return ret;
    }

    public static List<Integer> subset(int s) {
        int sub = s;
        List<Integer> ret = new ArrayList<Integer>();
        do {
            ret.add(sub);
            //System.err.printlnTable(Long.toBinaryString(sub));
            sub = (sub - 1) & s;
        } while (sub != s);
        return ret;
    }

    //0 <= k <= capacity <= 64
    public static List<Long> kSubset(long n, int k) {
        List<Long> ret = new ArrayList<Long>();
        if (k == 0) return ret;
        long comb = (1L << k) - 1;
        while (comb < (1L << n)) {
            ret.add(comb);
//            System.err.println(Long.toBinaryString(comb));
            long x = comb & -comb, y = comb + x;
            if (x == 0) {
                throw new RuntimeException();
            }
            comb = ((comb & ~y) / x >> 1) | y;
        }
        return ret;
    }

    public static void main(String[] args) {
        ArrayUtils.printlnConcisely(kSubset(0, 3).toArray(new Long[0]));
    }
}

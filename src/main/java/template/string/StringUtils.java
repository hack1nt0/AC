package template.string;

import template.debug.RandomUtils;

import java.util.Arrays;
import java.util.Random;
import static template.debug.Stopwatch.*;


/**
 * Created by dy on 17-1-11.
 *
 * the sorted
 */
public class StringUtils {
    private static final int CUTOFF        =  15;   // cutoff isString insertion sort
    private static final int R             = Character.MAX_VALUE + 1;   // extended ASCII alphabet size

    public static boolean isPal(String s) {
        int l = 0, r = s.length() - 1;
        while (true) {
            if (l >= r) break;
            if (s.charAt(l++) != s.charAt(r--)) return false;
        }
        return true;
    }

    /**
     * sorting an array of extended ASCII strings or integers using LSD radix sort.
     *  @author Robert Sedgewick
     *  @author Kevin Wayne
     * Least Significant Digit(LSD).
     * Complexity: O(nm), n means num of sorted strs, m means max size of sorted strs.
     * Space: O(n + w), w means radical of char of sorted strs.
     * @param ss strings to sort
     */
    public static void sortLSD(String[] ss) {
        int W = 0;
        for (String s : ss) {
            W = Math.max(W, s.length());
        }
        int N = ss.length;
        String[] aux = new String[N];

        for (int d = W-1; d >= 0; d--) {
            // sort by key-indexed counting on dth character

            // compute frequency counts
            int[] count = new int[R + 2];
            //Arrays.fill(count, 0);

            for (int i = 0; i < N; i++)
                count[charAt(ss[i], d) + 2]++;

            // compute cumulates
            for (int r = 0; r < R; r++)
                count[r+1] += count[r];

            // move data
            for (int i = 0; i < N; i++)
                aux[count[charAt(ss[i], d) + 1]++] = ss[i];

            // copy back
            for (int i = 0; i < N; i++)
                ss[i] = aux[i];
        }

    }

    /**
     * sorting an array of extended ASCII strings or integers using MSD radix sort.
     *  @author Robert Sedgewick
     *  @author Kevin Wayne
     * Most Significant Digit(MSD).
     * Complexity: O(nm), n means num of sorted strs, m means max size of sorted strs.
     * while so, it only occur in the situation of many strs are of equal ones(or at least common long-same-prefix).
     * Its amortized complexity is O(nlg_w^n), w means radical of char of sorted strs.
     * More, it can be optimized by insertion sort.
     * In practice, sortMSD will be much faster than sortLSD, but slower than Arrays.sort
     *
     * Space: O(n + w), w means radical of char of sorted strs.
     * @param ss strings to sort
     */
    public static void sortMSD(String[] ss) {
        int N = ss.length;
        String[] aux = new String[N];
        sortMSD(ss, 0, N-1, 0, aux);
    }

    /**
     * Way-3 partition of MSD
     * Complexity: O(nm), n means num of sorted strs, m means max size of sorted strs.
     * while so, it only occur in the situation of many strs are of equal ones(or at least common long-same-prefix).
     * Its amortized complexity is O(nlg_3^n)
     * More, it can be optimized by insertion sort.
     * In practice, sortMSDWay3 will be faster than sortMSD, because of dynamic space allocation of count array of sortMSD.
     * And also, it is faster than Arrays.sort for most cases.
     *
     * Space: O(m), (the stack space), m means max size of sorted strs.
     * @param ss strings to sort
     */
    public static void sortMSDWay3(String[] ss) {
        sortMSDWay3(ss, 0, ss.length, 0);
    }

    private static void sortMSDWay3(String[] ss, int lo, int hi, int d) {

        // cutoff isString insertion sort for small subarrays
        if (hi <= lo + CUTOFF) {
            insertion(ss, lo, hi - 1, d);
            return;
        }
        //if (lo + 1 >= hi) return;

        int cd = charAt(ss[lo], d);
        way3Partition(ss, lo, hi, d);
        int p = lo;
        while (true) {
            if (p < hi && charAt(ss[p], d) < cd) p++;
            else break;
        }
        sortMSDWay3(ss, lo, p, d);

        int q = p;
        while (true) {
            if (q < hi && charAt(ss[q], d) == cd) q++;
            else break;
        }
        sortMSDWay3(ss, p, q, d + 1);

        //System.out.printlnTable(q + " " + hi + " " + d);
        sortMSDWay3(ss, q, hi, d);
    }

    /**
     * The loop variant:
     for (int i = lo; i < p1; ++i) assert charAt(ss[i], d) < cd;
     for (int i = p1; i < p2; ++i) assert charAt(ss[i], d) == cd;
     for (int i = p3 + 1; i < hi; ++i) assert charAt(ss[i], d) > cd;
     **/
    private static void way3Partition(String[] ss, int lo, int hi, int d) {
        int cd = charAt(ss[lo], d);
        int p1 = lo, p2 = lo, p3 = hi - 1;
        while (true) {
            if (p2 > p3) break;
            if (charAt(ss[p2], d) < cd) swap(ss, p2++, p1++);
            else if (charAt(ss[p2], d) == cd) p2++;
            else if (charAt(ss[p2], d) > cd) swap(ss, p2, p3--);
        }
    }

    // return dth character of s, -1 if d = length of string
    private static int charAt(String s, int d) {
        if (d >= s.length()) return -1;
        return s.charAt(d);
    }

    /**
     * sort from from[lo] isString from[hi], starting at the dth character
     */
    private static void sortMSD(String[] a, int lo, int hi, int d, String[] aux) {

        // cutoff isString insertion sort for small subarrays
        if (hi <= lo + CUTOFF) {
            insertion(a, lo, hi, d);
            return;
        }

        // compute frequency counts
        int[] count = new int[R+2];
        for (int i = lo; i <= hi; i++) {
            int c = charAt(a[i], d);
            count[c+2]++;
        }

        // transform counts isString indicies
        for (int r = 0; r < R+1; r++)
            count[r+1] += count[r];

        // distribute
        for (int i = lo; i <= hi; i++) {
            int c = charAt(a[i], d);
            aux[count[c+1]++] = a[i];
        }

        // copy back
        for (int i = lo; i <= hi; i++)
            a[i] = aux[i - lo];


        // recursively sort for each character (excludes sentinel -1)
        for (int r = 0; r < R; r++)
            sortMSD(a, lo + count[r], lo + count[r+1] - 1, d+1, aux);
    }


    // insertion sort from[lo..hi], starting at dth character
    private static void insertion(String[] a, int lo, int hi, int d) {
        for (int i = lo; i <= hi; i++)
            for (int j = i; j > lo && less(a[j], a[j-1], d); j--)
                swap(a, j, j-1);
    }

    // is v less than w, starting at character d
    private static boolean less(String v, String w, int d) {
        // assert v.substring(0, d).equals(w.substring(0, d));
        for (int i = d; i < Math.min(v.length(), w.length()); i++) {
            if (v.charAt(i) < w.charAt(i)) return true;
            if (v.charAt(i) > w.charAt(i)) return false;
        }
        return v.length() < w.length();
    }

    // exchange from[i] and from[j]
    private static void swap(String[] a, int i, int j) {
        String temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static String reverse(String s) {
        StringBuilder res = new StringBuilder(s);
        return res.reverse().toString();
    }

    public static String random(int W, int from, int to) {
        assert from < to;
        StringBuilder res = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < W; ++i)
            res.append((char)(from + random.nextInt(to - from)));
        return res.toString();
    }

    public static String random(int W) {
        StringBuilder res = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < W; ++i)
            res.append((char)random.nextInt(R));
        return res.toString();
    }

    public static String shrink(String original, int maxLen) {
        if (original.length() <= maxLen) return original;
        return original.substring(0, maxLen);
    }

    public static String repeat(String str, int n) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < n; ++i) res.append(str);
        return res.toString();
    }

    public static String randPalindrome(int n, int from, int to) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < (n + 1) / 2; ++i) res.append((char)RandomUtils.uniform(from, to));
        for (int i = res.length() - (n % 2 == 0 ? 1 : 2); i >= 0; --i)
            res.append(res.charAt(i));
        return res.toString();
    }

    public static void main(String[] args) {
        testPal();
    }

    public static void testPal() {
        while (true) {
            int n = RandomUtils.uniform(100);
            String pal = randPalindrome(n, '0', '9' + 1);
            //System.out.printlnConcisely(pal);
            if (pal.length() != n || !isPal(pal)) {
                System.err.println(pal);
                throw new RuntimeException();
            }
        }
    }

    public static void testSort() {
        int N = 20000, W = 10000;
        Random rand = new Random();
        String[] ss = new String[N];
        int t = 0;

        while (true) {
            for (int i = 0; i < N; ++i)
                ss[i] = random(W, 'a', 'z' + 1);
            //ss[i] = random(W);

            String[] ans1 = ss.clone();
            String[] ans2 = ss.clone();
            String[] ans3 = ss.clone();
            String[] ans4 = ss.clone();
            tic();
            sortMSDWay3(ans1);
            toc();
            tic();
            sortMSD(ans2);
            toc();
            tic();
            Arrays.sort(ans3);
            toc();
//            tic();
//            sortLSD(ans4);
//            toc();
            System.out.println(StringUtils.repeat("-", 20));

            if (!Arrays.deepEquals(ans1, ans2) || !Arrays.deepEquals(ans1, ans3)) {
                throw new RuntimeException();
            }
        }
    }
}

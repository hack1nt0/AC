package template.collection;

import function_programming.IntIntMapper;
import template.numbers.IntUtils;
import template.string.StringUtils;

import java.util.*;

import static template.collection.sequence.ArrayUtils.sorted;

/**
 * @author dy[jealousing@gmail.com] on 17-3-20.
 */
public class Sorter {

    /* Sort */
    public <T extends Comparable<T>> List<T> sort(Iterable<T> iterable, Comparator<T> comparator) {
        List<T> list = CollectionUtils.asList(iterable);
        Collections.sort(list, comparator);
        return list;
    };

    public <T extends Comparable<T>> void sort(CharSequence[] strings) {
        StringUtils.sortMSDWay3(strings);
    };

    public void sort(List<CharSequence> stringList) {
        sortMSDWay3(stringList, 0, stringList.size(), 0);
    };

    private static void sortMSDWay3(List<CharSequence> stringList, int lo, int hi, int d) {
        // cutoff isString insertion sort for small subarrays
        if (hi <= lo + 15) {
            insertion(stringList, lo, hi - 1, d);
            return;
        }
        int cd = charAt(stringList.get(lo), d);
        way3Partition(stringList, lo, hi, d);
        int p = lo;
        while (true) {
            if (p < hi && charAt(stringList.get(p), d) < cd) p++;
            else break;
        }
        sortMSDWay3(stringList, lo, p, d);
        int q = p;
        while (true) {
            if (q < hi && charAt(stringList.get(q), d) == cd) q++;
            else break;
        }
        sortMSDWay3(stringList, p, q, d + 1);
        sortMSDWay3(stringList, q, hi, d);
    }

    /**
     * The loop variant:
     for (int i = lo; i < p1; ++i) assert charAt(ss[i], d) < cd;
     for (int i = p1; i < p2; ++i) assert charAt(ss[i], d) == cd;
     for (int i = p3 + 1; i < hi; ++i) assert charAt(ss[i], d) > cd;
     **/
    private static void way3Partition(List<CharSequence> stringList, int lo, int hi, int d) {
        int cd = charAt(stringList.get(lo), d);
        int p1 = lo, p2 = lo, p3 = hi - 1;
        while (true) {
            if (p2 > p3) break;
            if (charAt(stringList.get(p2), d) < cd) swap(stringList, p2++, p1++);
            else if (charAt(stringList.get(p2), d) == cd) p2++;
            else if (charAt(stringList.get(p2), d) > cd) swap(stringList, p2, p3--);
        }
    }

    // return dth character of s, -1 if d = length of string
    private static int charAt(CharSequence s, int d) {
        if (d >= s.length()) return -1;
        return s.charAt(d);
    }

    // insertion sort from[lo..hi], starting at dth character
    private static void insertion(List<CharSequence> stringList, int lo, int hi, int d) {
        for (int i = lo; i <= hi; i++)
            for (int j = i; j > lo && less(stringList.get(j), stringList.get(j-1), d); j--)
                swap(stringList, j, j-1);
    }

    // is v less than w, starting at character d
    private static boolean less(CharSequence v, CharSequence w, int d) {
        // assert v.substring(0, d).equals(w.substring(0, d));
        for (int i = d; i < Math.min(v.length(), w.length()); i++) {
            if (v.charAt(i) < w.charAt(i)) return true;
            if (v.charAt(i) > w.charAt(i)) return false;
        }
        return v.length() < w.length();
    }

    // exchange from[i] and from[j]
    private static void swap(List<CharSequence> stringList, int i, int j) {
        CharSequence swap = stringList.get(i);
        stringList.set(i, stringList.get(j));
        stringList.set(j, swap);
    }

    /**
     * @complexity O(nlogn) and stable
     * In practice, it is slower than Arrays.sort by 6~7x.
     * @param arr
     */
    public static void mergeSort(int[] arr) {
        int N = arr.length;
        if (N < 2) return;
        int[] tmp = new int[N];
        for (int len = 1; len < N; len *= 2) {
            for (int from = 0; from + len < N; from += 2 * len) {
                //merge(arr, from, from + len, tmp);
                int p = from + len;
                int q = p + len;
                if (q > N) q = N;

                if (q - from < 7) {
                    insertionSort(arr, from, q);
                    continue;
                }

                int i = from;
                int j = p;
                int k = from;
                while (true) {
                    if (j == q && i == p) break;
                    // <=(not <) here to maintain original order of elements
                    if (j == q || i < p && arr[i] <= arr[j]) {
                        tmp[k++] = arr[i++];
                    } else {
                        tmp[k++] = arr[j++];
                    }
                }
                //assert i == p && j == q && k == q;
                if (arr[p] < arr[p - 1]) {
                    //faster than loop
                    System.arraycopy(tmp, from, arr, from, q - from);
                }
//                if (!sorted(arr, from, q)) {
//                    throw new RuntimeException();
//                }
            }
        }
    }


    /**
     * @complexity O(n^2) and stable
     * @param arr
     * @param from
     * @param to
     */
    public static void insertionSort(int[] arr, int from, int to) {
        for (int i = from + 1; i < to; ++i) {
            for (int j = from; j < i; ++j) {
                if (arr[i] < arr[j]) {
                    int arri = arr[i];
                    for (int k = i; k > j; --k) arr[k] = arr[k - 1];
                    arr[j] = arri;
                    break;
                }
            }
//            if (!sorted(arr, from, i + 1)) {
//                throw new RuntimeException();
//            }
        }
    }

    public static void countingSort(int[] array, int W) {
        countingSort(array, new IntIntMapper() {
            @Override
            public int apply(int value) {
                return value;
            }
        }, W, null);
    }

    public static <T> void countingSort(int[] index, IntIntMapper mapper, int W) {
        countingSort(index, mapper, W, null);
    }

    public static void countingSort(int[] index, IntIntMapper mapper, int W, int[] aux) {
        if (index == null || index.length == 0) throw new IllegalArgumentException();
        if (aux == null) aux = new int[index.length];
        if (aux.length != index.length) throw new IllegalArgumentException();
        int min = Integer.MAX_VALUE;
        for (int i : index) min = Math.min(min, mapper.apply(i));
        int offset = 0;
        if (min < 0) offset = -min;
        int[] count = new int[W + 1];
        for (int i : index) count[mapper.apply(i) + offset + 1]++;
        for (int i = 0; i < W; ++i) count[i + 1] += count[i];
        for (int i = 0; i < aux.length; ++i) aux[count[mapper.apply(index[i]) + offset]++] = index[i];
        for (int i = 0; i < aux.length; ++i) index[i] = aux[i];
    }
    /* End of Sort */

    public static void main(String[] args) {
        //testSort();
        //testReverseOrderPairs();
        Integer a = 32432;
        Integer b = 32432;
        int x = 1;
    }

    private static void testSort() {
        while (true) {
//            int[] arr = IntUtils.randomInts(10000000, -100000000, 100000000);
//            int[] arr1 = arr.clone();
//            int[] arr2 = arr.clone();
//            Stopwatch.tic();
//            mergeSort(arr1);
//            Stopwatch.toc();
//            Stopwatch.tic();
//            Arrays.sort(arr2);
//            Stopwatch.toc();
//            if (!Arrays.equals(arr1, arr2)) {
//                //ArrayUtils.printlnTableH(arr1, arr2);
//                System.out.println("error");
//            }
            int W = 100000;
            int[] arr = IntUtils.randomInts(100000, 0, W);
            countingSort(arr, W);
            if (!sorted(arr)) {
                throw new RuntimeException();
            }
        }
    }
}

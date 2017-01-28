package template.collection.sequence;

import template.debug.RandomUtils;
import template.debug.Stopwatch;
import template.numbers.IntegerUtils;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * Created by dy on 16-12-22.
 */
public class ArrayUtils {
    public static void main(String[] args) {
        //testSort();
        testReverseOrderPairs();
    }

    private static void testSort() {
        while (true) {
            int[] arr = IntegerUtils.randomInts(10000000, -100000000, 100000000);
            int[] arr1 = arr.clone();
            int[] arr2 = arr.clone();
            Stopwatch.tic();
            mergeSort(arr1);
            Stopwatch.toc();
            Stopwatch.tic();
            Arrays.sort(arr2);
            Stopwatch.toc();
            if (!Arrays.equals(arr1, arr2)) {
                //ArrayUtils.printlnTableH(arr1, arr2);
                System.out.println("error");
            }
        }
    }

    public static void testReverseOrderPairs() {
        while (true) {
            int[] arr = IntegerUtils.randomInts(RandomUtils.uniform(100), 0, 10);
            int ans = 0;
            for (int i = 0; i < arr.length; ++i)
                for (int j = i + 1; j < arr.length; ++j) if (arr[i] > arr[j]) ans++;
            if (ans != reverseOrderPairs(arr)) {
                throw new RuntimeException();
            }
        }
    }


    public static int reverseOrderPairs(int[] arr) {
        int[] buf = new int[arr.length];
        return reverseOrderPairs(arr, 0, arr.length, buf);
    }

    public static int reverseOrderPairs(int[] arr, int from, int to, int[] buf) {
        if (from + 1 >= to) return 0;
        int mid = from + (to - from) / 2;
        int res = reverseOrderPairs(arr, from, mid, buf) + reverseOrderPairs(arr, mid, to, buf);
        int i = from, j = mid, k = from;
        while (true) {
            if (i == mid && j == to) break;
            if (j == to || i < mid && arr[i] <= arr[j]) buf[k++] = arr[i++];
            else {
                res += mid - i;
                buf[k++] = arr[j++];
            }
        }
        System.arraycopy(buf, from, arr, from, to - from);
        //if (!ArrayUtils.sorted(arr, from, to)) throw new RuntimeException();
        return res;
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

    public static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void swap(Object[] arr, int i, int j) {
        Object t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static boolean sorted(int[] arr) {
        return sorted(arr, 0, arr.length);
    }

    public static boolean sorted(int[] arr, int from, int to) {
        assert from < to;
        int prev = Integer.MIN_VALUE;
        for (int i = from; i < to; ++i)
            if (i > from && arr[i - 1] > arr[i]) return false;
        return true;
    }

    public static long max(long[] a) {
        if (a.length <= 0) throw new RuntimeException();
        long res = a[0];
        for (int i = 0; i < a.length; ++i) if (res < a[i]) res = a[i];
        return res;
    }

    public static int max(int[] a) {
        if (a.length <= 0) throw new RuntimeException();
        int res = a[0];
        for (int i = 0; i < a.length; ++i) if (res < a[i]) res = a[i];
        return res;
    }
    public static int min(int[] a) {
        if (a.length <= 0) throw new RuntimeException();
        int res = a[0];
        for (int i = 0; i < a.length; ++i) if (res > a[i]) res = a[i];
        return res;
    }
    public static int sum(int[] a) {
        int res = 0;
        for (int i = 0; i < a.length; ++i) res += a[i];
        return res;
    }

    public static boolean isIndex(int[] arr) {
        throw new UnsupportedOperationException();
    }

    public static int[] index(int n) {
        int[] res = new int[n];
        for (int i = 0; i < n; ++i) res[i] = i;
        return res;
    }

    public int count(int[] arr, int a) {
        int res = 0;
        for (int i = 0; i < arr.length; ++i)
            if (arr[i] == a) res++;
        return res;
    }

    public static Object[] inbox(Object arr) {
        if (!(arr instanceof Object[] || arr.getClass().isArray())) {
            return new Object[]{arr};
        }
        if (arr instanceof Object[])
            return (Object[]) arr;

        if (arr instanceof int[]) {
            int[] tmp = (int[]) arr;
            int N = tmp.length;
            Integer[] res = new Integer[N];
            for (int i = 0; i < N; ++i) res[i] = tmp[i];
            //System.arraycopy(tmp, 0, res, 0, N);
            return res;
        }
        if (arr instanceof long[]) {
            long[] tmp = (long[]) arr;
            int N = tmp.length;
            Long[] res = new Long[N];
            for (int i = 0; i < N; ++i) res[i] = tmp[i];
            return res;
        }
        if (arr instanceof char[]) {
            char[] tmp = (char[]) arr;
            int N = tmp.length;
            Character[] res = new Character[N];
            for (int i = 0; i < N; ++i) res[i] = tmp[i];
            return res;
        }
        if (arr instanceof float[]) {
            float[] tmp = (float[]) arr;
            int N = tmp.length;
            Float[] res = new Float[N];
            for (int i = 0; i < N; ++i) res[i] = tmp[i];
            return res;
        }
        if (arr instanceof double[]) {
            double[] tmp = (double[]) arr;
            int N = tmp.length;
            Double[] res = new Double[N];
            for (int i = 0; i < N; ++i) res[i] = tmp[i];
            return res;
        }
        if (arr instanceof byte[]) {
            byte[] tmp = (byte[]) arr;
            int N = tmp.length;
            Byte[] res = new Byte[N];
            System.arraycopy(tmp, 0, res, 0, N);
            return res;
        }
        if (arr instanceof boolean[]) {
            boolean[] tmp = (boolean[]) arr;
            int N = tmp.length;
            Boolean[] res = new Boolean[N];
            System.arraycopy(tmp, 0, res, 0, N);
            return res;
        }

        return null;
    }

    public static Object outbox(Object arr) {
        boolean isObjectArray = arr instanceof Object[];
        boolean isPrimitiveArray = !isObjectArray && arr.getClass().isArray();
        if (isPrimitiveArray) return arr;

        int N = ((Object[])arr).length;
        if (arr instanceof Integer[]) {
            Integer[] tmp = (Integer[]) arr;
            int[] res = new int[N];
            for (int i = 0; i < N; ++i) res[i] = tmp[i];
            //System.arraycopy(tmp, 0, res, 0, N);
            return res;
        }
        if (arr instanceof Long[]) {
            Long[] tmp = (Long[])arr;
            long[] res = new long[N];
            for (int i = 0; i < N; ++i) res[i] = tmp[i];
            return res;
        }
        if (arr instanceof Character[]) {
            Character[] tmp = (Character[]) arr;
            char[] res = new char[N];
            for (int i = 0; i < N; ++i) res[i] = tmp[i];
            return res;
        }
        if (arr instanceof Float[]) {
            Float[] tmp = (Float[]) arr;
            float[] res = new float[N];
            for (int i = 0; i < N; ++i) res[i] = tmp[i];
            return res;
        }
        if (arr instanceof Double[]) {
            Double[] tmp = (Double[]) arr;
            double[] res = new double[N];
            for (int i = 0; i < N; ++i) res[i] = tmp[i];
            return res;
        }
        if (arr instanceof Byte[]) {
            Byte[] tmp = (Byte[]) arr;
            byte[] res = new byte[N];
            System.arraycopy(tmp, 0, res, 0, N);
            return res;
        }
        if (arr instanceof Boolean[]) {
            Boolean[] tmp = (Boolean[]) arr;
            boolean[] res = new boolean[N];
            System.arraycopy(tmp, 0, res, 0, N);
            return res;
        }

        return null;
    }

    public static void fill(int[][] arr, int v) {
        for (int i = 0; i < arr.length; ++i)
            for (int j = 0; j < arr[i].length; ++j) arr[i][j] = v;
    }

    public static int[] flatten(int[][] arr) {
        int N = 0;
        for (int i = 0; i < arr.length; ++i) N += arr[i].length;
        int[] res = new int[N];
        int idx = 0;
        for (int i = 0; i < arr.length; ++i)
            for (int j = 0; j < arr[i].length; ++j) res[idx++] = arr[i][j];
        return res;
    }


    public static String repeat(String str, int nRepeated) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < nRepeated; ++i) res.append(str);
        return res.toString();
    }

    public static void printlnTableV(Object... objects) {
        printlnTable(null, true, objects);
    }

    public static void printlnTableVWithHeads(String[] heads, Object... objects) {
        printlnTable(heads, true, objects);
    }

    public static void printlnTableH(Object... objects) {
        printlnTable(null, false, objects);
    }

    public static void printlnTable(String[] heads, boolean vertical, Object... objects) {
        final PrintStream out = System.out;
        int W = 10;
        String formateStr = "%10s";

        int N = objects.length;
        if (N == 0) return;

        //printlnTable("-", (N + 1) * W + N);

        Object[][] matrix = new Object[N][];
        for (int i = 0; i < N; ++i) matrix[i] = inbox(objects[i]);

        if (vertical) {
            if (heads != null) {
                out.printf(formateStr, "L\\H");
                for (int i = 0; i < N; ++i)
                    out.printf(formateStr, heads[i]);
                out.println();

                //printlnTable("-", (N + 1) * W + N);
            }


            for (int i = 0; i < matrix[0].length; ++i) {
                out.printf(formateStr, i);
                for (int j = 0; j < N; ++j) {
                    out.printf(formateStr, String.valueOf(matrix[j][i]));
                }
                out.println();
            }
        } else {
            out.printf(formateStr, "R\\C");
            for (int i = 0; i < matrix[0].length; ++i)
                out.printf(formateStr, i);
            out.println();

            //printlnTable("-", (N + 1) * W + N);

            for (int i = 0; i < N; ++i) {
                out.printf(formateStr, i);
                for (int j = 0; j < matrix[i].length; ++j) {
                    out.printf(formateStr, String.valueOf(matrix[i][j]));
                }
                out.println();
            }
        }
        //printlnTable("-", (N + 1) * W + N);
        out.println();
    }

    public static void println(Object arr1, PrintWriter out) {
        if (out == null) {
            out = new PrintWriter(System.out);
        }
        Object[] arr = inbox(arr1);
        for (int i = 0; i < arr.length; ++i) {
            if (i > 0) out.print(' ');
            out.print(arr[i]);
        }
        out.println();
    }

    public static void reverse(int[] arr) {
        reverse(arr, 0, arr.length);
    }

    public static void reverse(int[] arr, int from, int to) {
        if (from < 0 || to > arr.length || from >= to) throw new RuntimeException();
        int l = from, r = to - 1;
        while (true) {
            if (l >= r) break;
            int t = arr[l];
            arr[l] = arr[r];
            arr[r] = t;
            l++; r--;
        }
    }

    public static int upperBound(int[] arr, int value) {
        return upperBound(arr, 0, arr.length, value);
    }

    public static int upperBound(int[] arr, int from, int to, int value) {
        if (from < 0 || to > arr.length || from >= to) throw new RuntimeException();
        int l = from, r = to;
        while (true) {
            if (l >= r) break;
            int mid = l + (r - l) / 2;
            if (arr[mid] <= value) l = mid + 1;
            else r = mid;
        }
        if (l != r) throw new RuntimeException();
        return l;
    }

    public static int lowerBound(int[] arr, int value) {
        return lowerBound(arr, 0, arr.length, value);
    }

    public static int lowerBound(int[] arr, int from, int to, int value) {
        if (from < 0 || to > arr.length || from >= to) throw new RuntimeException();
        int l = from, r = to;
        while (true) {
            if (l >= r) break;
            int mid = l + (r - l) / 2;
            if (arr[mid] >= value) r = mid;
            else l = mid + 1;
        }
        if (l != r) throw new RuntimeException();
        return l;
    }

    public static void println(Object arr1) {
        println(arr1, null);
    }
}

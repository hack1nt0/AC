package template.collection.sequence;

import template.debug.RandomUtils;
import template.debug.StopWatch;
import template.numbers.IntegerUtils;
import template.string.StringUtils;

import java.io.PrintStream;
import java.util.Arrays;

/**
 * Created by dy on 16-12-22.
 */
public class ArrayUtils {
    public static void main(String[] args) {
        while (true) {
            testSort();
            System.out.println(StringUtils.repeat("=", 12));
        }
    }

    private static void testSort() {
        int[] arr = IntegerUtils.randomInts(10000000, -100000000, 100000000);
        int[] arr1 = arr.clone();
        int[] arr2 = arr.clone();
        StopWatch.tic();
        mergeSort(arr1);
        StopWatch.toc();
        StopWatch.tic();
        Arrays.sort(arr2);
        StopWatch.toc();
        if (!Arrays.equals(arr1, arr2)) {
            //ArrayUtils.printlnH(arr1, arr2);
            System.out.println("error");
        }
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

    public static void printlnV(Object... objects) {
        println(null, true, objects);
    }

    public static void printlnVWithHeads(String[] heads, Object... objects) {
        println(heads, true, objects);
    }

    public static void printlnH(Object... objects) {
        println(null, false, objects);
    }

    public static void println(String[] heads, boolean vertical, Object... objects) {
        final PrintStream out = System.out;
        int W = 10;
        String formateStr = "%10s";

        int N = objects.length;
        if (N == 0) return;

        //println("-", (N + 1) * W + N);

        Object[][] matrix = new Object[N][];
        for (int i = 0; i < N; ++i) matrix[i] = inbox(objects[i]);

        if (vertical) {
            if (heads != null) {
                out.printf(formateStr, "L\\H");
                for (int i = 0; i < N; ++i)
                    out.printf(formateStr, heads[i]);
                out.println();

                //println("-", (N + 1) * W + N);
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

            //println("-", (N + 1) * W + N);

            for (int i = 0; i < N; ++i) {
                out.printf(formateStr, i);
                for (int j = 0; j < matrix[i].length; ++j) {
                    out.printf(formateStr, String.valueOf(matrix[i][j]));
                }
                out.println();
            }
        }
        //println("-", (N + 1) * W + N);
        out.println();
    }
}

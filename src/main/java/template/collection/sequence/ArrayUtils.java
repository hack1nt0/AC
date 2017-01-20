package template.collection.sequence;

import java.io.PrintStream;
import java.util.Arrays;

/**
 * Created by dy on 16-12-22.
 */
public class ArrayUtils {
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
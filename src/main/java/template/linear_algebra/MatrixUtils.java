package template.linear_algebra;

import template.numbers.DoubleUtils;

import java.util.Arrays;

/**
 * Created by dy on 16-10-8.
 */
public class MatrixUtils {
    // applied t count problem, where every element of Square is positive.
    // there may be overflows, or we just need s the sum of some matrix satisfy
    // some s lower bound.

    public static long[][] multiply(long[][] A, long[][] B) {
        if (A == null || B == null) return null;
        if (A[0].length != B.length) throw new RuntimeException();
        long[][] res = new long[A.length][B[0].length];
        for (int i = 0; i < A.length; ++i)
            for (int j = 0; j < B[0].length; ++j) {
                res[i][j] = 0;
                for (int k = 0; k < B.length; ++k) {
                    res[i][j] += A[i][k] * B[k][j];
                }
            }
        return res;
    }

    public static void multiply(double[][] A, double[][] B) {
        int N = A.length;
        int M = B[0].length;
        int K = A[0].length;
        double[][] res = new double[N][M];
        assert B.length == K;
        for (int i = 0; i < M; ++i)
            for (int j = 0; j < N; ++j)
                for (int k = 0; k < K; ++k)
                    res[i][j] += A[i][k] * B[k][j];
    }

    public static long[][] pow(long[][] M, long k) {
        if (M.length != M[0].length) throw new RuntimeException();
        if (k < 0) throw new RuntimeException();

        long[][] res = new long[M.length][M.length];
        for (int i = 0; i < res.length; ++i) res[i][i] = 1;
        long[][] acc = M;
        while (k > 0) {
            if ((k & 1) > 0) res = multiply(res, acc);
            acc = multiply(acc, acc);
            k >>= 1;
        }
        return res;
    }

    public static long[][] add(long[][] A, long[][] B) {
        if (A == null || B == null) return null;
        if (A.length != B.length || A[0].length != B[0].length) throw new RuntimeException();

        long[][] res = new long[A.length][A[0].length];
        for (int i = 0; i < A.length; ++i)
            for (int j = 0; j < A[0].length; ++j) {
                res[i][j] = A[i][j] + B[i][j];
            }
        return res;
    }

    public static class MatrixStat {
        public int rank;
        public double determinant;
        public double[] x;
        public boolean unique, infinite, none;

        @Override
        public String toString() {
            return "MatrixStat{" +
                    "rank=" + rank +
                    ", determinant=" + determinant +
                    ", x=" + Arrays.toString(x) +
                    ", unique=" + unique +
                    ", infinite=" + infinite +
                    ", none=" + none +
                    '}';
        }
    }

    public static MatrixStat solve(double[][] A, double[] b) {
        MatrixStat res = new MatrixStat();
        int n = A.length, m = A[0].length;
        if (n != b.length) throw new IllegalArgumentException();
        res.determinant = 1D;
        res.x = new double[m];
        int nswap = 0;
        double eps = DoubleUtils.epsilon;
        for (int v = 0; v < m; ++v) {
            int se = Math.min(n - 1, v);
            int max = se;
            for (int e = max + 1; e < n; ++e)
                if (Math.abs(A[e][v]) > Math.abs(A[max][v]))
                    max = e;
            if (max != se) {
                nswap++;
                double[] t = A[se];
                A[se] = A[max];
                A[max] = t;
                double t2 = b[se];
                b[se] = b[max];
                b[max] = t2;
                max = se;
            }
            if (Math.abs(A[max][v]) < eps) {
                res.determinant = 0D;
            } else {
                res.rank++;
                res.determinant *= A[max][v];
                int e = max;
                while (true) {
                    if (e == max) {
                        for (int vv = v + 1; vv < m; ++vv)
                            A[e][vv] /= A[e][v];
                        b[e] /= A[e][v];
                        A[e][v] = 1D;
                    } else {
                        for (int vv = v + 1; vv < m; ++vv)
                            A[e][vv] -= A[max][vv] * A[e][v];
                        b[e] -= b[max] * A[e][v];
                        A[e][v] = 0D;
                    }
                    e = (e + 1) % n;
                    if (e == max)
                        break;
                }
            }
        }
        if (nswap % 2 == 1)
            res.determinant = -res.determinant;
        for (int v = 0; v < m; ++v) {
            int e = Math.min(n - 1, v);
            double denom = Math.abs(A[e][v]) < eps ? 0D : A[e][v];
            double numer = Math.abs(b[e]) < eps ? 0D : b[e];
            res.x[v] = numer / denom;
            if (Double.isInfinite(res.x[v]))
                res.none = true;
            if (Double.isNaN(res.x[v])) {
                res.infinite = true;
                res.x[v] = 0D;
            }
        }
        res.unique = !res.none && !res.infinite;
        if (res.rank > Math.min(n, m))
            throw new RuntimeException();
        return res;
    }

    private static void testUnique() {
        double[][] A = {
                { 0, 1,  1 },
                { 2, 4, -2 },
                { 0, 3, 15 }
        };
        double[] b = { 4, 2, 36 };
        System.out.println(solve(A, b));
    }

    private static void testNone() {
        double[][] A = {
                {  2, -1,  1 },
                {  3,  2, -4 },
                { -6,  3, -3 },
        };
        double[] b = { 1, 4, 2 };
        System.out.println(solve(A, b));
    }

    private static void testInfinite() {
        double[][] A = {
                {  1, -1,  2 },
                {  4,  4, -2 },
                { -2,  2, -4 },
        };
        double[] b = { -3, 1, 6 };
        System.out.println(solve(A, b));
    }

    public static String toString(double[][] m) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m.length; ++i) {
            for (int j = 0; j < m[i].length; ++j)
                sb.append(m[i][j] + (j < m[i].length - 1 ? " " : ""));
            sb.append("\n");
        }
        return sb.toString();
    }

    public static String toString(double[] v) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < v.length; ++i)
            sb.append(v[i] + "\n");
        return sb.toString();
    }

    public static long sum(long[][] A) {
        long res = 0L;
        for (int i = 0; i < A.length; ++i)
            for (int j = 0; j < A[i].length; ++j) res += A[i][j];
        return res;
    }

    public static int[][] rotate90Right(int[][] m) {
        int h = m.length, w = m[0].length;
        int[][] res = new int[w][h];
        for (int i = 0; i < w; ++i) for (int j = 0; j < h; ++j) res[i][j] = m[j][i];
        int lj = 0, rj = h - 1;
        while (lj < rj) {
            for (int i = 0; i < w; ++i) {
                int swap = res[i][lj];
                res[i][lj] = res[i][rj];
                res[i][rj] = swap;
            }
            lj++;
            rj--;
        }
        return res;
    }

    public static int[][] mirrorHorizontally(int[][] m) {
        int h = m.length, w = m[0].length;
        int[][] res = new int[h][w];
        for (int i = 0; i < h; ++i) for (int j = 0; j < w; ++j) res[i][j] = m[i][j];
        int li = 0, ri = h - 1;
        while (li < ri) {
            for (int j = 0; j < w; ++j) {
                int swap = res[li][j];
                res[li][j] = res[ri][j];
                res[ri][j] = swap;
            }
            li++;
            ri--;
        }
        return res;
    }

    public static int[][] mirrorVertically(int[][] m) {
        int h = m.length, w = m[0].length;
        int[][] res = new int[h][w];
        for (int i = 0; i < h; ++i) for (int j = 0; j < w; ++j) res[i][j] = m[i][j];
        int lj = 0, rj = w - 1;
        while (lj < rj) {
            for (int i = 0; i < h; ++i) {
                int swap = res[i][lj];
                res[i][lj] = res[i][rj];
                res[i][rj] = swap;
            }
            lj++;
            rj--;
        }
        return res;
    }

    public static void main(String[] args) {
        testUnique();
        testNone();
        testInfinite();
    }
}

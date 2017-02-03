package template.linear_algebra;

import java.math.BigInteger;

/**
 * Created by dy on 16-10-8.
 */
public class MatrixUtils {
    // applied to count problem, where every element of Square is positive.
    // there may be overflows, or we just need a the sum of some matrix satisfy
    // some a lower bound.
    private static BigInteger LONGMAX = BigInteger.valueOf(Long.MAX_VALUE);
    public static void setOverflowBound(BigInteger up) {
        LONGMAX = up;
    }

    public static long[][] mul(long[][] A, long[][] B) {
        if (A == null || B == null) return null;
        if (A[0].length != B.length) throw new RuntimeException();

        if (A[0].length != B.length) throw new RuntimeException();
        long[][] res = new long[A.length][B[0].length];
        for (int i = 0; i < A.length; ++i)
            for (int j = 0; j < B[0].length; ++j) {
                res[i][j] = 0;
                for (int k = 0; k < B.length; ++k) {
                    BigInteger tmp = BigInteger.valueOf(A[i][k]).multiply(BigInteger.valueOf(B[k][j])).add(BigInteger.valueOf(res[i][j]));
                    if (tmp.compareTo(LONGMAX) >= 0) return null;
                    res[i][j] = tmp.longValue();
                }
            }
        return res;
    }

    public static long[][] pow(long[][] M, long k) {
        if (M.length != M[0].length) throw new RuntimeException();
        if (k < 0) throw new RuntimeException();

        long[][] res = new long[M.length][M.length];
        for (int i = 0; i < res.length; ++i) res[i][i] = 1;
        long[][] acc = M;
        while (k > 0) {
            if ((k & 1) > 0) res = mul(res, acc);
            acc = mul(acc, acc);
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
                BigInteger tmp = BigInteger.valueOf(A[i][j]).add(BigInteger.valueOf(B[i][j]));
                if (tmp.compareTo(LONGMAX) >= 0) return null;
                res[i][j] = tmp.longValue();
            }
        return res;
    }

    public static Long sum(long[][] A) {
        Long res = new Long(0);
        for (int i = 0; i < A.length; ++i)
            for (int j = 0; j < A[i].length; ++j) {
                BigInteger tmp = BigInteger.valueOf(res).add(BigInteger.valueOf(A[i][j]));
                if (tmp.compareTo(LONGMAX) >= 0) return null;
                res = tmp.longValue();
            }

        return res;
    }

    public static SparseMatrix mul(SparseMatrix A, SparseMatrix B) {
        int N = A.N, M = B.M;
        SparseMatrix C = new SparseMatrix(N, M);
        for (int i = 0; i < N; ++i) {
            if (A.matrixR[i] == null) continue;
            for (int j = 0; j < M; ++j) {
                if (B.matrixC[j] == null) continue;
                int cijv = 0;
                if (A.matrixR[i].size() < B.matrixC[j].size()) {
//                    for (Map.Entry<Integer, Long> ake : A.matrixR[i].entrySet()) {
//                        int ak = ake.getKey();
//                        Long bkv = B.matrixC[j].get(ak);
//                        if (bkv == null) continue;
//                        long akv = ake.getFromValue();
//                        cijv += akv * bkv;
//                    }
                    for (int ak : A.matrixR[i].keySet()) {
                        if (!B.matrixC[j].containsKey(ak)) continue;
                        cijv += A.matrixR[i].get(ak) * B.matrixC[j].get(ak);
                    }
                } else {
                    for (int bk : B.matrixC[j].keySet()) {
                        if (!A.matrixR[i].containsKey(bk)) continue;
                        cijv += A.matrixR[i].get(bk) * B.matrixC[j].get(bk);
                    }
                }
                C.put(i, j, cijv);
            }
        }
        return C;
    }

}

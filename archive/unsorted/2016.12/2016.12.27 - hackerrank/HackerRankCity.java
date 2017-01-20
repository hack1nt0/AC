package main;



import template.Numbers;

import java.util.Scanner;
import java.io.PrintWriter;
import static template.Numbers.*;

/**
 * sum_i sum_j {xi + yj} = sum_i sum_j {xi} + sum_i sum_j {yj}
 */
public class HackerRankCity {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        Numbers.MODULUS = (long)1e9 + 7;
        long ret = 0;
        long one2all = 0;
        long diag = 0;
        long cn = 1;
        int N = in.nextInt();
        int[] A = new int[N];
        for (int i = 0; i < N; ++i) A[i] = in.nextInt();
        for (int i = 0; i < N; ++i) {
            long nret = mul(ret, 4);
            nret = add(nret, mul(4, add(add(mul(cn, one2all), mul(cn, mul(cn, 3 * A[i]))), mul(cn, one2all))));
            nret = add(nret, mul(2, add(add(mul(cn, one2all), mul(cn, mul(cn, 2 * A[i]))), mul(cn, one2all))));
            nret = add(nret, mul(4, add(mul(cn, A[i]), one2all)));
            nret = add(nret, mul(4, add(mul(cn, 2 * A[i]), one2all)));
            nret = add(nret, A[i]);
            ret = nret;

            long nOne2all = one2all;
            nOne2all = add(nOne2all, mul(2, add(one2all, mul(cn, add(diag, 3 * A[i])))));
            nOne2all = add(nOne2all, mul(1, add(one2all, mul(cn, add(diag, 2 * A[i])))));
            nOne2all = add(nOne2all, add(add(2 * A[i], diag), add(A[i], diag)));
            one2all = nOne2all;

            diag = add(mul(2, diag), 3 * A[i]);
            cn = add(mul(cn, 4), 2);
        }
        out.println(ret);
    }
}

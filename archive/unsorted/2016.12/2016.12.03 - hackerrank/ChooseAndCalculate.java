package main;

import main.template.Numbers;

import java.util.Arrays;
import java.util.Scanner;
import java.io.PrintWriter;

public class ChooseAndCalculate {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int N = in.nextInt();
        int K = in.nextInt();
        if (K <= 1 || N < K) {
            out.println(0);
            out.close();
            return;
        }
        int[] A = new int[N];
        for (int i = 0; i < N; ++i) A[i] = in.nextInt();

        Arrays.sort(A);
        //for (int i = 0; i < N - 1; ++i) if (A[i] == A[i + 1]) throw new RuntimeException();

        long res = 0;
        int MOD = (int)1e9 + 7;
        //C(i + 1, j) = (i+1)!/(i+1-j)!/j! = C(i,j)*(i+1)/(i+1-j)
        Numbers.MOD = MOD;
        long[] C = new long[N]; C[K - 2] = 1;
//        for (int i = K - 2; i + 1 < N; ++i) C[i + 1] = C[i] * (i + 1) % modulus * IntegerUtils.inv(i + 1 - K + 2) % modulus;
//        for (int i = 0; i < N; ++i)
//            for (int j = i + K - 1; j < N; ++j) {
//            res = (res + Math.abs(A[i] - A[j]) * C[j - i - 1] % modulus) % modulus;
//        }
//        out.println(res);

        Arrays.fill(C, 0);
        C[K - 1] = 1;
        for (int i = K - 1; i + 1 < N; ++i) C[i + 1] = C[i] * (i + 1) % MOD * Numbers.inv(i + 1 - K + 1) % MOD;
        long mins = 0;
        for (int i = 0; i < N; ++i) mins = (mins + A[i] * C[N - i - 1] % MOD) % MOD;
        long maxs = 0;
        for (int i = N - 1; i >= 0; --i) maxs = (maxs + A[i] * C[i] % MOD) % MOD;
        res = (maxs - mins + MOD) % MOD;

        out.println(res);
        out.close();
    }
}

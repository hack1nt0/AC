package main;

import java.math.BigInteger;
import java.util.Scanner;
import java.io.PrintWriter;

public class nCrTable {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int N = in.nextInt();
        long MOD = (long)1e9;
        //long[] C = new long[N + 1]; C[0] = 1;
        //for (int i = 0; i + 1 <= N; ++i) C[i + 1] = C[i] * (N - i) % modulus * IntegerUtils.minv(i + 1, modulus) % modulus;
        BigInteger[] C = new BigInteger[N + 1]; C[0] = BigInteger.ONE;
        for (int i = 0; i + 1 <= N; ++i) C[i + 1] = C[i].multiply(BigInteger.valueOf(N - i)).divide(BigInteger.valueOf(i + 1));
        BigInteger gMOD = BigInteger.valueOf(MOD);
        for (int i = 0; i <= N; ++i) out.print(C[i].mod(gMOD).longValue() + " ");
        out.println();
    }
}

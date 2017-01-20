package main;

import main.template.Numbers;

import java.util.Scanner;
import java.io.PrintWriter;

//unsolved
public class ChocolateFiesta {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int N = in.nextInt();
        int[] cnt = new int[2];
        for (int i = 0; i < N; ++i) cnt[in.nextInt() % 2]++;
        long MOD = (long)1e9 + 7;
        Numbers.MOD = MOD;
        long A = Numbers.pow(2, cnt[0]);
        long nCi = 1;
        long B = 0;

//        long[][] C = new long[N + 1][N + 1];
//        for (int i = 0; i <= N; ++i) {
//            C[0][i] = 0;
//            C[i][0] = 1;
//        }
//        for (int i = 1; i <= N; ++i)
//            for (int j = 1; j <= N; ++j) C[i][j] = (C[i - 1][j - 1] + C[i - 1][j]) % modulus;

        for (int i = 0; i <= cnt[1];) {
            B = (B + nCi) % MOD;
            i += 2;
            nCi = nCi * (cnt[1] - i + 2) % MOD * (cnt[1] - i + 1) % MOD * Numbers.inv(i * (i - 1)) % MOD;
        }

        //System.err.println(B + ", " + IntegerUtils.pow(2, cnt[1] - 1));

        long res = A * B % MOD;
        out.println(res - 1);
    }
}

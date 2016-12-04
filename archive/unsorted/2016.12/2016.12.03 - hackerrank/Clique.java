package main;

import java.util.Scanner;
import java.io.PrintWriter;

public class Clique {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int N = in.nextInt();
        int M = in.nextInt();
        int ret = N * N - 2 * M;
        ret = (N * N + ret - 1) / ret;
        int L = 1;
        int R = 1;
        for (int i = 2; i <= N; ++i) if (i * (i - 1) / 2 <= M) R = i * (i - 1) / 2;
        R += 1;
        while (L < R) {
            int k = L + (R - L) / 2;
            if (M <= (k - 1) * N * N / (2 * k)) R = k;
            else L = k + 1;
        }
        out.println(L);
    }
}

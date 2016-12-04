package main;

import java.util.Scanner;
import java.io.PrintWriter;

public class QuicksortInPlace {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int N = in.nextInt();
        int[] A = new int[N];
        for (int i = 0; i < N; ++i) A[i] = in.nextInt();
        sort(A, 0, N, out);
        out.close();
    }

    private static void sort(int[] A, int L, int R, PrintWriter out) {
        if (R - L <= 1) return;
        int p = L;
        for (int i = L; i < R; ++i) {
            if (A[i] > A[R - 1] && A[p] < A[R - 1]) p = i;
            if (A[i] < A[R - 1] && A[p] > A[R - 1]) {
                int tmp = A[i];
                A[i] = A[p];
                A[p] = tmp;
                p++;
            }
        }
        if (A[p] > A[R - 1]) {
            int tmp = A[p];
            A[p] = A[R - 1];
            A[R - 1] = tmp;
        } else {
            p = R - 1;
        }
        for (int i = 0; i < A.length; ++i) out.print(A[i] + " ");
        out.println();
        sort(A, L, p, out);
        sort(A, p + 1, R, out);
    }
}

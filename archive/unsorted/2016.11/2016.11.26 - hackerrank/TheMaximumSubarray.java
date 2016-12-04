package template;

import java.util.Arrays;
import java.util.Scanner;
import java.io.PrintWriter;

public class TheMaximumSubarray {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int N = in.nextInt();
        int[] A = new int[N];
        for (int i = 0; i < N; ++i) A[i] = in.nextInt();
        int ret1 = Integer.MIN_VALUE;
        int preMin = 0, curAcc = 0;
        for (int i = 0; i < A.length; ++i) {
            curAcc += A[i];
            ret1 = Math.max(ret1, curAcc - preMin);
            preMin = Math.min(preMin, curAcc);
        }

        Arrays.sort(A);
        int ret2 = A[A.length - 1];
        for (int i = A.length - 2; i >= 0; --i) {
            if (A[i] <= 0) break;
            ret2 += A[i];
        }
        out.println(ret1 + " " + ret2);
    }
}

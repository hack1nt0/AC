package main;

import java.util.Scanner;
import java.io.PrintWriter;

// Divide and Conquer not work?
public class MountainView {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        out.print("Case #" + testNumber + ": ");
        int N = in.nextInt();
        int[] height = new int[N];
        int[] which = new int[N];
        for (int i = 0; i < N - 1; ++i) which[i] = in.nextInt() - 1;
        which[N - 1] = N - 1;
        if (!good(0, N - 1, which)) {
            out.println("Impossible");
        } else {
            dfs(0, N - 1, which, height);
            for (int i = 0; i < N; ++i) out.print(height[i] + " ");
            out.println();
        }
    }

    private boolean good(int s, int t, int[] which) {
        if (s + 1 >= t)
            return true;
        for (int i = s; i < which[s]; ++i) if (which[i] > which[s]) return false;
        System.out.println(s + " " + t);
        return good(s + 1, which[s] - 1, which) && good(which[s], t, which);
    }

    private void dfs(int s, int t, int[] which, int[] height) {
        if (s + 1 >= t)
            return;
        dfs(s + 1, which[s], which, height);
        dfs(which[s] + 1, t, which, height);
        throw new RuntimeException();
    }
}

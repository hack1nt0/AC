package main;

import template.combinatorics.CombinatoricsUtils;
import template.numbers.IntegerUtils;

import java.util.Arrays;
import java.util.Scanner;
import java.io.PrintWriter;

public class Runround {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        long m = in.nextLong();
        int[] ans = IntegerUtils.toArray(m + 1);
        boolean[] used = new boolean[10];
        for (int i = 0; i < ans.length; ++i) {
            if (ans[i] == 0 || used[ans[i]]) {
                for (int j = i, k = 1; j < ans.length && k < 10; ++k) {
                    if (used[k]) continue;
                    used[k] = true;
                    ans[j++] = k;
                }
                break;
            }
            used[ans[i]] = true;
        }
        while (true) {
//            System.err.println(ans);
//            Arrays.sort(digits);
//            if (digits[0] == 0) continue;
//            boolean repeated = false;
//            for (int i = 0; i < digits.length - 1; ++i) {
//                if (digits[i] == digits[i + 1]) {
//                    repeated = true; break;
//                }
//            }
//            if (repeated) continue;

            int from = 0;
            int runN = 0;
            boolean ok = true;
            boolean[] visited = new boolean[ans.length];
            while (true) {
                if (visited[from]) {
                    if (runN < ans.length || from != 0) ok = false;
                    break;
                }
                visited[from] = true;
                int to = (from + ans[from]) % ans.length;
                from = to;
                runN++;
            }

            System.err.println("end " + ans);
            if (ok) break;

            if (!CombinatoricsUtils.nextPermutation(ans)) {
                Arrays.fill(used, false);
                for (int i = 0; i < ans.length; ++i) used[ans[i]] = true;
                for (int i = 1; i < 10; ++i) if (!used[i]) {used[i] = true; break;}
                ans = new int[ans.length + 1];
                for (int i = 1, j = 0; j < ans.length && i < 10; ++i) {
                    if (!used[i]) continue;
                    ans[j++] = i;
                }
            }
        }

        out.println(IntegerUtils.fromArray(ans));
    }
}

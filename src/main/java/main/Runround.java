package main;

import template.numbers.IntegerUtils;

import java.util.Scanner;
import java.io.PrintWriter;
/*
 ID: hackint1
 LANG: JAVA
 TASK: runround
*/
public class Runround {
    long ans;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        long m = in.nextLong();
//        int[] ans = IntegerUtils.toArray(m + 1);
//        boolean[] used = new boolean[10];
//        for (int i = 0; i < ans.length; ++i) {
//            if (ans[i] == 0 || used[ans[i]]) {
//                for (int j = i, k = 1; j < ans.length && k < 10; ++k) {
//                    if (used[k]) continue;
//                    used[k] = true;
//                    ans[j++] = k;
//                }
//                break;
//            }
//            used[ans[i]] = true;
//        }
//        while (true) {
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

//            System.err.println("end " + IntegerUtils.fromArray(ans));
//            int from = 0;
//            int runN = 0;
//            boolean ok = true;
//            boolean[] visited = new boolean[ans.length];
//            while (true) {
//                if (visited[from]) {
//                    if (runN < ans.length || from != 0) ok = false;
//                    break;
//                }
//                visited[from] = true;
//                int to = (from + ans[from]) % ans.length;
//                from = to;
//                runN++;
//            }
//
//            if (ok) break;
//
//            if (!CombinatoricsUtils.nextPermutation(ans)) {
//                Arrays.fill(used, false);
//                for (int i = 0; i < ans.length; ++i) used[ans[i]] = true;
//                for (int i = 1; i < 10; ++i) if (!used[i]) {used[i] = true; break;}
//                ans = new int[ans.length + 1];
//                for (int i = 1, j = 0; j < ans.length && i < 10; ++i) {
//                    if (!used[i]) continue;
//                    ans[j++] = i;
//                }
//            }
//        }


        boolean[] used = new boolean[10];
        ans = Long.MAX_VALUE;
        dfs(0, 0, m, used);
        assert ans != Long.MAX_VALUE;
        out.println(ans);
    }

    private void dfs(int cur, long acc, long m, boolean[] used) {
        if (cur >= 10) return;
        if (m < acc && acc < ans && runroundable(acc)) ans = acc;
        for (int i = 1; i < 10; ++i) {
            if (used[i]) continue;
            used[i] = true;
            dfs(cur + 1, acc * 10 + i, m, used);
            used[i] = false;
        }
    }

    private boolean runroundable(long n) {
        int[] digits = IntegerUtils.toArray(n);
        int from = 0;
        int runN = 0;
        boolean ok = true;
        boolean[] visited = new boolean[digits.length];
        while (true) {
            if (visited[from]) {
                if (runN < digits.length || from != 0) ok = false;
                break;
            }
            visited[from] = true;
            int to = (from + digits[from]) % digits.length;
            from = to;
            runN++;
        }
        return ok;
    }
}

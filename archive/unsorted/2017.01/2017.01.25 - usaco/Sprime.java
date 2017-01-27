package main;

import template.numbers.IntegerUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.PrintWriter;

public class Sprime {
    List<Integer> ans;
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
//        int from = (int)Math.pow(10, n - 1);
//        int to   = (int)Math.pow(10, n);
//        boolean[] isPrime = new boolean[(int)Math.sqrt(to)];
//        Arrays.fill(isPrime, true);
//        isPrime[1] = false;
//        boolean[] isPrime2 = new boolean[to - from];
//        Arrays.fill(isPrime2, true);
//        for (int i = 2; i < isPrime.length; ++i) {
//            if (!isPrime[i]) continue;
//            for (int j = i + i; j < isPrime.length; j += i) isPrime[j] = false;
//            for (int j = (from + i - 1) / i * i; j < to; j += i) {
//                if (j < from) throw new RuntimeException();
//                isPrime2[j - from] = false;
//                //System.err.printlnTable(j);
//            }
//        }
//
//        for (int i = 0; i < isPrime2.length; ++i) {
//            if (isPrime2[i] != IntegerUtils.isPrime(i + from)) {
//                System.err.printlnTable(i + from);
//                throw new RuntimeException();
//            }
//            if (!isPrime2[i]) continue;
//            out.printlnTable(i + from);
//        }
        ans = new ArrayList<>();
        dfs(0, 0, n);
        for (int sp : ans) out.println(sp);
    }

    private void dfs(int cur, int rib, int n) {
        //System.err.printlnTable(rib);
        if (cur >= n) {
           ans.add(rib);
           return;
        }

        for (int d = cur == 0 ? 1 : 0; d < 10; ++d) {
            int nrib = rib * 10 + d;
            boolean valid = IntegerUtils.isPrime(nrib);
            if (!valid) continue;
            dfs(cur + 1, nrib, n);
        }
    }
}

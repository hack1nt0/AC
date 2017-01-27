package main;

import template.numbers.IntegerUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.io.PrintWriter;

public class Pprime {
    int a, b;
    List<Long> ans;
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        a = in.nextInt();
        b = in.nextInt();
        ans = new ArrayList<>();
        dfs(0, 0, (String.valueOf(b).length() + 1) / 2);

        Collections.sort(ans);
        for (long pp : ans) out.println(pp);
    }

    private void dfs(int cur, int half, int n) {
        if (cur > n) return;
        if (cur > 0) {
            int lenHalf = String.valueOf(half).length();
            long pal1 = half * (long)Math.pow(10, lenHalf - 1) + IntegerUtils.reverse(half / 10);
            if (a <= pal1 && pal1 <= b && IntegerUtils.isPrime(pal1)) ans.add(pal1);
            long pal2 = half * (long)Math.pow(10, lenHalf) + IntegerUtils.reverse(half);
            //System.err.println(pal2);
            if (a <= pal2 && pal2 <= b && IntegerUtils.isPrime(pal2)) ans.add(pal2);
        }
        for (int d = cur == 0 ? 1 : 0; d < 10; ++d)
            dfs(cur + 1, half * 10 + d, n);
    }
}

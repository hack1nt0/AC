package main;

import template.numbers.IntUtils;

import java.util.Arrays;
import java.util.Scanner;
import java.io.PrintWriter;

/*
 ID: hackint1
 LANG: JAVA
 TASK: nuggets
*/

public class Nuggets {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int[] boxes = new int[n];
        for (int i = 0; i < n; ++i) boxes[i] = in.nextInt();
        Arrays.sort(boxes);
        if (boxes.length == 1) {
            out.println(0);
            return;
        }
        long gcd = IntUtils.gcd(boxes[0], boxes[1]);
        for (int i = 2; i < boxes.length; ++i) gcd = IntUtils.gcd(gcd, boxes[i]);
        if (gcd != 1) {
            out.println(0);
            return;
        }

        int MAX_NUGGETS = (int)IntUtils.lcm(boxes[boxes.length - 1], boxes[boxes.length - 2]);
        boolean[][] ok = new boolean[MAX_NUGGETS + 1][n + 1];
        Arrays.fill(ok[0], true);
        for (int i = 1; i <= MAX_NUGGETS; ++i) {
            for (int j = 0; j < n; ++j) {
                ok[i][j + 1] = ok[i][j];
                if (ok[i][j + 1]) continue;
                if (i >= boxes[j]) ok[i][j + 1] |= ok[i - boxes[j]][j + 1];
            }
        }

        for (int i = MAX_NUGGETS; i > 0; --i) if (!ok[i][n]) {
            out.println(i);
            return;
        }
    }
}

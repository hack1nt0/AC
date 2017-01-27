package main;

import template.collection.sequence.ArrayUtils;
import template.numbers.IntegerUtils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.io.PrintWriter;

public class Numtri {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int[] pre = new int[n];
        int[] cur = new int[n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j <= i; ++j) {
                if (j < i) cur[j] = Math.max(cur[j], pre[j]);
                if (j - 1 >= 0) cur[j] = Math.max(cur[j], pre[j - 1]);
                cur[j] += in.nextInt();
            }
            int[] t = pre;
            pre = cur;
            cur = t;
            Arrays.fill(cur, 0);
        }

        out.println(ArrayUtils.max(pre));
    }

    public static void main(String[] args) throws IOException {
        int n = 1000;
        PrintWriter out = new PrintWriter(new FileOutputStream("output/numtri.in"));
        ArrayUtils.println(n, out);
        for (int i = 0; i < n; ++i) {
            ArrayUtils.println(IntegerUtils.randomInts(i + 1, 0, 101), out);
        }
        out.close();
    }
}

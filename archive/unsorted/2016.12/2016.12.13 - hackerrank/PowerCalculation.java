package main;

import template.Numbers;

import java.util.Scanner;
import java.io.PrintWriter;

public class PowerCalculation {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        long K = in.nextLong();
        long N = in.nextLong();
        long[] first100 = new long[100 + 1];
        Numbers.MOD = 100;
        for (int i = 1; i <= 100; ++i) first100[i] = Numbers.pow(i, N);
        long[] acc = new long[first100.length];
        for (int i = 1; i < acc.length; ++i) acc[i] = acc[i - 1] + first100[i];
        long ret = Numbers.add(Numbers.mul(K / 100, acc[100]), acc[(int)(K % 100)]);
        out.printf("%02d\n", ret);
    }
}

package main;

import java.util.Scanner;
import java.io.PrintWriter;

public class A {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        out.println("Case #" + testNumber + ":");
        int R = in.nextInt();
        int C = in.nextInt();
        char[][] cake = new char[R][];
        for (int i = 0; i < R; ++i) {
            cake[i] = in.next().toCharArray();
        }
        for (int r = 0; r < R; ++r) {
            int p = 0;
            while (p < C) {
                int q = p;
                while (q < C && cake[r][q] == '?')
                    ++q;
                if (q == C && p == 0)
                    break;
                if (q == C) {
                    char inital = cake[r][p - 1];
                    for (int c = p; c < q; ++c)
                        cake[r][c] = inital;
                } else {
                    char inital = cake[r][q];
                    for (int c = p; c < q; ++c)
                        cake[r][c] = inital;
                }
                p = q + 1;
            }

        }
        int p = 0;
        while (p < R) {
            int q = p;
            while (q < R && cake[q][0] == '?')
                ++q;
            if (q == R) {
                for (int r = p; r < q; ++r) {
                    for (int c = 0; c < C; ++c) {
                        cake[r][c] = cake[p - 1][c];
                    }
                }
            } else {
                for (int r = p; r < q; ++r) {
                    for (int c = 0; c < C; ++c) {
                        cake[r][c] = cake[q][c];
                    }
                }
            }
            p = q + 1;
        }
        for (int r = 0; r < R; ++r) {
            for (int c = 0; c < C; ++c) {
                out.print(cake[r][c]);
            }
            out.println();
        }
    }
}

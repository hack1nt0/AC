package main;

import java.util.Arrays;
import java.util.Scanner;
import java.io.PrintWriter;

/*
 ID: hackint1
 LANG: JAVA
 TASK: transform
*/

public class Transform {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int N = in.nextInt();
        Square beforeM = new Square(N);
        Square afterM = new Square(N);
        for (int i = 0; i < N; ++i) {
            beforeM.set(i, in.next().toCharArray());
        }
        for (int i = 0; i < N; ++i) {
            afterM.set(i, in.next().toCharArray());
        }
        int ans = 7;
        if (beforeM.rot90().equals(afterM)) {
            ans = 1;
        } else if (beforeM.rot180().equals(afterM)) {
            ans = 2;
        } else if (beforeM.rot270().equals(afterM)) {
            ans = 3;
        } else if (beforeM.reflectH().equals(afterM)) {
            ans = 4;
        } else if (beforeM.reflectH().rot90().equals(afterM) ||
                beforeM.reflectH().rot180().equals(afterM) ||
                beforeM.reflectH().rot270().equals(afterM)) {
            ans = 5;
        } else if (beforeM.equals(afterM)) {
            ans = 6;
        }

        out.println(ans);
    }

    static class Square {
        char[][] m;
        int N, M;

        public Square(int N) {
            this.N = N;
            m = new char[N][N];
        }

        public void set(int rn, char[] r) {
            m[rn] = r;
        }

        public void set(int i, int j, char c) {
            m[i][j] = c;
        }

        public Square rot90() {
            Square nm = new Square(N);
            for (int j = N - 1; j >= 0; --j)
                for (int i = 0; i < N; ++i) nm.set(i, j, m[N - 1 - j][i]);
            return nm;
        }

        public Square rot180() {
            return rot90().rot90();
        }

        public Square rot270() {
            return rot90().rot90().rot90();
        }

        public Square reflectH() {
            Square nm = new Square(N);
            for (int i = 0; i < N; ++i)
                for (int j = 0; j < N; ++j) nm.set(i, j, m[i][N - 1 - j]);
            return nm;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Square matrix = (Square) o;

            return Arrays.deepEquals(m, matrix.m);
        }
    }
}

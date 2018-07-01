package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.PrintWriter;

public class TaskA {
    class Rect {
        char initial;
        int rmin = 30, rmax = 0, cmin = 30, cmax = 0;

        public Rect(char initial) {
            this.initial = initial;
        }

        void update(int r, int c) {
            rmin = Math.min(rmin, r);
            rmax = Math.max(rmax, r);
            cmin = Math.min(cmin, c);
            cmax = Math.max(cmax, c);
        }

        boolean valid() {
            return rmin <= rmax && cmin <= cmax;
        }

        void expand(char[][] cake) {
            int R = cake.length;
            int C = cake[0].length;
            for (int r = rmin - 1; r >= 0; --r) {
                if (!all(cake, r, r, cmin, cmax))
                    break;
                fill(cake, r, r, cmin, cmax, initial);
                --rmin;
            }
            for (int r = rmax + 1; r < R; ++r) {
                if (!all(cake, r, r, cmin, cmax))
                    break;
                fill(cake, r, r, cmin, cmax, initial);
                ++rmax;
            }
            for (int c = cmin - 1; c >= 0; --c) {
                if (!all(cake, rmin, rmax, c, c))
                    break;
                fill(cake, rmin, rmax, c, c, initial);
                --cmin;
            }
            for (int c = cmax + 1; c < C; ++c) {
                if (!all(cake, rmin, rmax, c, c))
                    break;
                fill(cake, rmin, rmax, c, c, initial);
                ++cmax;
            }
        }
    }

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        out.println("Case #" + testNumber + ":");
        int R = in.nextInt();
        int C = in.nextInt();
        char[][] cake = new char[R][];
        for (int i = 0; i < R; ++i) {
            cake[i] = in.next().toCharArray();
        }
        int N = 128;
        Rect[] rects = new Rect[N];
        for (int i = 0; i < N; ++i)
            rects[i] = new Rect((char)i);
        for (int r = 0; r < R; ++r) {
            for (int c = 0; c < C; ++c) {
                rects[cake[r][c]].update(r, c);
            }
        }
        for (char initial = 'A'; initial <= 'Z'; ++initial) {
            Rect rect = rects[initial];
            if (!rect.valid())
                continue;
            rect.expand(cake);
        }
        List<Integer> rows = new ArrayList<>();
        for (int r = 0; r < R; ++r) {
            if (!exist(cake[r]))
                rows.add(r);
        }
        rows.add(R);
        if (rows.size() > 0) {
            if (0 < rows.get(0)) {
                for (int r = 0; r < rows.get(0); ++r)
                    System.arraycopy(cake[rows.get(0)], 0, cake[r], 0, C);
            }
            for (int i = 1; i < rows.size(); ++i) {
                int rFrom = rows.get(i - 1);
                int rTo = rows.get(i);
                if (rFrom + 1 < rTo) {
                    for (int r = rFrom + 1; r < rTo; ++r)
                        System.arraycopy(cake[rFrom], 0, cake[r], 0, C);
                }
            }
        }


        for (int r = 0; r < cake.length; ++r) {
            for (int c = 0; c < cake[r].length; ++c) {
                out.print(cake[r][c]);
            }
            out.println();
        }
    }

    private void fill(char[][] cake, int rFrom, int rTo, int cFrom, int cTo, char initial) {
        for (int r = rFrom; r <= rTo; ++r)
            for (int c = cFrom; c <= cTo; ++c)
                cake[r][c] = initial;
    }

    private boolean all(char[][] cake, int rFrom, int rTo, int cFrom, int cTo) {
        for (int r = rFrom; r <= rTo; ++r)
            for (int c = cFrom; c <= cTo; ++c)
                if (cake[r][c] != '?')
                    return false;
        return true;
    }

    private boolean exist(char[] row) {
        for (char c : row)
            if (c == '?')
                return true;
        return false;
    }
}

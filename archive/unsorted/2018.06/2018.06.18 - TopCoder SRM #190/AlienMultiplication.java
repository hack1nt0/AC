package main;

import template.numbers.IntUtils;

public class AlienMultiplication {
    int min = 6;
    int a, b, c;
    public int getCorrections(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
        dfs(0, 6, 0, 0, 0, 10);
        return min;
    }

    private void dfs(int cur, int n, int nchange, int a, int b, int mask) {
//        System.out.println(a + " " + b);
        int i = cur / 2;
        if (i == n) {
            this.min = Math.min(this.min, nchange);
            return;
        }
        if (cur % 2 == 0 && nchange >= min)
            return;
        if (cur % 2 == 0) {
            int ai = digit(this.a, i);
            for (int to = 0; to < 10; ++to) {
                if (to != ai)
                    nchange++;
                int na = a + to * mask / 10;
                int nb = b;
                dfs(cur + 1, n, nchange, na, nb, mask);
                if (to != ai)
                    nchange--;
            }
        }
        if (cur % 2 == 1) {
            int bi = digit(this.b, i);
            for (int to = 0; to < 10; ++to) {
                if (to != bi)
                    nchange++;
                int na = a;
                int nb = b + to * mask / 10;
                int c = (int) ((long) na * nb % mask);
                int cci = digit(c, i);
                int ci = digit(this.c, i);
                if (cci != ci)
                    nchange++;
                dfs(cur + 1, n, nchange, na, nb, mask * 10);
                if (to != bi)
                    nchange--;
                if (cci != ci)
                    nchange--;
            }
        }
    }

    private int digit(int d, int i) {
        for (int ii = 0; ii < i; ++ii)
            d /= 10;
        return d % 10;
    }
}

package main;

import java.util.Scanner;
import java.io.PrintWriter;

//You need to use Turan graph [https://www.wikiwand.com/en/Tur%C3%A1n_graph]
//to find the exact uppper bound of edges of max clique of a graph;
//Finally, solving the min-max prob using binary search.
public class Clique {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int N = in.nextInt();
        int M = in.nextInt();
        int L = 0, R = N;
        while (true) {
            //System.err.println(L + " " + R);
            if (L + 1 >= R) break;
            int maxClique = L + (R - L) / 2;
            if (C(N, M, maxClique)) R = maxClique;
            else L = maxClique;
        }
        out.println(R);
    }

    private boolean C(int n, int m, int r) {
        int floor = n / r;
        int ceil = n / r + 1;
        int maxes = nC2(n) - n % r * nC2(ceil) - (r - n % r) * nC2(floor);
        return nC2(r) <= m && m <= maxes;
    }

    private int nC2(int n) {
        return n * (n - 1) / 2;
    }

}

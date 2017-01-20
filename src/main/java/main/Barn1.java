package main;

import template.collection.intervals.Interval1D;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.io.PrintWriter;

/*
 ID: hackint1
 LANG: JAVA
 TASK: barn1
*/

public class Barn1 {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int N = in.nextInt();
        int S = in.nextInt();
        int C = in.nextInt();
        boolean[] occ = new boolean[S + 1];
        for (int i = 0; i < C; ++i) occ[in.nextInt()] = true;
        int L = 1, R = S;
        while (!occ[L]) L++;
        while (!occ[R]) R--;
        PriorityQueue<Interval1D> pq = new PriorityQueue<Interval1D>(Collections.reverseOrder());
        for (int i = L; i <= R;) {
            while (i <= R && occ[i]) i++;
            if (i > R) break;
            int j = i;
            while (j <= R && !occ[j]) j++;
            //System.out.println(new Interval(i, j));
            pq.add(new Interval1D(i, j));
            i = j;
        }
        N--;
        int ans = R - L + 1;
        while (true) {
            if (N == 0 || pq.size() == 0) break;
            ans -= pq.poll().length();
            N--;
        }

        out.println(ans);
    }
}

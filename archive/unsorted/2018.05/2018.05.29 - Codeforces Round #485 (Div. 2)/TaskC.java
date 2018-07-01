package main;

import template.collection.Sorter;
import template.misc.IntComparator;

import java.util.Scanner;
import java.io.PrintWriter;

public class TaskC {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int N = in.nextInt();
        int[] sizes = new int[N];
        int[] costs = new int[N];
        for (int i = 0; i < N; ++i) sizes[i] = in.nextInt();
        for (int i = 0; i < N; ++i) costs[i] = in.nextInt();
        int[] order = new int[N];
        for (int i = 0; i < N; ++i) order[i] = i;
        Sorter.sort(order, new IntComparator() {
            @Override
            public int compare(int a, int b) {
                return sizes[a] - sizes[b];
            }
        });
        int oo = Integer.MAX_VALUE;
        int min = oo;
        for (int mid = 1; mid < N - 1; ++mid) {
            int j = order[mid];
            int minl = oo;
            for (int ii = 0; ii < mid; ++ii) {
                int i = order[ii];
                if (sizes[i] < sizes[j] && i < j)
                    minl = Math.min(minl, costs[i]);
            }
            int minr = oo;
            for (int kk = mid + 1; kk < N; ++kk) {
                int k = order[kk];
                if (sizes[j] < sizes[k] && j < k)
                    minr = Math.min(minr, costs[k]);
            }
            if (minl != oo && minr != oo)
                min = Math.min(min, costs[j] + minl + minr);
        }
        if (min == oo)
            min = -1;
        out.println(min);
    }
}

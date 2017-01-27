package main;

import template.collection.intervals.Interval;

import java.util.*;
import java.io.PrintWriter;

public class Ariprog {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        boolean[] bisquaresSet = new boolean[m * m * 2 + 1];
        List<Integer> sortedBisquares = new ArrayList<>();
        for (int p = 0; p <= m; ++p)
            for (int q = p; q <= m; ++q) {
                int bi = p * p + q * q;
                if (!bisquaresSet[bi]) {
                    bisquaresSet[bi] = true;
                    sortedBisquares.add(bi);
                }
            }
        Collections.sort(sortedBisquares);
        List<Interval> ans = new ArrayList<>();
        for (int aId = 0; aId < sortedBisquares.size(); ++aId) {
            for (int aplusbId = aId + 1; aplusbId < sortedBisquares.size(); ++aplusbId) {
                int a = sortedBisquares.get(aId);
                int aplusb = sortedBisquares.get(aplusbId);
                int b = aplusb - a;
                boolean ok = true;
                for (int i = 0, last = aplusb + b;; ++i, last += b) {
                    if (i >= n - 2) break;
                    if (last >= bisquaresSet.length || !bisquaresSet[last]) {
                        ok = false; break;
                    }
                }
                if (ok) {
                    ans.add(new Interval(a, aplusb));
                }
            }
        }
        System.out.println(Integer.highestOneBit(Integer.MIN_VALUE));
        if (ans.size() == 0) {
            out.println("NONE");
            return;
        }
        Collections.sort(ans, Interval.LENGTH_ORDER);
        for (Interval interval : ans) out.println(interval.left() + " " + (interval.right() - interval.left()));
    }
}

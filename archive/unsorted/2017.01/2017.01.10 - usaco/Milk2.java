package main;

import template.geometry.Interval;

import java.util.Arrays;
import java.util.Scanner;
import java.io.PrintWriter;

/*
 ID: hackint1
 LANG: JAVA
 TASK: milk2
*/

public class Milk2 {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int N = in.nextInt();
        Interval[] interval1Ds = new Interval[N];
        for (int i = 0; i < N; ++i) interval1Ds[i] = new Interval(in.nextInt(), in.nextInt());
        Arrays.sort(interval1Ds, Interval.LEFT_ENDPOINT_ORDER);
        assert N >= 1;
        double L = interval1Ds[0].left(), R = interval1Ds[0].right();
        double ans1, ans2;
        ans1 = R - L;
        ans2 = 0;

        for (int i = 1; i < N; ++i) {
            if (interval1Ds[i].left() <= R) {
                R = Math.max(R, interval1Ds[i].right());
            } else {
                ans1 = Math.max(ans1, R - L);
                ans2 = Math.max(ans2, interval1Ds[i].left() - R);
                L = interval1Ds[i].left();
                R = interval1Ds[i].right();
            }
        }

        ans1 = Math.max(ans1, R - L);

        out.println((int)ans1 + " " + (int)ans2);
    }
}

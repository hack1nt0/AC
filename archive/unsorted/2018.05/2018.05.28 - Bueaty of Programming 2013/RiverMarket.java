package main;

import template.geometry.Interval;
import java.util.Scanner;
import java.io.PrintWriter;

public class RiverMarket {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int N = in.nextInt();
        double[] x = new double[N];
        double[] y = new double[N];
        for (int i = 0; i < N; ++i) {
            x[i] = in.nextDouble();
            y[i] = in.nextDouble();
        }
        double MAX = 10000;
        double l = 0, r = Math.sqrt(MAX * MAX * 4 + MAX * MAX);
        int iterations = 100;
        Interval ans = null;
        while (l < r && iterations > 0) {
            double mid = (l + r) / 2;
            boolean good = true;
            Interval intersect = new Interval(-MAX, MAX);
            for (int i = 0; i < N; ++i) {
                if (mid < y[i]) {
                    good = false;
                    break;
                }
                double span = Math.sqrt(mid * mid - y[i] * y[i]);
                Interval interval = new Interval(x[i] - span, x[i] + span);
                if (!interval.intersects(intersect)) {
                    good = false;
                    break;
                } else {
                    intersect = Interval.intersects(intersect, interval);
                }
            }
            if (good) {
                r = mid;
                ans = intersect;
            } else
                l = mid;
            iterations--;
        }
        out.print("Case #" + testNumber + ": ");
        out.println(ans.right());
    }
}

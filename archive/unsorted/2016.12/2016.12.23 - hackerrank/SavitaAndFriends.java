package main;

import template.*;

import java.util.*;
import java.io.PrintWriter;

/**
 * arg min-max f ... f denote some function.
 *
 * Binary Search and Intervals related timeout, need some mathematics..
 *
 * This problem illustrate a graph view of min-max(max-min) problem. That is, it's actually a collections of two-dimension curves, each curve is the inner f.
 *
 * Also require geometry, hard t me.
 */
public class SavitaAndFriends {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int N = in.nextInt();
        int M = in.nextInt();
        int K = in.nextInt();
        int maxC = 0;
        int A, B, dAB;
        A = B = dAB = -1;
        ShortestPath sp = new ShortestPath(N);
        for (int i = 1; i <= M; ++i) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            int c = in.nextInt();
            maxC = Math.max(maxC, c);
            if (i == K) {
                A = a; B = b; dAB = c;
            }
            sp.addE(a, b, c);
            sp.addE(b, a, c);
        }

        long[] da = sp.shortestPath(A);
        long[] db = sp.shortestPath(B);
        double L = 0, R = Math.max(Seqs.max(da), Seqs.max(db));
        int niter = 0;
        List<Interval1D> validps = new ArrayList<Interval1D>();
        while (true) {
            niter++;
            if (niter > 100) break;
            double mid = (L + R) / 2;
            boolean ok = true;

            List<Interval1D> interval1Ds = new ArrayList<Interval1D>();
            interval1Ds.add(new Interval1D(0, dAB));
            for (int i = 0; i < N; ++i) {
                List<Interval1D> ninterval1Ds = new ArrayList<Interval1D>();
                double t = mid - da[i];
                if (t >= 0) {
                    Interval1D a = new Interval1D(0, t);
                    for (Interval1D it : interval1Ds) if (a.intersects(it))
                        ninterval1Ds.add(Intervals.intersects(a, it));
                }
                t = dAB - (mid - db[i]);
                if (t <= dAB) {
                    Interval1D a = new Interval1D(t, dAB);
                    for (Interval1D it : interval1Ds) if (a.intersects(it))
                        ninterval1Ds.add(Intervals.intersects(a, it));
                }

                interval1Ds = join(ninterval1Ds);
                if (interval1Ds.size() == 0) {ok = false; break;}

                //System.err.println(interval1Ds.size());
            }

            if (ok) {
                R = mid;
                validps = interval1Ds;
            }
            else L = mid;

        }
        out.printf("%.5f %.5f\n", validps.get(0).left(), R);
    }

    private List<Interval1D> join(List<Interval1D> lst) {
        Collections.sort(lst, Interval1D.LEFT_ENDPOINT_ORDER);
        List<Interval1D> res = new ArrayList<Interval1D>();
        double maxR = 0;
        for (int i = 0; i < lst.size();) {
            maxR = lst.get(i).right();
            int j = i + 1;
            while (j < lst.size() && lst.get(j).left() <= maxR) {
                maxR = Math.max(maxR, lst.get(j).right());
                j++;
            }
            if (j == i + 1) {
                res.add(lst.get(i));
            } else {
                res.add(new Interval1D(lst.get(i).left(), maxR));
            }
            i = j;
        }
        return res;
    }
}

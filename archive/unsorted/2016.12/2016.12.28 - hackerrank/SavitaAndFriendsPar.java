package main;

import concurrency.Scheduler;
import concurrency.Task;
import template.geometry.Interval;
import template.collection.sequence.ArrayUtils;
import template.operation.ShortestPath;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.io.PrintWriter;

public class SavitaAndFriendsPar {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        Scheduler scheduler = new Scheduler(in, out, () -> new Task() {
            int N, M, K, A, B, dAB;
            ShortestPath sp;
            double ret1, ret2;
            @Override
            public void read(Scanner in) {
                N = in.nextInt();
                M = in.nextInt();
                K = in.nextInt();
                A = B = dAB = -1;
                sp = new ShortestPath(N);
                for (int i = 1; i <= M; ++i) {
                    int a = in.nextInt() - 1;
                    int b = in.nextInt() - 1;
                    int c = in.nextInt();
                    //maxC = Math.max(maxC, c);
                    if (i == K) {
                        A = a;
                        B = b;
                        dAB = c;
                    }
                    sp.addE(a, b, c);
                    sp.addE(b, a, c);
                }
            }

            @Override
            public void solve() {
                long[] da = sp.shortestPath(A);
                long[] db = sp.shortestPath(B);
                double L = 0, R = Math.max(ArrayUtils.max(da), ArrayUtils.max(db));
                int niter = 0;
                List<Interval> validps = new ArrayList<Interval>();
                while (true) {
                    niter++;
                    if (niter > 100) break;
                    double mid = (L + R) / 2;
                    boolean ok = true;

                    List<Interval> interval1Ds = new ArrayList<Interval>();
                    //interval1Ds.clear();
                    interval1Ds.add(new Interval(0, dAB));
                    for (int i = 0; i < N; ++i) {
                        List<Interval> ninterval1Ds = new ArrayList<Interval>();
                        double t = mid - da[i];
                        if (t >= 0) {
                            Interval a = new Interval(0, t);
                            for (Interval it : interval1Ds)
                                if (a.intersects(it))
                                    ninterval1Ds.add(Interval.intersects(a, it));
                        }
                        t = dAB - (mid - db[i]);
                        if (t <= dAB) {
                            Interval a = new Interval(t, dAB);
                            for (Interval it : interval1Ds)
                                if (a.intersects(it))
                                    ninterval1Ds.add(Interval.intersects(a, it));
                        }

                        interval1Ds = join(ninterval1Ds);
                        if (interval1Ds.size() == 0) {
                            ok = false;
                            break;
                        }

                        //System.err.println(interval1Ds.size());
                    }

                    if (ok) {
                        R = mid;
                        validps = interval1Ds;
                    } else L = mid;

                }
                ret1 = validps.get(0).left();
                ret2 = R;

            }


            @Override
            public void write(PrintWriter out, int testNumber) {
                out.printf("%.5f %.5f\n", ret1, ret2);
                //out.println("Case #" + testNumber + ":");
            }

            private List<Interval> join(List<Interval> lst) {
                Collections.sort(lst, Interval.LEFT_ENDPOINT_ORDER);
                List<Interval> res = new ArrayList<Interval>();
                double maxR = 0;
                for (int i = 0; i < lst.size(); ) {
                    maxR = lst.get(i).right();
                    int j = i + 1;
                    while (j < lst.size() && lst.get(j).left() <= maxR) {
                        maxR = Math.max(maxR, lst.get(j).right());
                        j++;
                    }
                    if (j == i + 1) {
                        res.add(lst.get(i));
                    } else {
                        res.add(new Interval(lst.get(i).left(), maxR));
                    }
                    i = j;
                }
                return res;
            }

        }, 4);
    }
}
package main;

import template.geometry.GeometryUtils;
import template.geometry.Point;
import template.numbers.DoubleUtils;

import java.util.*;

//WA
public class ElectronicScarecrows {
    public double largestArea(int[] x, int[] y, int n) {
        int N = x.length;
        Point[] points = new Point[N];
        for (int i = 0; i < N; ++i)
            points[i] = new Point(x[i], y[i]);
        double ans = 0;
        for (int s = 0; s < N; ++s) {
            Point start = points[s];
            LinkedList<Point> S = new LinkedList<>();
            for (int o = 0; o < N; ++o)
                if (o != s && points[o].compareTo(start) != 0 && angle(points[o], start) >= 0)
                    S.add(points[o]);
            Collections.sort(S, new Comparator<Point>() {
                @Override
                public int compare(Point o1, Point o2) {
                    double angle1 = angle(o1, start);
                    double angle2 = angle(o2, start);
                    if (DoubleUtils.compare(-angle1, -angle2) != 0)
                        return DoubleUtils.compare(-angle1, -angle2);
                    else
                        return DoubleUtils.compare(o1.distance(start), o2.distance(start));
                }
            });
            S.addFirst(start);
            S.addLast(start);
            int ns = S.size();
            int head = 0;
            int tail = S.size() - 1;
            int atmost = Math.min(n, ns - 1);
            double[][] dp = new double[ns][atmost + 1];
            for (int i = 0; i < ns; ++i)
                dp[i][atmost] = Integer.MIN_VALUE;
            dp[tail][atmost] = 0;
            for (int used = atmost - 1; used >= 0; --used) {
                for (int cur = 0; cur < ns; ++cur) {
                    double res = Integer.MIN_VALUE;
                    if (cur == tail) {
                        res = 0;
                    } else {
                        for (int nxt = cur + 1; nxt < ns; ++nxt) {
                            if (dp[nxt][used + 1] != Integer.MIN_VALUE)
                                res = Math.max(res, dp[nxt][used + 1] + area(S.get(cur),S.get(nxt)));
                        }
                    }
                    dp[cur][used] = res;
                }
            }
            ans = Math.max(ans, dp[head][0]);
        }
        return ans;
    }

    double area(Point a, Point b) {
//        return (b.x * a.y - a.x * b.y) / 2;
        return (b.x - a.x) * (b.y + a.y) / 2;
    }

    private double angle(Point a, Point b) {
        return Math.atan2(b.y - a.y, b.x - a.x);
    }
}

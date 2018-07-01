package main;

import template.geometry.Point;
import template.numbers.DoubleUtils;
import template.numbers.GaussJordanElimination;

import java.util.HashSet;
import java.util.Set;

public class TVTower {
    public double minRadius(int[] x, int[] y) {
        Set<Point> pointSet = new HashSet();
        for (int i = 0; i < x.length; ++i) pointSet.add(new Point(x[i], y[i]));
        Point[] points = pointSet.toArray(new Point[0]);
        int N = points.length;
        if (N == 1)
            return 0;
        double min = Double.MAX_VALUE;
        for (int a = 0; a < N; ++a) {
            for (int b = a + 1; b < N; ++b) {
                Point pa = points[a], pb = points[b];
                double max2 = points[a].distance(points[b]) / 2;
                Point c2 = new Point((pa.x + pb.x) / 2, (pa.y + pb.y) /2);
                if (good(c2, points, max2)) {
                    min = Math.min(min, max2);
                }
                for (int c = b + 1; c < N; ++c) {
                    Point[] p = {points[a], points[b], points[c]};
                    //d[x, a] = d[x, b]
                    //d[x, b] = d[x, c]
                    // (a.x - x.x)^2 + (a.y - x.y)^2 = (b.x - x.x)^2 + (b.y - x.y)^2
                    //a.x^2 - 2*a.x*x.x + a.y^2 - 2*a.y*x.y = b.x^2 - 2*b.x*x.x + b.y^2 - 2 * b.y * x.y
                    //2 * (b.x - a.x) * x.x + 2 * (b.y - a.y) * x.y = b.x ^ 2 + b.y ^ 2 - a.x ^ 2 - a.y ^ 2
                    //||
                    double[][] A = new double[2][2];
                    double[] Y = new double[2];
                    for (int i = 0; i < 2; ++i) {
                        A[i][0] = 2 * (p[i].x - p[i + 1].x);
                        A[i][1] = 2 * (p[i].y - p[i + 1].y);
                        Y[i] = p[i].x * p[i].x + p[i].y * p[i].y - p[i + 1].x * p[i + 1].x - p[i + 1].y * p[i + 1].y;
                    }
                    GaussJordanElimination solver = new GaussJordanElimination(A, Y);
//                    GaussianElimination solver = new GaussianElimination(A, Y);
                    double[] cxy3 = solver.primal();
                    if (cxy3 == null) {
//                        throw new RuntimeException();
                        continue;
                    }
                    Point c3 = new Point(cxy3[0], cxy3[1]);
                    double max3 = c3.distance(p[0]);
                    if (good(c3, points, max3)) {
                        min = Math.min(min, max3);
                    }
                }
            }
        }
        return min;
    }

    private boolean good(Point center, Point[] points, double max) {
        for (Point p : points)
            if (DoubleUtils.compare(center.distance(p), max) > 0)
                return false;
        return true;
    }
}

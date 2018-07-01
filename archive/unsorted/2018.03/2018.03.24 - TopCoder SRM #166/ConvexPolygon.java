package main;

public class ConvexPolygon {
    public double findArea(int[] x, int[] y) {
        double ans = 0;
        for (int i = 1; i < x.length - 1; ++i) {
            double x1 = x[i] - x[0];
            double x2 = x[i + 1] - x[0];
            double y1 = y[i] - y[0];
            double y2 = y[i + 1] - y[0];
            ans +=  Math.abs(x1 * y2 - x2 * y1) / 2;
        }
        return ans;
    }
}

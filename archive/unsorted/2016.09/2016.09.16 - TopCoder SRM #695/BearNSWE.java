package main;

public class BearNSWE {
    public double totalDistance(int[] a, String dir) {
        double x = 0, y = 0;
        double res = 0;
        for (int i = 0; i < a.length; ++i) {
            char d = dir.charAt(i);
            res += a[i];
            if (d == 'N') y += a[i];
            if (d == 'S') y -= a[i];
            if (d == 'W') x -= a[i];
            if (d == 'E') x += a[i];
        }
        res += Math.sqrt(x * x + y * y);
        return res;
    }
}

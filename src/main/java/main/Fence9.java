package main;

import template.geometry.GeometryUtils;
import template.geometry.Line;
import template.geometry.Point;

import java.util.Scanner;
import java.io.PrintWriter;

/*
 ID: hackint1
 LANG: JAVA
 TASK: fence9
*/

public class Fence9 {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int p = in.nextInt();
        Point a = new Point(0, 0);
        Point b = new Point(n, m);
        Point c = new Point(p, 0);
        long lattices = 0;
        {
            Line line1 = new Line(a, b);
            for (int x = 0; x <= n; ++x) {
                double yActual = line1.y(x);
                long y = Math.round(yActual);
                //System.out.println(x + " " + yActual + " " + y);
                if (Math.abs(yActual - y) <= GeometryUtils.epsilon || y > yActual) lattices += Math.max(0, y - 1);
                else lattices += Math.max(0, y);
            }
        }
        if (n == p) {
            lattices -= m - 1;
        } else if (n < p) {
            Line line1 = new Line(b, c);
            for (int x = p; x > n; --x) {
                double yActual = line1.y(x);
                long y = Math.round(yActual);
                if (Math.abs(yActual - y) <= GeometryUtils.epsilon || y > yActual) lattices += Math.max(0, y - 1);
                else lattices += Math.max(0, y);
            }
        } else {
            Line line1 = new Line(b, c);
            int minus = 0;
            for (int x = p; x <= n; ++x) {
                double yActual = line1.y(x);
                long y = Math.round(yActual);
                if (Math.abs(yActual - y) <= GeometryUtils.epsilon || y < yActual) minus += Math.max(0, y);
                else minus += Math.max(0, y - 1);
            }
            minus--;
            lattices -= minus;
        }

        out.println(lattices);
    }
}

package main;

import template.debug.Stopwatch;
import template.geometry.LineSegment;
import template.geometry.Point;
import template.geometry.Polygon;

import java.io.InputStream;
import java.util.Scanner;
import java.io.PrintWriter;

/*
 ID: hackint1
 LANG: JAVA
 TASK: fc
*/

public class Fc {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        Point[] points = new Point[n];
        Stopwatch.tic();
        for (int i = 0; i < n; ++i) points[i] = new Point(in.nextDouble(), in.nextDouble());
        Stopwatch.toc();

        Stopwatch.tic();
        Polygon hull = Polygon.convexHull(points);
        Stopwatch.toc();

        Stopwatch.tic();
        double circumference= 0;
        Stopwatch.toc();
        for (LineSegment lineSegment : hull.sides()) {
            circumference += lineSegment.length();
        }
        out.printf("%.2f\n", circumference);
    }
}

package main;

import template.debug.InputReader;
import template.debug.RandomUtils;
import template.geometry.LineSegment;
import template.geometry.Point;

import java.io.PrintWriter;

/*
 ID: hackint1
 LANG: JAVA
 TASK: fence3
*/
/**
   模拟退火算法(Simulated Annealing): 温度越高,能量变化发生的概率越大; 相同温度下,发生越大能量变化的概率越小。

    Prob(energyDiff) = exp(energyDiff / temperature) : energyDiff < 0
 */
public class Fence3 {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.readInt();
        LineSegment[] segs = new LineSegment[n];
        for (int i = 0; i < n; ++i) segs[i] = new LineSegment(new Point(in.readInt(), in.readInt()), new Point(in.readInt(), in.readInt()));
        double temperature = 10000;
        double alpha = 0.01;
        int maxIter = 10000;
        double x = RandomUtils.uniform(0.0, 100);
        double y = RandomUtils.uniform(0.0, 100);
        double optimalDist = dist(x, y, segs);
        for (int iter = 0; iter < maxIter; ++iter) {
            double curX = x + RandomUtils.uniform(-0.1, +0.1);
            double curY = y + RandomUtils.uniform(-0.1, +0.1);
            if (curX < 0 || curX > 1000 || curY < 0 || curY > 1000) continue;
            double curDist = dist(curX, curY, segs);
            if (curDist < optimalDist) {
                optimalDist = curDist;
                x = curX;
                y = curY;
            } else {
                double energyDiff = optimalDist - curDist;
                double acceptProb = Math.exp(energyDiff / temperature);
                if (RandomUtils.uniform() < acceptProb) {
                    optimalDist = curDist;
                    x = curX;
                    y = curY;
                }
            }
            temperature *= alpha;
        }

        out.printf("%.1f %.1f %.1f\n", x, y, optimalDist);
    }

    private double dist(double x, double y, LineSegment[] segs) {
        Point point = new Point(x, y);
        double dist = 0;
        for (LineSegment seg : segs) dist += seg.distance(point);
        return dist;
    }
}

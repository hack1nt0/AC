package main;

import template.numbers.DoubleUtils;

public class FixedPointTheorem {
    public double cycleRange(double R) {
        double x = .25;
        for (int i = 0; i < 200000; ++i) x = R * x * (1 - x);
        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;
        for (int i = 0; i < 1000; ++i) {
            x = R * x * (1 - x);
            min = Math.min(min, x);
            max = Math.max(max, x);
        }
        return max - min;
    }
}

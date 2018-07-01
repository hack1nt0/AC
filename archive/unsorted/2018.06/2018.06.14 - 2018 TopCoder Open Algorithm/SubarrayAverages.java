package main;

import template.numbers.DoubleUtils;

public class SubarrayAverages {
    public double[] findBest(int[] arr) {
        int n = arr.length;
        double[] min = new double[n];
        for (int i = 0; i < n; ++i) min[i] = arr[i];
        for (int i = 0; i < n; ++i) {
            double mi = min[i];
            double sum = min[i];
            int to = i + 1;
            for (int j = i + 1; j < n; ++j) {
                sum += min[j];
                double avg = sum / (j - i + 1);
                if (DoubleUtils.compare(avg, mi) < 0) {
                    to = j + 1;
                    mi = avg;
                }
            }
            for (int j = i; j < to; ++j)
                min[j] = mi;
        }
        return min;
    }
}

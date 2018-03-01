package main;

import java.util.Arrays;

public class EllysBottles {
    public double getVolume(int[] bottles, int k) {
        int n = bottles.length;
        double[] capacity = new double[n];
        for (int i = 0; i < n; ++i) capacity[i] = bottles[i];
        double average = Arrays.stream(capacity).sum() / n;
        Arrays.sort(capacity);
        double res = capacity[0];
        for (int i = 0; i < k; ++i) {
//            if (Math.abs(res - average) <= 1e-9) break; //Timeout
            double min = capacity[0];
            double max = capacity[n - 1];
            if (max - min <= 1e-9) break; //Correct
            capacity[0] = capacity[n - 1] = (max + min) / 2.0;
            Arrays.sort(capacity);
            res = capacity[0];
        }
        return res;
    }
}

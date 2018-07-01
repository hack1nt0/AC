package main;

import template.numbers.DoubleUtils;

import java.util.Arrays;
import java.util.Comparator;

public class RaceCalculator {
    public int bestRace(int[] distances, int[] times) {
        int n = distances.length;
        Race[] races = new Race[n];
        for (int i = 0; i < n; ++i)
            races[i] = new Race(distances[i], times[i], i);
        Arrays.sort(races, new Comparator<Race>() {
            @Override
            public int compare(Race o1, Race o2) {
                return o1.dist - o2.dist;
            }
        });
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                for (int k = 0; k < n; ++k) {
                    if (k == i || k == j)
                        continue;
                    double d1 = races[i].dist;
                    double t1 = races[i].time;
                    double d2 = races[j].dist;
                    double t2 = races[j].time;
                    double d = races[k].dist;
                    double expect = t1 * Math.exp(Math.log(t2 / t1) * Math.log(d1 / d) / Math.log(d1 / d2));
                    double error = (races[k].time - expect) / expect;
                    races[k].maxError = Math.max(races[k].maxError, error);
                }
            }
        }
        Arrays.sort(races, new Comparator<Race>() {
            @Override
            public int compare(Race o1, Race o2) {
                return DoubleUtils.compare(o1.maxError, o2.maxError);
            }
        });
        return races[0].index;
    }

    class Race {
        int dist, time, index;
        double maxError = -Double.MAX_VALUE;

        public Race(int dist, int time, int index) {
            this.dist = dist;
            this.time = time;
            this.index = index;
        }
    }
}

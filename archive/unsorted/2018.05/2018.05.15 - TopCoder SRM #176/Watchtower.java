package main;

import template.collection.Sorter;
import template.collection.sequence.ArrayUtils;
import template.debug.RandomUtils;
import template.misc.IntComparator;

import java.util.Arrays;

public class Watchtower {
    public int[] orderByArea(int[] x, int[] y) {
        int N = x.length;
        int[] area = new int[N];
        int MAX_ITER = 100000;
        int[] order = null;
        while (true) {
            for (int iter = 0; iter < 10000000; ++iter) {
                double xx = RandomUtils.uniform() * 100;
                double yy = RandomUtils.uniform() * 100;
                double minDist = Double.MAX_VALUE;
                int closer = 0;
                for (int i = 0; i < N; ++i) {
                    double dist = (xx - x[i]) * (xx - x[i]) + (yy - y[i]) * (yy - y[i]);
                    if (dist < minDist) {
                        minDist = dist;
                        closer = i;
                    }
                }
                area[closer]++;
            }
            int[] curOrder = ArrayUtils.index(N);
            Sorter.sort(curOrder, new IntComparator() {
                @Override
                public int compare(int a, int b) {
                    return -area[a] + area[b];
                }
            });
            if (order == null || !Arrays.equals(order, curOrder))
                order = curOrder;
            else
                break;
        }
        return order;
    }
}

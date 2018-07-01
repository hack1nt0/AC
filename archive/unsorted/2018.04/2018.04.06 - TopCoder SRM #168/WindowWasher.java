package main;

import java.util.Arrays;

public class WindowWasher {
    public int fastest(int width, int height, int[] washTimes) {
        Arrays.sort(washTimes);
        int[][] minMax = new int[width + 1][washTimes.length];
        for (int from = width - 1; from >= 0; --from) {
            for (int which = 0; which < washTimes.length; ++which) {
                int res = Integer.MAX_VALUE;
                if (which < washTimes.length - 1) {
                    for (int to = from + 1; to <= width; ++to) {
                        res = Math.min(res, Math.max((to - from) * height * washTimes[which], minMax[to][which + 1]));
                    }
                } else {
                    res = (width - from) * height * washTimes[which];
                }
                minMax[from][which] = res;
            }
        }
        return minMax[0][0];
    }
}

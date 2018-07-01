package main;

import template.collection.sequence.ArrayUtils;

import java.util.Arrays;

public class FanFailure {
    public int[] getRange(int[] capacities, int minCooling) {
        Arrays.sort(capacities);
        int n = capacities.length;
        int[] ans = new int[2];
        int acc = 0;
        int sum = ArrayUtils.sum(capacities);
        for (int i = n - 1; i >= 0; --i) {
            acc += capacities[i];
            if (acc >= minCooling && ans[0] == 0) {
                ans[0] = i;
            }
            if (sum - acc >= minCooling) {
                ans[1] = n - i;
            }
        }
        return ans;
    }
}

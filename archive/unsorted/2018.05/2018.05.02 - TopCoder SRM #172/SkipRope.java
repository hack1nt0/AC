package main;

import template.collection.Sorter;
import template.collection.sequence.ArrayUtils;
import template.misc.IntComparator;

public class SkipRope {
    public int[] partners(int[] candidates, int height) {
        Sorter.sort(candidates, new IntComparator() {
            @Override
            public int compare(int a, int b) {
                int diff1 = Math.abs(a - height);
                int diff2 = Math.abs(b - height);
                if (diff1 != diff2)
                    return diff1 - diff2;
                else
                    return -a + b;
            }
        });
        if (candidates[0] > candidates[1]) {
            ArrayUtils.swap(candidates, 0, 1);
        }
        return ArrayUtils.subArray(candidates, 0, 2);
    }
}

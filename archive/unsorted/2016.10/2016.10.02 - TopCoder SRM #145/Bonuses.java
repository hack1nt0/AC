package main;

import java.util.Arrays;
import java.util.Comparator;

public class Bonuses {
    public int[] getDivision(final int[] points) {
        final int[] ans = new int[points.length];
        int tot = 0;
        for (int i = 0; i < points.length; ++i) tot += points[i];
        for (int i = 0; i < ans.length; ++i) ans[i] = points[i] * 100 / tot;

        int left = 100;
        for (int i = 0; i < ans.length; ++i) left -= ans[i];
        Integer[] rank = new Integer[ans.length];
        for (int i = 0; i < rank.length; ++i) rank[i] = i;
        Arrays.sort(rank, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (points[o1] != points[o2]) return points[o2] - points[o1];
                return o1 - o2;
            }
        });
        for (int i = 0; i < left; ++i) ans[rank[i]]++;

        return ans;
    }
}

package main;

import java.util.Arrays;

public class BinPackingEasy {
    public int minBins(int[] item) {
        Arrays.sort(item);
        int l = 1, r = item.length + 1;
        while (l < r) {
            int m = l + (r - l) / 2;
            if (sat(m, item)) r = m;
            else l = m + 1;
        }
        int ans = l;
        return ans;
    }

    private boolean sat(int bn, int[] item) {
        int[] bs = new int[bn];
        Arrays.fill(bs, 300);
        for (int i = item.length - 1; i >= 0; --i) {
            boolean find = false;
            for (int j = 0; j < bs.length; ++j)
                if (item[i] <= bs[j]) {
                    bs[j] -= item[i];
                    find = true;
                    break;
                }
            if (!find) return false;
        }
        return true;
    }
}

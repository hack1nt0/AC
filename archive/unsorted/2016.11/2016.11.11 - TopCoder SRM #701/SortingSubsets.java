package main;

import java.util.Arrays;

public class SortingSubsets {
    public int getMinimalSize(int[] a) {
        int ans = 0;
        int[] sorta = Arrays.copyOf(a, a.length);
        Arrays.sort(sorta);
        for (int i = 0; i < a.length; ++i)
            if (sorta[i] != a[i]) ans++;
        return ans;
    }
}

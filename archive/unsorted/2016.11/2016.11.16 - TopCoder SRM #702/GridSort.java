package main;

import java.util.Arrays;
import java.util.Comparator;

public class GridSort {
    public String sort(final int n, final int m, int[] grid) {
        String OK = "Possible";
        String NO = "Impossible";
        for (int i = 0; i < n; ++i) {
            int r = (grid[i * m] - 1) / m;
            for (int j = 0; j < m; ++j)
                if ((grid[i * m + j] - 1) / m != r) return NO;
        }

        final Integer[][] sortGrid = new Integer[n][m];
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < m; ++j) sortGrid[i][j] = grid[i * m + j];
        Arrays.sort(sortGrid, new Comparator<Integer[]>() {
            @Override
            public int compare(Integer[] o1, Integer[] o2) {
                return (o1[0] - 1) / m - (o2[0] - 1) / m;
            }
        });

        Integer[] idx = new Integer[m];
        for (int i = 0; i < m; ++i) idx[i] = i;
        Arrays.sort(idx, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return sortGrid[0][o1] - sortGrid[0][o2];
            }
        });

        for (int i = 1; i < n; ++i) {
            Integer[] A = new Integer[m];
            for (int j = 0; j < m; ++j) A[j] = sortGrid[i][idx[j]];
            Arrays.sort(sortGrid[i]);
            if (!Arrays.equals(A, sortGrid[i])) return NO;
        }


        return OK;
    }
}

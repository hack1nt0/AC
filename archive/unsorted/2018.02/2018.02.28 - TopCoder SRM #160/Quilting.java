package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Quilting {
    public String lastPatch(int length, int width, String[] colorList) {
        int maxn = 200;
        int[][] cmap = new int[maxn][maxn];
        for (int i = 0; i < maxn; ++i) Arrays.fill(cmap[i], -1);
        int x = 100;
        int y = 100;
        int patches = 1;
        cmap[x][y] = 0;
        int cd = 0;
        int[] dx = {0, -1, 0, +1, -1, -1, +1, +1};
        int[] dy = {1, 0, -1, 0, +1, -1, -1, +1};
        int[] history = new int[colorList.length];
        history[0]++;
        Integer[] index = new Integer[colorList.length];
        for (int i = 0; i < index.length; ++i) index[i] = i;
        while (patches < length * width) {
            int nx = x + dx[cd];
            int ny = y + dy[cd];
            if (cmap[nx][ny] != -1) {
                cd = (cd - 1 + 4) % 4;
                continue;
            }
            else {
                patches++;
                int[] usageNei = new int[colorList.length];
                for (int d = 0; d < 8; ++d) {
                    int nex = nx + dx[d];
                    int ney = ny + dy[d];
                    if (cmap[nex][ney] != -1) usageNei[cmap[nex][ney]]++;
                }
                Arrays.sort(index, new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        if (usageNei[o1] != usageNei[o2])
                            return usageNei[o1] - usageNei[o2];
                        if (history[o1] != history[o2])
                            return history[o1] - history[o2];
                        return o1 - o2;
                    }
                });
                int nc = index[0];
                cmap[nx][ny] = nc;
                history[nc]++;
                cd = (cd + 1) % 4;
                x = nx; y = ny;
//                System.out.println(x + " " + y + " " + colorList[cmap[x][y]]);
            }
        }
        return colorList[cmap[x][y]];
    }
}

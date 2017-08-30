package main;

import template.collection.Sorter;
import template.collection.sequence.ArrayUtils;
import template.misc.IntComparator;

import java.util.Arrays;

public class Quilting {
    public String lastPatch(int length, int width, String[] colorList) {
        int x = width / 2 ;
        int y = (length - 1) / 2;
        int[] dx = {0, -1, 0, +1};
        int[] dy = {+1, 0, -1, 0};
        int[] ddx = {-1, -1, +1, +1};
        int[] ddy = {+1, -1, -1, +1};
        int curDirection = 0;
        int stride = 1;
        int steps = 1;
        int[][] color = new int[width][length];
        for (int i = 0; i < width; ++i) Arrays.fill(color[i], -1);
        color[x][y] = 0;
        int[] global = new int[colorList.length];
        global[0]++;
        int[] local = new int[colorList.length];
        int[] id = ArrayUtils.index(colorList.length);
        while (true) {
            for (int i = 0; i < 2; ++i) {
                for (int j = 0; j < stride; ++j) {
                    if (steps >= length * width) {
                        return colorList[color[x][y]];
                    }
                    x += dx[curDirection];
                    y += dy[curDirection];
                    Arrays.fill(local, 0);
                    for (int d = 0; d < 4; ++d) {
                        int nx = x + dx[d];
                        int ny = y + dy[d];
                        if (nx < 0 || width <= nx || ny < 0 || length <= ny || color[nx][ny] < 0) continue;
                        local[color[nx][ny]]++;
                    }
                    for (int d = 0; d < 4; ++d) {
                        int nx = x + ddx[d];
                        int ny = y + ddy[d];
                        if (nx < 0 || width <= nx || ny < 0 || length <= ny || color[nx][ny] < 0) continue;
                        local[color[nx][ny]]++;
                    }
                    Sorter.sort(id, (a, b) -> {
                            if (local[a] != local[b]) return local[a] - local[b];
                            if (global[a] != global[b]) return global[a] - global[b];
                            return a - b;
                    });
                    color[x][y] = id[0];
                    global[color[x][y]]++;
                    ++steps;
                }
                curDirection = (curDirection + 1) % dx.length;
            }
            ++stride;
        }
    }
}

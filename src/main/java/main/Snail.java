package main;

import template.collection.sequence.ArrayUtils;
import template.debug.InputReader;
import template.debug.OutputWriter;

import java.io.PrintWriter;

/*
 ID: hackint1
 LANG: JAVA
 TASK: snail
*/

public class Snail {
    char[][] grid;
    int[] di = new int[]{-1, 0, +1, 0};
    int[] dj = new int[]{0, +1, 0, -1};
    boolean[][] visited;
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.readInt();
        grid = new char[n][n];
        visited = new boolean[n][n];
        int barries = in.readInt();
        for (int i = 0; i < n; ++i) for (int j = 0; j < n; ++j) grid[i][j] = '.';
        for (int b = 0; b < barries; ++b) {
            String ij = in.readString();
            int i = Integer.valueOf(ij.substring(1)) - 1;
            int j = ij.charAt(0) - 'A';
            grid[i][j] = '#';
        }
        //printMatrix(grid);
        int largest = 0;
        largest = Math.max(largest, dfs(0, 0, 1));
        largest = Math.max(largest, dfs(0, 0, 2));
        out.println(largest);
    }

    private int dfs(int curI, int curJ, int curD) {
        if (out(curI, curJ) || visited[curI][curJ] || grid[curI][curJ] == '#') return 0;
        int ni = curI + di[curD];
        int nj = curJ + dj[curD];
        int res = 0;
        visited[curI][curJ] = true;
        if (out(ni, nj) || grid[ni][nj] == '#') {
            {
                int d = (curD - 1 + di.length) % di.length;
                int nii = curI + di[d];
                int njj = curJ + dj[d];
                res = Math.max(res, 1 + dfs(nii, njj, d));
            }
            {
                int d = (curD + 1 + di.length) % di.length;
                int nii = curI + di[d];
                int njj = curJ + dj[d];
                res = Math.max(res, 1 + dfs(nii, njj, d));
            }
        } else if (!out(ni, nj) && visited[ni][nj]) {
            res = 1;
        } else {
            res = 1 + dfs(ni, nj, curD);
        }
        visited[curI][curJ] = false;
        //System.out.println(curI + " " + curJ + " " + curD + " " + res);
        return res;
    }

    private boolean out(int i, int j){
        return !(0 <= i && i < grid.length && 0 <= j && j < grid.length);
    }

    private void printMatrix(Object...objects) {
        for (Object o : objects) ArrayUtils.printlnConcisely(o);
    }
}

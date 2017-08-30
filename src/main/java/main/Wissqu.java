package main;

import template.collection.sequence.ArrayUtils;
import template.collection.tuple.Tuple3;
import template.debug.InputReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/*
 ID: hackint1
 LANG: JAVA
 TASK: wissqu
*/
//WA, the count is 5 times larger

public class Wissqu {
    int n = 4;
    int[] kindCap = new int[]{3, 3, 3, 4, 3};
    int[] dr = new int[]{-1, +1, 0, 0, -1, +1, +1, -1, 0};
    int[] dc = new int[]{0, 0, -1, +1, -1, +1, -1, +1, 0};
    char[][] square = new char[n][];
    int[][][] neighborKindCnt = new int[n][n][5];
    boolean[][] visited = new boolean[n][n];
    boolean foundFirst = false;
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        for (int r = 0; r < n; ++r) {
            square[r] = in.readString().toCharArray();
            for (int c = 0; c < n; ++c) {
                char herd = square[r][c];
                for (int d = 0; d < dr.length; ++d) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];
                    if (0 <= nr && nr < n && 0 <= nc && nc < n) neighborKindCnt[nr][nc][id(herd)]++;
                }
            }
        }

        long tot = count('D', 0, new ArrayList<>(), out);
        out.println(tot / 5);
    }

    private long count(char newHeld, int locates,  ArrayList<Tuple3<Character, Integer, Integer>> acc, PrintWriter out) {
        if (locates >= n * n) {
            if (!foundFirst) {
                foundFirst = true;
                for (Tuple3 tuple3 : acc) out.println(tuple3);
            }
//            System.err.println('d');
//            for (int i = 0; i < n; ++i) ArrayUtils.printlnConcisely(square[i]);
//            for (Tuple3 tuple3 : acc) System.out.println(tuple3);

            return 1L;
        }
        if (kindCap[id(newHeld)] == 0) return 0L;

        long res = 0;
        for (int r = 0; r < n; ++r) {
            for (int c = 0; c < n; ++c) {
                if (visited[r][c]) continue;
                if (neighborKindCnt[r][c][id(newHeld)] > 0) continue;
                visited[r][c] = true;
                kindCap[id(newHeld)]--;
                char oldHerd = square[r][c];
                square[r][c] = newHeld;
                if (!foundFirst) acc.add(new Tuple3<>(newHeld, r + 1, c + 1));
                for (int d = 0; d < dr.length; ++d) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];
                    if (0 <= nr && nr < n && 0 <= nc && nc < n) {
                        neighborKindCnt[nr][nc][id(newHeld)]++;
                        neighborKindCnt[nr][nc][id(oldHerd)]--;
                    }
                }
                for (char nextHerd = 'A'; nextHerd <= 'E'; nextHerd++) res += count(nextHerd, locates + 1, acc, out);
                visited[r][c] = false;
                kindCap[id(newHeld)]++;
                square[r][c] = oldHerd;
                if (!foundFirst) acc.remove(acc.size() - 1);//?
                for (int d = 0; d < dr.length; ++d) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];
                    if (0 <= nr && nr < n && 0 <= nc && nc < n) {
                        neighborKindCnt[nr][nc][id(newHeld)]--;
                        neighborKindCnt[nr][nc][id(oldHerd)]++;
                    }
                }
            }
        }
        return res;
    }

    private int id(char herd) {return herd - 'A';}
}

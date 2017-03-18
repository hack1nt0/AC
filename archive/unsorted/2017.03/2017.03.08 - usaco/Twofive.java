package main;

import template.collection.sequence.ArrayUtils;
import template.debug.InputReader;
import java.io.PrintWriter;

/*
 ID: hackint1
 LANG: JAVA
 TASK: twofive
*/

/**
 * Executing...
 Test 1: TEST OK [0.202 secs, -1194644 KB]
 Test 2: TEST OK [0.115 secs, -1194644 KB]
 Test 3: TEST OK [0.108 secs, -1194644 KB]
 Test 4: TEST OK [0.086 secs, -1194644 KB]
 Test 5: TEST OK [0.094 secs, -1194644 KB]
 Test 6: TEST OK [0.094 secs, -1194644 KB]
 Test 7: TEST OK [0.101 secs, -1194644 KB]
 Test 8: TEST OK [0.101 secs, -1194644 KB]
 Test 9: TEST OK [0.108 secs, -1194644 KB]
 Test 10: TEST OK [0.108 secs, -1194644 KB]
 Test 11: TEST OK [0.108 secs, -1194644 KB]
 Test 12: TEST OK [0.108 secs, -1194644 KB]

 All tests OK.
 Your program ('twofive') produced all correct answers!  This is your
 submission #7 for this problem.  Congratulations!
 */

/**
 * Counting problem with constraints, That is, some states are invalid.
 *
 * Dynamic Programming with 'order' of balls (alphabetic order), not the 'order' of boxes.
 */
public class Twofive {
    long TARGET_RANK;
    String TARGET_STRING;
    int n = 5;
    int rank;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        //solve1 and solve2 are wrong.
        solve3(testNumber, in, out);
    }
    public void solve3(int testNumber, InputReader in, PrintWriter out) {
        char kind = in.readCharacter();
        if (kind == 'N') {
            long targetRank = in.readLong() - 1;
            char[][] grid = new char[n][n];
            boolean[] used = new boolean[128];
            for (int r = 0; r < n; ++r) {
                for (int c = 0; c < n; ++c) {
                    for (char letter = 'A'; letter <= 'Y'; ++letter) {
                        if (used[letter]) continue;
                        if (r > 0 && letter < grid[r - 1][c]) continue;
                        if (c > 0 && letter < grid[r][c - 1]) continue;
                        grid[r][c] = letter;
                        used[letter] = true;
                        long[][][][][] grids = new long[n + 1][n + 1][n + 1][n + 1][n + 1];
                        ArrayUtils.fill(grids, -1);
                        grids[n][n][n][n][n] = 1;
                        long howMany = gridsWithPrefix(0, 0, 0, 0, 0, grid, grids);
                        if (howMany > targetRank) break;
                        targetRank -= howMany;
                        used[letter] = false;
                    }
                }
            }
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < n; ++i) for (int j = 0; j < n; ++j) stringBuilder.append(grid[i][j]);
            out.println(stringBuilder);
        } else {
            String targetGrid = in.readString();
            char[][] grid = new char[n][n];
            boolean[] used = new boolean[128];
            long rank = 0;
            for (int r = 0; r < n; ++r) {
                for (int c = 0; c < n; ++c) {
                    for (char letter = 'A'; letter <= 'Y'; ++letter) {
                        if (used[letter]) continue;
                        int targetLetter = targetGrid.charAt(r * n + c);
                        used[letter] = true;
                        grid[r][c] = letter;
                        if (letter == targetLetter) break;

                        long[][][][][] grids = new long[n + 1][n + 1][n + 1][n + 1][n + 1];
                        ArrayUtils.fill(grids, -1);
                        grids[n][n][n][n][n] = 1;
                        long howMany = gridsWithPrefix(0, 0, 0, 0, 0, grid, grids);
                        rank += howMany;
                        used[letter] = false;
                    }
                }
            }
            out.println(rank + 1);
        }

    }

    private long gridsWithPrefix(int a, int b, int c, int d, int e, char[][] grid, long[][][][][] grids) {
        long res = grids[a][b][c][d][e];
        if (res != -1) return res;
        char curLetter = (char)(a + b + c + d + e + 'A');
        res = 0;
        if (a < n) {
            if (grid[0][a] == 0 || grid[0][a] == curLetter) res += gridsWithPrefix(a + 1, b, c, d, e, grid, grids);
        }
        if (b < a) {
            if (grid[1][b] == 0 || grid[1][b] == curLetter) res += gridsWithPrefix(a, b + 1, c, d, e, grid, grids);
        }
        if (c < b) {
            if (grid[2][c] == 0 || grid[2][c] == curLetter) res += gridsWithPrefix(a, b, c + 1, d, e, grid, grids);
        }
        if (d < c) {
            if (grid[3][d] == 0 || grid[3][d] == curLetter) res += gridsWithPrefix(a, b, c, d + 1, e, grid, grids);
        }
        if (e < d) {
            if (grid[4][e] == 0 || grid[4][e] == curLetter) res += gridsWithPrefix(a, b, c, d, e + 1, grid, grids);
        }

        grids[a][b][c][d][e] = res;
        return res;
    }



//    private String ith(long target_rank, long[][][][][] count) {
//        char[][] grid = new char[n][n];
//        int[] state = new int[n];
//        for (char i = 'A'; i <= 'Y'; ++i) {
//            boolean ok = false;
//            for (int r = 0; r < n; ++r) {
//                if (r == 0 && state[0] < n) {
//                    if (count[state[0] + 1][state[1]][state[2]][state[3]][state[4]] > target_rank) {
//                        ok = true;
//                        grid[0][state[0]++] = i;
//                        break;
//                    }
//                   target_rank -= count[state[0] + 1][state[1]][state[2]][state[3]][state[4]];
//                }
//                if (r == 1 && state[1] < state[0]) {
//                    if (count[state[0]][state[1] + 1][state[2]][state[3]][state[4]] > target_rank) {
//                        ok = true;
//                        grid[1][state[1]++] = i;
//                        break;
//                    }
//                    target_rank -= count[state[0]][state[1] + 1][state[2]][state[3]][state[4]];
//                }
//                if (r == 2 && state[2] < state[1]) {
//                    if (count[state[0]][state[1]][state[2] + 1][state[3]][state[4]] > target_rank) {
//                        ok = true;
//                        grid[2][state[2]++] = i;
//                        break;
//                    }
//                    target_rank -= count[state[0]][state[1]][state[2] + 1][state[3]][state[4]];
//                }
//                if (r == 3 && state[3] < state[2]) {
//                    if (count[state[0]][state[1]][state[2]][state[3] + 1][state[4]] > target_rank) {
//                        ok = true;
//                        grid[3][state[3]++] = i;
//                        break;
//                    }
//                    target_rank -= count[state[0]][state[1]][state[2]][state[3] + 1][state[4]];
//                }
//                if (r == 4 && state[4] < state[3]) {
//                    if (count[state[0]][state[1]][state[2]][state[3]][state[4] + 1] > target_rank) {
//                        ok = true;
//                        grid[4][state[4]++] = i;
//                        break;
//                    }
//                    target_rank -= count[state[0]][state[1]][state[2]][state[3]][state[4] + 1];
//                }
//            }
//            if (!ok) throw new RuntimeException();
//        }
//        StringBuilder stringBuilder = new StringBuilder();
//        for (int i = 0; i < n; ++i) for (int j = 0; j < n; ++j) stringBuilder.append(grid[i][j]);
//        return stringBuilder.toString();
//    }

    public void solve2(int testNumber, InputReader in, PrintWriter out) {
        char kind = in.readCharacter();
        TARGET_RANK = kind == 'N' ? in.readLong() - 1 : -1;
        TARGET_STRING = kind == 'W' ? in.readString() : null;
        char[][] grid = new char[n][n];
        long[] catalan = new long[n * n / 2 + 1];
        catalan[0] = 1;
        for (int i = 1; i < catalan.length; ++i) catalan[i] = catalan[i - 1] * 2 * (2 * (i - 1) + 1) / (i + 1);
        ArrayUtils.printlnConcisely(catalan);

        long targetRank = TARGET_RANK;
        long firstLineRank = targetRank / (catalan[10] * catalan[5] * catalan[5]);
        boolean[] used = new boolean[128];
        rank = 0;
        if (!fill2(0, 1, 0, grid, firstLineRank, used)) throw new RuntimeException();

        targetRank %= catalan[10] * catalan[5] * catalan[5];
        long secondThirdRank = targetRank / catalan[5];
        rank = 0;
        //if (!fill2(1, 3, 0, grid, secondThirdRank, used)) throw new RuntimeException();
        fill2(1, 3, 0, grid, secondThirdRank, used);
        targetRank %= catalan[5];
        long fourFiveRank = targetRank;
        rank = 0;
        //if (!fill2(3, 5, 0, grid, fourFiveRank, used)) throw new RuntimeException();
        fill2(3, 5, 0, grid, fourFiveRank, used);


        if (kind == 'N') {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < n; ++i) for (int j = 0; j < n; ++j) stringBuilder.append(grid[i][j]);
            TARGET_STRING = stringBuilder.toString();
            out.println(TARGET_STRING);
        }
        else out.println(TARGET_RANK + 1);
    }

    private boolean fill2(int r, int toR, int c, char[][] grid, long targetRank, boolean[] used) {
        if (r == toR) {
            if (rank == targetRank) return true;
            rank++;
            return false;
        }
        int nr = c == grid[r].length - 1 ? r + 1 : r;
        int nc = c == grid[r].length - 1 ? 0 : c + 1;
        for (char i = 'A'; i <= 'Y'; ++i) {
            if (used[i]) continue;
            if (!(r == 0 || i > grid[r - 1][c])) continue;
            if (!(c == 0 || i > grid[r][c - 1])) continue;

            int bigger = (n - r) * (n - c) - 1;
            char upper = (char)('Y' - bigger + 1);
            if (upper <= i) break;

            grid[r][c] = i;
            used[i] = true;
            if (fill2(nr, toR, nc, grid, targetRank, used)) return true;
            used[i] = false;
        }
        return false;
    }

    public void solve1(int testNumber, InputReader in, PrintWriter out) {
        char kind = in.readCharacter();
        TARGET_RANK = kind == 'N' ? in.readInt() - 1 : -1;
        TARGET_STRING = kind == 'W' ? in.readString() : null;
        char[][] grid = new char[n][n];
        dfs(0, 0, grid, new boolean[128], true);

        if (kind == 'N') out.println(TARGET_STRING);
        else out.println(TARGET_RANK + 1);
    }

    private boolean dfs(int r, int c, char[][] grid, boolean[] used, boolean matched) {
        if (r == n) {
//            for (int i = 0; i < n; ++i) for (int j = 0; j < n; ++j) {
//                if (!(i == 0 || grid[i][j] > grid[i - 1][j])) {
//                    throw new RuntimeException();
//                }
//                if (!(j == 0 || grid[i][j] > grid[i][j - 1])) throw new RuntimeException();
//            }

            if (TARGET_RANK != -1 && rank == TARGET_RANK) {
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < n; ++i) for (int j = 0; j < n; ++j) stringBuilder.append(grid[i][j]);
                TARGET_STRING = stringBuilder.toString();
                return true;
            }
            if (TARGET_STRING != null && matched) {
                TARGET_RANK = rank;
                return true;
            }
            rank++;
            return false;
        }
        if (r == n - 1) {
            boolean ok = true;
            for (char i = 'A', j = 0; i <= 'Y'; ++i) if (!used[i]) {
                //used[i] = true;
                if (!(r == 0 || i > grid[r - 1][j])) {ok = false; break;}
                //if (!(c == 0 || i > grid[r][c - 1])) {ok = false; break;}
                if (TARGET_STRING != null && TARGET_STRING.charAt(r * n + j) != i) matched = false;
                grid[r][j++] = i;
            }
            if (!ok) return false;
            return dfs(n, 0, grid, used, matched);
        }
        int nr = c == grid[r].length - 1 ? r + 1 : r;
        int nc = c == grid[r].length - 1 ? 0 : c + 1;
        for (char i = 'A'; i <= 'Y'; ++i) {
            if (used[i]) continue;
            if (!(r == 0 || i > grid[r - 1][c])) continue;
            if (!(c == 0 || i > grid[r][c - 1])) continue;

            int bigger = (n - r) * (n - c) - 1;
            char upper = (char)('Y' - bigger + 1);
            if (upper <= i) break;

            grid[r][c] = i;
            used[i] = true;
            if (dfs(nr, nc, grid, used, (TARGET_STRING != null && TARGET_STRING.charAt(r * n + c) != i) ? false : matched)) return true;
            used[i] = false;
        }

        return false;
    }
}

package main;

public class TreasureHunt {
    public int[] findTreasure(String[] island, String[] instructions) {
        int N = island.length;
        int M = island[0].length();
        int I = instructions.length;
        int tx, ty;
        tx = ty = -1;
        for (int y = 0; y < N; ++y) {
            for (int x = 0; x < M; ++x) {
                char c = island[y].charAt(x);
                if (c == 'X') {
                    tx = x;
                    ty = y;
                }
            }
        }
        int[][][] dp = new int[I + 1][N][M];
        int[] dirMap = new int[128];
        dirMap['N'] = 0; dirMap['S'] = 1; dirMap['W'] = 2; dirMap['E'] = 3;
        int[] dx = {0, 0, -1, +1};
        int[] dy = {-1, +1, 0, 0};
        for (int i = I; i >= 0; --i) {
            int dir, paces;
            dir = paces = -1;
            if (i < I) {
                String[] tmp = instructions[i].split(" ");
                dir = dirMap[tmp[0].charAt(0)];
                paces = Integer.parseInt(tmp[1]);
            }
            for (int y = 0; y < N; ++y) {
                for (int x = 0; x < M; ++x) {
                    int ans = 0;
                    char c = island[y].charAt(x);
                    if (c == '.') {
                        ans = -1;
                    } else if (i == I){
                        ans = dist(tx, ty, x, y);
                    } else {
                        int nx = x, ny = y;
                        boolean pathValid = true;
                        for (int p = 0; p < paces; ++p) {
                            nx += dx[dir];
                            ny += dy[dir];
                            if (0 <= nx && nx < M && 0 <= ny && ny < N && island[ny].charAt(nx) != '.')
                                continue;
                            else {
                                pathValid = false;
                                break;
                            }
                        }
                        if (pathValid)
                            ans = dp[i + 1][ny][nx];
                        else
                            ans = -1;
                    }
                    dp[i][y][x] = ans;
                }
            }
        }
        int distMin = Integer.MAX_VALUE;
        int[] start = new int[0];
        for (int y = 0; y < N; ++y) {
            for (int x = 0; x < M; ++x) {
                char c = island[y].charAt(x);
                if (c == '.')
                    continue;
                boolean beach = false;
                for (int d = 0; d < dx.length; ++d) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];
                    if (!(0 <= nx && nx < M && 0 <= ny && ny < N) || island[ny].charAt(nx) == '.')
                        beach |= true;
                }
                if (!beach)
                    continue;
                int cur = dp[0][y][x];
                if (cur == -1)
                    continue;
                if (cur < distMin) {
                    distMin = cur;
                    start = new int[]{x, y};
                }
            }
        }
        int[] ans = new int[0];
        if (start.length != 0) {
            int x = start[0], y = start[1];
            for (int i = 0; i < I; ++i) {
                int dir, paces;
                String[] tmp = instructions[i].split(" ");
                dir = dirMap[tmp[0].charAt(0)];
                paces = Integer.parseInt(tmp[1]);
                x += dx[dir] * paces;
                y += dy[dir] * paces;
            }
            ans = new int[] {x, y};
        }
        return ans;
    }

    int dist(int tx, int ty, int x, int y) {
        return (tx - x) * (tx - x) + (ty - y) * (ty - y);
    }
}

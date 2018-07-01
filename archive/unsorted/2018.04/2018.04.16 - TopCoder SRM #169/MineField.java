package main;

public class MineField {
    public String[] getMineField(String mineLocations) {
        int n = 9;
        int[][] map = new int[n][n];
        int[] dr = {-1, +1, 0, 0, -1, -1, +1, +1};
        int[] dc = {0, 0, -1, +1, -1, +1, +1, -1};
        for (int i = 0; i < mineLocations.length();) {
            int r = mineLocations.charAt(i + 1) - '0';
            int c = mineLocations.charAt(i + 3) - '0';
            map[r][c] = -1;
            for (int d = 0; d < dr.length; ++d) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                if (0 <= nr && nr < n && 0 <= nc && nc < n && map[nr][nc] != -1)
                    ++map[nr][nc];
            }
            i += 5;
        }
        String[] ans = new String[n];
        for (int i = 0; i < n; ++i) {
            ans[i] = "";
            for (int j = 0; j < n; ++j)
                ans[i] += map[i][j] == -1 ? "M" : map[i][j];
        }
        return ans;
    }
}

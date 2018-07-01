package main;

public class SquareCode {
    public String[] completeIt(String[] grid) {
        int N = grid.length;
        int[][] count = new int[N][N];
        for (int i = 0; i < N; ++i)
            for (int j = 0; j < N; ++j)
                if (grid[i].charAt(j) == '.')
                    count[i][j] = 1;
        int[][] rotated = count;
        for (int t = 0; t < 3; ++t) {
            int[][] tmp = new int[N][N];
            for (int i = 0; i < N; ++i)
                for (int j = 0; j < N; ++j)
                    tmp[j][N - i - 1] = rotated[i][j];
            for (int i = 0; i < N; ++i)
                for (int j = 0; j < N; ++j)
                    count[i][j] += tmp[i][j];
            rotated = tmp;
        }
        for (int i = 0; i < N; ++i)
            for (int j = 0; j < N; ++j)
                if (count[i][j] > 1)
                    return new String[0];
        if (N % 2 == 1)
            throw new RuntimeException();
        char[][] ans = new char[N][N];
        for (int i = 0; i < N; ++i)
            ans[i] = grid[i].toCharArray();
        for (int i = 0; i < N / 2; ++i) {
            for (int offset = i; offset < i + (N - 2 * i) / 2; ++offset) {
                if (count[offset][i] == 0)
                    ans[offset][i] = '.';
                if (count[i][offset] == 0)
                    ans[i][offset] = '.';
            }
        }
        String[] ans2 = new String[N];
        for (int i = 0; i < N; ++i)
            ans2[i] = new String(ans[i]);
        return ans2;
    }
}

package main;

import java.util.Arrays;

public class Target {
    public String[] draw(int n) {
        char[][] target = new char[n][n];
        for (int i = 0; i < n; ++i)
            Arrays.fill(target[i], ' ');
        for (int startRow = 0, startCol = 0, len = n; len > 0; startRow += 2, startCol +=2, len -= 4) {
            for (int r = startRow; r < startRow + len; ++r)
                target[r][startCol] = target[r][startCol + len - 1] = '#';
            for (int c = startCol; c < startCol + len; ++c)
                target[startRow][c] = target[startRow + len - 1][c] = '#';
        }
        String[] ans = new String[n];
        for (int i = 0; i < n; ++i)
            ans[i] = new String(target[i]);
        return ans;
    }
}

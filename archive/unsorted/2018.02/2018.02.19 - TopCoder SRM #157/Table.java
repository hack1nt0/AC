package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Table {
    public String[] layout(String[] tbl) {
        int N = 50;
        char[][] mat = new char[N][N];
        for (int i = 0; i < N; ++i) Arrays.fill(mat[i], ' ');
        int ncol = 0;
        for (int i = 0; i < tbl.length; ++i) {
            if (tbl[i].length() == 0) continue;
            String[] cells = tbl[i].substring(1, tbl[i].length() - 1).split("\\)\\(");
            for (int j = 0; j < cells.length; ++j) {
                String[] tri = cells[j].split(",");
                int colSpan = Integer.parseInt(tri[0]);
                if (i == 0) ncol += colSpan;
                int rowSpan = Integer.parseInt(tri[1]);
                char content = tri[2].charAt(0);
                for (int r = i; r < N; ++r) {
                    boolean found = false;
                    for (int c = 0; c < (i == 0 ? N : ncol); ++c)
                        if (mat[r][c] == ' ') {
                            for (int rr = r; rr < r + rowSpan; ++rr)
                                for (int cc = c; cc < c + colSpan; ++cc)
                                    mat[rr][cc] = content;
                            found = true;
                            break;
                        }
                    if (found) break;
                }
            }
        }
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < N; ++i) {
            String r = new String(mat[i]).trim();
            if (r.length() == 0) break;
            ans.add(r);
        }
        return ans.toArray(new String[0]);
    }
}

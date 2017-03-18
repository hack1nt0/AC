package main;

import scala.Array;
import template.collection.sequence.ArrayUtils;
import template.string.StringUtils;

import java.util.*;
import java.io.PrintWriter;

/*
 ID: hackint1
 LANG: JAVA
 TASK: frameup
*/

public class Frameup {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        char[][] picture = new char[n][m];
        for (int i = 0; i < n; ++i) picture[i] = in.next().toCharArray();
        boolean[] existed = new boolean['Z' + 1];
        int[] xfrom = new int['Z' + 1];
        Arrays.fill(xfrom, Integer.MAX_VALUE);
        int[] xto = new int['Z' + 1];
        Arrays.fill(xto, -1);
        int[] yfrom = new int['Z' + 1];
        Arrays.fill(yfrom, Integer.MAX_VALUE);
        int[] yto = new int['Z' + 1];
        Arrays.fill(yto, -1);
        int nframe = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                char c = picture[i][j];
                if (!existed[c]) {
                    if (c != '.') nframe++;
                    existed[c] = true;
                }
                xfrom[c] = Math.min(xfrom[c], j);
                xto[c] = Math.max(xto[c], j);
                yfrom[c] = Math.min(yfrom[c], i);
                yto[c] = Math.max(yto[c], i);
            }
        }
        boolean[] used = new boolean['Z' + 1];
        List<String> ans = new ArrayList<>();
        dfs(0, nframe, xfrom, xto, yfrom, yto, used, new StringBuilder(), ans, picture, existed, out);
        String[] ansArr = ans.toArray(new String[0]);
        StringUtils.sortMSDWay3(ansArr);
        for (String s : ansArr) out.println(s);
    }

    private void dfs(int cur, int nframe, int[] xfrom, int[] xto, int[] yfrom, int[] yto, boolean[] used, StringBuilder acc, List<String> ans, char[][] picture, boolean[] existed, PrintWriter out) {
        if (cur >= nframe) {
            ans.add(StringUtils.reverse(acc.toString()));
            return;
        }

        for (char frame = 'Z'; frame >= 'A'; --frame) {
            if (!existed[frame] || used[frame]) continue;
            boolean ok = true;
            for (int i = yfrom[frame]; i <= yto[frame]; ++i) {
                char occupied = picture[i][xfrom[frame]];
                if (occupied != frame && !used[occupied]) ok = false;
            }
            for (int i = yfrom[frame]; i <= yto[frame]; ++i) {
                char occupied = picture[i][xto[frame]];
                if (occupied != frame && !used[occupied]) ok = false;
            }
            for (int j = xfrom[frame]; j <= xto[frame]; ++j) {
                char occupied = picture[yfrom[frame]][j];
                if (occupied != frame && !used[occupied]) ok = false;
            }
            for (int j = xfrom[frame]; j <= xto[frame]; ++j) {
                char occupied = picture[yto[frame]][j];
                if (occupied != frame && !used[occupied]) ok = false;
            }
            if (ok) {
                used[frame] = true;
                acc.append(frame);
                dfs(cur + 1, nframe, xfrom, xto, yfrom, yto, used, acc, ans, picture, existed, out);
                used[frame] = false;
                acc.setLength(acc.length() - 1);
            }
        }
    }
}

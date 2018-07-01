package main;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestCase;

import java.io.PrintWriter;
import java.util.*;

public class DescendingInTheDarkTestCase {
    @TestCase
    public Collection<Test> createTests() {
        int T = 10;
        return Collections.emptyList();
    }

    static class Segment {
        int len;
        long goodExitMask;
        long badExitMask;
    }

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int rows = in.nextInt();
        int cols = in.nextInt();
        String[] field = new String[rows];
        for (int r = 0; r < rows; ++r) {
            field[r] = in.next();
            if (field[r].length() != cols) throw new RuntimeException();
        }
        System.out.println("Case #" + (testNumber + 1) + ":");
        for (char caveId = '0'; caveId <= '9'; ++caveId) {
            int cr = -1;
            int cc = -1;
            for (int r = 0; r < rows; ++r)
                for (int c = 0; c < cols; ++c)
                    if (field[r].charAt(c) == caveId) {
                        cr = r;
                        cc = c;
                    }
            if (cr < 0) continue;
            boolean[][] reach = new boolean[rows][cols];
            reach[cr][cc] = true;
            int nc = 1;
            while (true) {
                boolean updated = false;
                for (int r = 0; r < rows; ++r)
                    for (int c = 0; c < cols; ++c)
                        if (reach[r][c]) {
                            if (r > 0 && field[r - 1].charAt(c) != '#' && !reach[r - 1][c]) {
                                reach[r - 1][c] = true;
                                ++nc;
                                updated = true;
                            }
                            if (c > 0 && field[r].charAt(c - 1) != '#' && !reach[r][c - 1]) {
                                reach[r][c - 1] = true;
                                ++nc;
                                updated = true;
                            }
                            if (c + 1 < cols && field[r].charAt(c + 1) != '#' && !reach[r][c + 1]) {
                                reach[r][c + 1] = true;
                                ++nc;
                                updated = true;
                            }
                        }
                if (!updated) break;
            }
            List<Segment> segments = new ArrayList<Segment>();
            for (int r = 0; r <= cr; ++r)
                for (int c = 0; c < cols; ++c)
                    if (reach[r][c] && (c == 0 || !reach[r][c - 1])) {
                        int c1 = c;
                        while (reach[r][c1 + 1]) ++c1;
                        Segment s = new Segment();
                        s.len = c1 - c + 1;
                        for (int pos = c; pos <= c1; ++pos) {
                            if (r + 1 < rows && field[r + 1].charAt(pos) != '#') {
                                if (reach[r + 1][pos])
                                    s.goodExitMask |= 1L << (pos - c);
                                else
                                    s.badExitMask |= 1L << (pos - c);
                            }
                        }
                        segments.add(s);
                    }
            while (true) {
                int maxLen = 0;
                for (Segment s : segments)
                    maxLen = Math.max(maxLen, s.len);
                long[] badByLen = new long[maxLen + 1];
                for (Segment s : segments) {
                    badByLen[s.len] |= s.badExitMask;
                }
                long[] possible = new long[maxLen + 1];
                possible[1] = 1;
                for (int len = 1; len <= maxLen; ++len) {
                    possible[len] &= ~badByLen[len];
                    if (len < maxLen) {
                        possible[len + 1] = possible[len] | (possible[len] << 1);
                    }
                }
                for (int len = maxLen; len > 1; --len) {
                    possible[len - 1] &= possible[len] | (possible[len] >> 1);
                }
                List<Segment> remaining = new ArrayList<Segment>();
                for (Segment s : segments)
                    if ((s.goodExitMask & possible[s.len]) == 0) {
                        remaining.add(s);
                    }
                if (remaining.size() == segments.size()) break;
                segments = remaining;
            }
            System.out.println(caveId + ": " + nc + " " + (segments.size() == 1 ? "Lucky" : "Unlucky"));

        }
    }
}

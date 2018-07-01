package main;



import template.collection.intervals.Interval;

import java.util.*;
import java.io.PrintWriter;

public class DescendingInTheDark {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int nrow = in.nextInt();
        int ncol = in.nextInt();
        char[][] grid = new char[nrow][ncol];
        for (int i = 0; i < nrow; ++i)
            grid[i] = in.next().toCharArray();
        int[] dr = {-1, +1, 0, 0};
        int[] dc = {0, 0, -1, +1};
        class Cave {
            int id, starts = 1;
            boolean lucky;

            @Override
            public String toString() {
                return "" + id + ": " + starts + " " + (lucky ? "Lucky" : "Unlucky");
            }
        }
        List<Cave> caves = new ArrayList<Cave>();
        for (int r = 0; r < nrow; ++r) {
            for (int c = 0; c < ncol; ++c) {
                if (Character.isDigit(grid[r][c])) {
                    Cave cave = new Cave();
                    cave.id = grid[r][c] - '0';
                    boolean[][] visited = new boolean[nrow][ncol];
                    Queue<Integer> queue = new LinkedList<>();
                    queue.add(r * ncol + c);
                    visited[r][c] = true;
                    while (queue.size() > 0) {
                        int cr = queue.peek() / ncol;
                        int cc = queue.poll() % ncol;
                        for (int d = 0; d < 4; ++d) {
                            if (d == 1) continue;
                            int nr = cr + dr[d];
                            int nc = cc + dc[d];
                            if (grid[nr][nc] != '#' && !visited[nr][nc]) {
                                visited[nr][nc] = true;
                                cave.starts++;
                                queue.add(nr * ncol + nc);
                            }
                        }
                    }
                    List<List<Interval>> intervals = new ArrayList<>();
                    for (int cr = 1; cr <= r; ++cr) {
                        List<Interval> ci = new ArrayList<>();
                        int cc = 0;
                        while (cc < ncol) {
                            while (cc < ncol && !visited[cr][cc]) cc++;
                            int s = cc;
                            while (cc < ncol && visited[cr][cc]) cc++;
                            int t = cc;
                            if (cc < ncol)
                                ci.add(new Interval(s, t));
                        }
                        if (ci.size() > 0) intervals.add(ci);
                    }
                    Set<List<Long>> cache = new HashSet<>();
                    List<Long> state = new ArrayList<>();
                    for (int i = 0; i < intervals.size(); ++i) state.add((1L << intervals.get(i).size()) - 1);
                    cave.lucky = dfs(state, cache, intervals, visited);
                    caves.add(cave);
                }
            }
        }
        Collections.sort(caves, new Comparator<Cave>() {
            @Override
            public int compare(Cave o1, Cave o2) {
                return o1.id - o2.id;
            }
        });
        out.println("Case #" + testNumber + ":");
        for (int i = 0; i < caves.size(); ++i)
            out.println(caves.get(i));
    }

    private boolean dfs(List<Long> state, Set<List<Long>> cache, List<List<Interval>> intervals, boolean[][] valid) {
        if (state.size() == 1)
            return true;
        if (cache.contains(state))
            return false;
        throw new RuntimeException();
    }
}

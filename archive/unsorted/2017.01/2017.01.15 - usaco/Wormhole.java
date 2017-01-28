package main;

import template.collection.sequence.ArrayUtils;

import java.util.*;
import java.io.PrintWriter;
/*
 ID: hackint1
 LANG: JAVA
 TASK: wormhole
*/

public class Wormhole {
    private class Point {
        int x, y;
        Point right, connect;
        int id;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Point point = (Point) o;

            if (x != point.x) return false;
            return y == point.y;

        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }
    }
    int ans;
    List<Point> points;
    int N;
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        N = in.nextInt();
        points = new ArrayList<>();
        //List<LinesToGraph.Seg> ps = new ArrayList<>();
        for (int i = 0; i < N; ++i) {
            int x = in.nextInt();
            int y = in.nextInt();
            points.add(new Point(x, y));
        }
        //LinesToGraph.zip(ps, true, false);
        Collections.sort(points, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                if (o1.y != o2.y) return o1.y - o2.y;
                return o1.x - o2.x;
            }
        });
        for (int i = 0; i < points.size(); ++i) {
            points.get(i).id = i;
            if (i + 1 < points.size() && points.get(i + 1).y == points.get(i).y)
                points.get(i).right = points.get(i + 1);
        }

        boolean[] paired = new boolean[N];
        dfs(0, paired);

        out.println(ans);
    }

    private void dfs(int cur, boolean[] paired) {
        if (cur >= N) {
            if (cyclable()) {
                ans++;
            }
            return;
        }
        if (paired[cur]) {
            dfs(cur + 1, paired);
            return;
        }
        paired[cur] = true;
        for (int i = cur + 1; i < N; ++i) {
            if (paired[i]) continue;
            paired[i] = true;
            points.get(cur).connect = points.get(i);
            points.get(i).connect = points.get(cur);
            dfs(cur + 1, paired);
            paired[i] = false;
        }
        paired[cur] = false;
    }

    private boolean cyclable() {
        int[][] vis = new int[N][2];
        for (int i = 0; i < points.size(); ++i) {
            //assert vis[i] != -1;
            //if (vis[i] == 1) continue;
            if (cyclable(i, 0, vis)) return true;
            ArrayUtils.fill(vis, 0);
        }
        return false;
    }

    private boolean cyclable(int cur, int way, int[][] vis) {
        if (vis[cur][way] == -1) return true;
        vis[cur][way] = -1;
        Point curNode = points.get(cur);
        boolean res = false;
        if (way == 1) {
            if (curNode.right == null)
                res |= false;
            else
                res |= cyclable(curNode.right.id, 0, vis);
        } else if (way == 0) {
            res |= cyclable(curNode.connect.id, 1, vis);
        }
        vis[cur][way] = 1;
        return res;
    }
}

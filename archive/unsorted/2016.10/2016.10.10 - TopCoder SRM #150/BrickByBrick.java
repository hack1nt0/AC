package main;

import java.util.*;

public class BrickByBrick {

    Point[] points;

    public int timeToClear(String[] map) {
        int N = map.length;
        int M = map[0].length();
        int MAXPN = N * (M + 1) + M * (N + 1);
        points = new Point[MAXPN];
        for (int i = 0; i < points.length; ++i) points[i] = new Point(i);
        for (int i = 0; i < N; ++i)
            for (int j = 0; j < M; ++j) {
                char c = map[i].charAt(j);
                if (c == '#') continue;
                int up = i * M + j;
                int down = (i + 1) * M + j;
                int left = M * (N + 1) + i * (M + 1) + j;
                int right = left + 1;
                addEdge(up, left);
                addEdge(up, right);
                addEdge(left, down);
                addEdge(right, down);
                if (c == 'B') {
                    points[up].block = true;
                    points[down].block = true;
                    points[left].block = true;
                    points[right].block = true;
                }
            }
        return 0;
    }

    public void addEdge(int u, int v) {
        points[u].adj.add(points[v]);
        points[v].adj.add(points[u]);
    }

}

class Point implements Comparable<Point>{
    int id;
    List<Point> adj = new ArrayList<Point>();
    boolean block;
    int[] nxt = new int[]{1, 0, 3, 2};
    int[] nxt1 = new int[]{3, 2, 1, 0};

    public Point(int id) {
        this.id = id;
    }

    public Point to(Point pre) {
        Collections.sort(adj);


        for (int i = 0; i < adj.size(); ++i) {
            if (adj.get(i) != pre) continue;
            if (block) return adj.get(nxt[i]);
            else return adj.get(nxt1[i]);
        }
        return null;
    }

    @Override
    public int compareTo(Point o) {
        return id - o.id;
    }
}

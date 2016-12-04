package template;

import java.util.*;

/**
 * Created by dy on 16-10-1.
 */
public class LinesToGraph {
    public static class Point implements Comparable<Point> {
        int x, y;
        int id = -1;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int dot(Point o) {
            return x * o.x + y * o.y;
        }

        public Point minus(Point o) {
            return new Point(x - o.x, y - o.y);
        }

        public double dist(Point o) {
            return Math.sqrt((x - o.x) * (x - o.x) + (y - o.y) * (y - o.y));
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

        @Override
        public int compareTo(Point o) {
            if (x != o.x) return x - o.x;
            if (y != o.y) return y - o.y;
            return 0;
        }

        @Override
        public String toString() {
            return "(" + x + "," + y + ")";
        }
    }

    public static class Seg {
        Point a, b;
        int id;
        List<Point> ips;

        public Seg(Point a, Point b) {
            this.a = a.compareTo(b) < 0 ? a : b;
            this.b = a.compareTo(b) > 0 ? a : b;
            ips = new ArrayList<Point>();
            ips.add(a);
            ips.add(b);
        }

        public boolean overlap(Seg o) {
            if (b.minus(a).dot(o.b.minus(o.a)) == 0)
                return false;

            if (a.y == b.y & b.y == o.a.y || a.x == b.x & b.x == o.a.x)
                return !(o.b.compareTo(a) < 0 || b.compareTo(o.a) < 0);

            return false;
        }

        public Seg cont(Seg o) {
            Point na = a.compareTo(o.a) < 0 ? a : o.a;
            Point nb = b.compareTo(o.b) > 0 ? b : o.b;
            return new Seg(na, nb);
        }

        public boolean interact(Seg o) {
            if (b.minus(a).dot(o.b.minus(o.a)) != 0)
                return false;

            return !(o.b.x < a.x || b.x < o.a.x) && !(o.b.y < a.y || b.y < o.a.y);
        }

        public Point intpoint(Seg o) {
            int[] xs = new int[]{a.x, b.x, o.a.x, o.b.x};
            int[] ys = new int[]{a.y, b.y, o.a.y, o.b.y};
            Arrays.sort(xs);
            Arrays.sort(ys);
            return new Point(xs[1], ys[1]);
        }

        public int addIp(Point p) {
            if (p.equals(a) || p.equals(b)) return -1;
            ips.add(p);
            return 1;
        }
    }

    public static class Edge {
        int to; double c;

        public Edge(int to, double c) {
            this.to = to;
            this.c = c;
        }
    }

    public static List<Edge>[] transfer(List<Seg> segs) {

        for (int i = 0; i < segs.size(); ++i)
            for (int j = i + 1; j < segs.size(); ++j) {
                Seg sa = segs.get(i);
                Seg sb = segs.get(j);
                if (!sa.interact(sb)) continue;
                Point ip = sa.intpoint(sb);
                sa.addIp(ip);
                sb.addIp(ip);
            }
        Map<Point, List<Point>> madj = new HashMap<Point, List<Point>>();
        for (int i = 0; i < segs.size(); ++i) {
            List<Point> ips = segs.get(i).ips;
            Collections.sort(ips);
            for (int j = 0; j < ips.size(); ++j) {
                Point p = ips.get(j);
                if (!madj.containsKey(p)) madj.put(p, new ArrayList<Point>());
                if (j - 1 >= 0) madj.get(p).add(ips.get(j - 1));
                if (j + 1 < ips.size()) madj.get(p).add(ips.get(j + 1));
            }
        }

        int N = madj.size();
        List<Edge>[] adj = new ArrayList[N];
        for (int i = 0; i < adj.length; ++i) adj[i] = new ArrayList<Edge>();
        int cnt = 0;
        for (Point p : madj.keySet()) p.id = cnt++;
        for (Point p : madj.keySet())
            for (Point chd : madj.get(p)) adj[p.id].add(new Edge(chd.id, p.dist(chd)));

        return adj;
    }

    public static boolean[][] zip(List<Seg> segs1, boolean print, boolean sideffect) {
        int W, H;

        double EPS = 1e-9;

        List<Seg> segs = segs1;
        if (!sideffect) segs = new ArrayList<Seg>(segs1);

        Set<Double> txs = new TreeSet<Double>();
        for (Seg seg : segs) {
            txs.add((double) seg.a.x);
            txs.add(seg.a.x - EPS);
            txs.add(seg.a.x + EPS);
            txs.add((double) seg.b.x);
            txs.add(seg.b.x - EPS);
            txs.add(seg.b.x + EPS);
        }
        Double[] xs = txs.toArray(new Double[0]);
        W = xs.length;
        for (Seg seg : segs) {
            if (seg.a.x != xs[Arrays.binarySearch(xs, (double) seg.a.x)])
                throw new RuntimeException();
            seg.a.x = Arrays.binarySearch(xs, (double) seg.a.x);
            seg.b.x = Arrays.binarySearch(xs, (double) seg.b.x);
        }

        Set<Double> tys = new TreeSet<Double>();
        for (Seg seg : segs) {
            tys.add((double) seg.a.y);
            tys.add(seg.a.y - EPS);
            tys.add(seg.a.y + EPS);
            tys.add((double) seg.b.y);
            tys.add(seg.b.y - EPS);
            tys.add(seg.b.y + EPS);
        }
        Double[] ys = tys.toArray(new Double[0]);
        H = ys.length;
        for (Seg seg : segs) {
            seg.a.y = Arrays.binarySearch(ys, (double) seg.a.y);
            seg.b.y = Arrays.binarySearch(ys, (double) seg.b.y);
        }

        boolean[][] M = new boolean[H][W];
        for (Seg seg : segs)
            for (int i = seg.a.y; i <= seg.b.y; ++i)
                for (int j = seg.a.x; j <= seg.b.x; ++j) M[i][j] = true;

        if (print) {
            char[][] m = new char[H][W];
            for (int k = 0; k < segs.size(); ++k) {
                Seg seg = segs.get(k);
                for (int i = seg.a.y; i <= seg.b.y; ++i)
                    for (int j = seg.a.x; j <= seg.b.x; ++j) m[i][j] = (char) ('A' + k);
            }

            for (int i = H - 1; i >= 0; --i) {
                for (int j = 0; j < W; ++j) System.out.print(m[i][j] != 0 ? m[i][j] : "-");
                System.out.println();
            }
        }

        return M;
    }
}

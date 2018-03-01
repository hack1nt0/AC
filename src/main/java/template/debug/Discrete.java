package template.debug;

import template.geometry.LineSegment;
import template.geometry.Point;

import java.util.*;

/**
 * Created by dy on 16-10-1.
 */
public class Discrete {
//    public static class Point implements Comparable<Point> {
//        double x, y;
//        int id = -1;
//
//        public Point(double x, double y) {
//            this.x = x;
//            this.y = y;
//        }
//        public double dot(Point o) {
//            return x * o.x + y * o.y;
//        }
//
//        public Point minus(Point o) {
//            return new Point(x - o.x, y - o.y);
//        }
//
//        public double dist(Point o) {
//            return Math.sqrt((x - o.x) * (x - o.x) + (y - o.y) * (y - o.y));
//        }
//
//
//        @Override
//        public boolean equals(Object o) {
//            if (this == o) return true;
//            if (o == null || getClass() != o.getClass()) return false;
//
//            Point point = (Point) o;
//
//            if (x != point.x) return false;
//            return y == point.y;
//
//        }
//
//        @Override
//        public int hashCode() {
//            int result;
//            long temp;
//            temp = Double.doubleToLongBits(x);
//            result = (int) (temp ^ (temp >>> 32));
//            temp = Double.doubleToLongBits(y);
//            result = 31 * result + (int) (temp ^ (temp >>> 32));
//            return result;
//        }
//
//        @Override
//        public String toString() {
//            return "[" + x + "," + y + "]";
//        }
//    }
//
//    public static class Seg {
//        Point s, t;
//        int id;
//        List<Point> ips;
//
//        public Seg(Point s, Point t) {
//            this.s = s.compareTo(t) < 0 ? s : t;
//            this.t = s.compareTo(t) > 0 ? s : t;
//            ips = new ArrayList<Point>();
//            ips.add(s);
//            ips.add(t);
//        }
//
//        public boolean overlap(Seg o) {
//            if (t.minus(s).dot(o.t.minus(o.s)) == 0)
//                return false;
//
//            if (s.y == t.y & t.y == o.s.y || s.x == t.x & t.x == o.s.x)
//                return !(o.t.compareTo(s) < 0 || t.compareTo(o.s) < 0);
//
//            return false;
//        }
//
//        public Seg cont(Seg o) {
//            Point na = s.compareTo(o.s) < 0 ? s : o.s;
//            Point nb = t.compareTo(o.t) > 0 ? t : o.t;
//            return new Seg(na, nb);
//        }
//
//        public boolean interact(Seg o) {
//            if (t.minus(s).dot(o.t.minus(o.s)) != 0)
//                return false;
//
//            return !(o.t.x < s.x || t.x < o.s.x) && !(o.t.y < s.y || t.y < o.s.y);
//        }
//
//        public Point intpoint(Seg o) {
//            double[] xs = new double[]{s.x, t.x, o.s.x, o.t.x};
//            double[] ys = new double[]{s.y, t.y, o.s.y, o.t.y};
//            Arrays.sort(xs);
//            Arrays.sort(ys);
//            return new Point(xs[1], ys[1]);
//        }
//
//        public int addIp(Point p) {
//            if (p.equals(s) || p.equals(t)) return -1;
//            ips.add(p);
//            return 1;
//        }
//    }
//
//    public static class AbstractEdge {
//        int t; double c;
//
//        public AbstractEdge(int t, double c) {
//            this.t = t;
//            this.c = c;
//        }
//    }
//
//    public static List<AbstractEdge>[] transfer(List<Seg> segs) {
//
//        for (int i = 0; i < segs.capacity(); ++i)
//            for (int j = i + 1; j < segs.capacity(); ++j) {
//                Seg sa = segs.get(i);
//                Seg sb = segs.get(j);
//                if (!sa.interact(sb)) continue;
//                Point ip = sa.intpoint(sb);
//                sa.addIp(ip);
//                sb.addIp(ip);
//            }
//        Map<Point, List<Point>> madj = new HashMap<Point, List<Point>>();
//        for (int i = 0; i < segs.capacity(); ++i) {
//            List<Point> ips = segs.get(i).ips;
//            Collections.sort(ips);
//            for (int j = 0; j < ips.capacity(); ++j) {
//                Point p = ips.get(j);
//                if (!madj.containsKey(p)) madj.put(p, new ArrayList<Point>());
//                if (j - 1 >= 0) madj.get(p).add(ips.get(j - 1));
//                if (j + 1 < ips.capacity()) madj.get(p).add(ips.get(j + 1));
//            }
//        }
//
//        int N = madj.capacity();
//        List<AbstractEdge>[] adj = new ArrayList[N];
//        for (int i = 0; i < adj.length; ++i) adj[i] = new ArrayList<AbstractEdge>();
//        int cnt = 0;
//        for (Point p : madj.keySet()) p.id = cnt++;
//        for (Point p : madj.keySet())
//            for (Point chd : madj.get(p)) adj[p.id].add(new AbstractEdge(chd.id, p.dist(chd)));
//
//        return adj;
//    }

    /**
     *
     * Zip the continual infinite geometry space t discrete finite one.
     * Only applied t horizontal and vertical line segments.
     * @param segs
     * @param print
     * @return
     */
    public static boolean[][] zip(List<LineSegment> segs, boolean print) {
        int W, H;

        double EPS = 1e-9;

        Set<Double> txs = new TreeSet<Double>();
        for (LineSegment seg : segs) {
            txs.add(seg.a.x);
            txs.add(seg.a.x - EPS);
            txs.add(seg.a.x + EPS);
            txs.add(seg.b.x);
            txs.add(seg.b.x - EPS);
            txs.add(seg.b.x + EPS);
        }
        Double[] xs = txs.toArray(new Double[0]);
        W = xs.length;
        Set<Double> tys = new TreeSet<Double>();
        for (LineSegment seg : segs) {
            tys.add(seg.a.y);
            tys.add(seg.a.y - EPS);
            tys.add(seg.a.y + EPS);
            tys.add(seg.b.y);
            tys.add(seg.b.y - EPS);
            tys.add(seg.b.y + EPS);
        }
        Double[] ys = tys.toArray(new Double[0]);
        H = ys.length;
        List<LineSegment> zippedSegs = new ArrayList<>();
        for (LineSegment seg : segs) {
            if (seg.a.x != xs[Arrays.binarySearch(xs, seg.a.x)])
                throw new RuntimeException();
            double ax = Arrays.binarySearch(xs, seg.a.x);
            double bx = Arrays.binarySearch(xs, seg.b.x);
            double ay = Arrays.binarySearch(ys, seg.a.y);
            double by = Arrays.binarySearch(ys, seg.b.y);
            zippedSegs.add(new LineSegment(new Point(ax, ay), new Point(bx, by)));
        }

        boolean[][] M = new boolean[H][W];
        for (LineSegment seg : zippedSegs)
            for (int i = (int)seg.a.y; i <= (int)seg.b.y; ++i)
                for (int j = (int)seg.a.x; j <= (int)seg.b.x; ++j) M[i][j] = true;

        if (print) {
            char[][] m = new char[H][W];
            for (int k = 0; k < zippedSegs.size(); ++k) {
                LineSegment seg = zippedSegs.get(k);
                for (int i = (int)seg.a.y; i <= (int)seg.b.y; ++i)
                    for (int j = (int)seg.a.x; j <= (int)seg.b.x; ++j) m[i][j] = (char) ('A' + k);
            }

            for (int i = H - 1; i >= 0; --i) {
                for (int j = 0; j < W; ++j) System.out.print(m[i][j] != 0 ? m[i][j] : "-");
                System.out.println();
            }
        }

        return M;
    }
}

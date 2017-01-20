import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.*;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 *
 * @author DY
 */
public class Main2 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        double t1 = System.currentTimeMillis();
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        final Scanner in = new Scanner(inputStream);
        final PrintWriter out = new PrintWriter(outputStream);
        int testCount = Integer.parseInt(in.next());
        ExecutorService executor = Executors.newFixedThreadPool(40);
        List<Callable<Long>> tasks = new ArrayList<Callable<Long>>();
        for (int i = 1; i <= testCount; i++) {
            final SavitaAndFriends solver = new SavitaAndFriends();

            final int finalI = i;
            Callable<Long> curTask = new Callable<Long>() {
                public Long call() throws Exception {
                    solver.solve(finalI, in, out);
                    return null;
                }
            };
            tasks.add(curTask);
        }
        for (Future<Long> task : executor.invokeAll(tasks)) task.get();
        executor.shutdown();
        out.close();
        double t2 = System.currentTimeMillis();
        System.err.println("Time consumed: " + (t2 - t1) / 1000 + "s");
    }

    static class SavitaAndFriends {
        static int whichTestCanRun1 = 1;
        static int whichTestCanRun2 = 1;

        public void solve(int testNumber, Scanner in, PrintWriter out) {
            //synchronized (this) {
            {
                int wc = 0;
                while (true) {
                    //System.err.println(testNumber + " waiting " + whichTestCanRun1);
                    wc++;
                    if (whichTestCanRun1 != testNumber) {
//                        try {
//                            Thread.sleep(Math.max(testNumber - wc, 1) * 15);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
                        Thread.yield();
                        continue;
                    }
                    break;
                }
            }
            //}

            int N = in.nextInt();
            int M = in.nextInt();
            int K = in.nextInt();
            int maxC = 0;
            int A, B, dAB;
            A = B = dAB = -1;
            ShortestPath sp = new ShortestPath(N);
            for (int i = 1; i <= M; ++i) {
                int a = in.nextInt() - 1;
                int b = in.nextInt() - 1;
                int c = in.nextInt();
                maxC = Math.max(maxC, c);
                if (i == K) {
                    A = a;
                    B = b;
                    dAB = c;
                }
                sp.addE(a, b, c);
                sp.addE(b, a, c);
            }

            //synchronized (this)
            {
                whichTestCanRun1++;
            }

            long[] da = sp.shortestPath(A);
            long[] db = sp.shortestPath(B);
            double L = 0, R = Math.max(Seqs.max(da), Seqs.max(db));
            int niter = 0;
            List<Interval1D> validps = new ArrayList<Interval1D>();
            while (true) {
                niter++;
                if (niter > 100) break;
                double mid = (L + R) / 2;
                boolean ok = true;

                List<Interval1D> interval1Ds = new ArrayList<Interval1D>();
                //interval1Ds.clear();
                interval1Ds.add(new Interval1D(0, dAB));
                for (int i = 0; i < N; ++i) {
                    List<Interval1D> ninterval1Ds = new ArrayList<Interval1D>();
                    double t = mid - da[i];
                    if (t >= 0) {
                        Interval1D a = new Interval1D(0, t);
                        for (Interval1D it : interval1Ds)
                            if (a.intersects(it))
                                ninterval1Ds.add(Intervals.intersects(a, it));
                    }
                    t = dAB - (mid - db[i]);
                    if (t <= dAB) {
                        Interval1D a = new Interval1D(t, dAB);
                        for (Interval1D it : interval1Ds)
                            if (a.intersects(it))
                                ninterval1Ds.add(Intervals.intersects(a, it));
                    }

                    interval1Ds = join(ninterval1Ds);
                    if (interval1Ds.size() == 0) {
                        ok = false;
                        break;
                    }

                    //System.err.println(interval1Ds.size());
                }

                if (ok) {
                    R = mid;
                    validps = interval1Ds;
                } else L = mid;

            }
            //out.println(interval1Ds.get(0).left() + " " + R);
            //System.err.printf("%.5f %.5f\n", validps.get(0).left(), R);

            //System.err.println("testNumber: " + testNumber);
            System.err.printf("testNumber %d : %.5f %.5f\n", testNumber, validps.get(0).left(), R);
            //System.err.println("whichTestCanRun: " + whichTestCanRun2);
            //synchronized (this) {
            {
                int wc = 0;
                while (true) {
                    wc++;
                    if (whichTestCanRun2 != testNumber) {
//                        try {
//                            Thread.sleep(Math.max(testNumber - wc, 1) * 15);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
                        Thread.yield();
                        continue;
                    }
                    //System.err.println("end");
                    //System.err.println(testNumber + " waiting " + whichTestCanRun2);
                    break;
                }
            }
            //}
            out.printf("%.5f %.5f\n", validps.get(0).left(), R);
            //synchronized (this) {
                whichTestCanRun2++;
            //}
        }

        private List<Interval1D> join(List<Interval1D> lst) {
            Collections.sort(lst, Interval1D.LEFT_ENDPOINT_ORDER);
            List<Interval1D> res = new ArrayList<Interval1D>();
            double maxR = 0;
            for (int i = 0; i < lst.size(); ) {
                maxR = lst.get(i).right();
                int j = i + 1;
                while (j < lst.size() && lst.get(j).left() <= maxR) {
                    maxR = Math.max(maxR, lst.get(j).right());
                    j++;
                }
                if (j == i + 1) {
                    res.add(lst.get(i));
                } else {
                    res.add(new Interval1D(lst.get(i).left(), maxR));
                }
                i = j;
            }
            return res;
        }

    }

    static class ShortestPath {
        int N;
        int M;
        public HashMap<Integer, Long>[] adj;

        public ShortestPath(int N) {
            this.N = N;
            adj = new HashMap[N];
            for (int i = 0; i < N; ++i) adj[i] = new HashMap<Integer, Long>();
        }

        public void addE(int a, int b, long cost) {
            if (a == b) {
                if (cost < 0) throw new RuntimeException("Negative loops exist.");
                return;
            }
            if (adj[a].containsKey(b) && adj[a].get(b) > cost) adj[a].put(b, cost);
            else adj[a].put(b, cost);
            M++;
        }

        public long[] shortestPath(int S) {
            long[] d = new long[N];
            Arrays.fill(d, Long.MAX_VALUE);
            boolean[] inque = new boolean[N];
            d[S] = 0;
            Queue<Integer> que = new LinkedList<Integer>();
            que.add(S);
            while (true) {
                if (que.isEmpty()) break;
                int a = que.poll();
                inque[a] = false;
                for (int b : adj[a].keySet()) {
                    long a2b = d[a] + adj[a].get(b);
                    if (a2b < d[b] && !inque[b]) {
                        que.add(b);
                        inque[b] = true;
                    }
                    d[b] = Math.min(d[b], a2b);
                }
            }
            return d;
        }

    }

    static class Seqs {
        public static long max(long[] a) {
            if (a.length <= 0) throw new RuntimeException();
            long res = a[0];
            for (int i = 0; i < a.length; ++i) if (res < a[i]) res = a[i];
            return res;
        }

    }

    static class Intervals {
        public static Interval1D intersects(Interval1D a, Interval1D b) {
            if (!a.intersects(b)) return null;
            return new Interval1D(Math.max(a.left(), b.left()), Math.min(a.right(), b.right()));
        }

    }

    static class Interval1D {
        public static final Comparator<Interval1D> LEFT_ENDPOINT_ORDER = new Interval1D.LeftComparator();
        private final double left;
        private final double right;

        public Interval1D(double left, double right) {
            if (Double.isInfinite(left) || Double.isInfinite(right))
                throw new IllegalArgumentException("Endpoints must be finite");
            if (Double.isNaN(left) || Double.isNaN(right))
                throw new IllegalArgumentException("Endpoints cannot be NaN");

            // convert -0.0 to +0.0
            if (left == 0.0) left = 0.0;
            if (right == 0.0) right = 0.0;

            if (left <= right) {
                this.left = left;
                this.right = right;
            } else throw new IllegalArgumentException("Illegal interval");
        }

        public double left() {
            return left;
        }

        public double right() {
            return right;
        }

        public boolean intersects(Interval1D that) {
            if (this.right < that.left) return false;
            if (that.right < this.left) return false;
            return true;
        }

        public String toString() {
            return "[" + left + ", " + right + "]";
        }

        public boolean equals(Object other) {
            if (other == this) return true;
            if (other == null) return false;
            if (other.getClass() != this.getClass()) return false;
            Interval1D that = (Interval1D) other;
            return this.left == that.left && this.right == that.right;
        }

        public int hashCode() {
            int hash1 = ((Double) left).hashCode();
            int hash2 = ((Double) right).hashCode();
            return 31 * hash1 + hash2;
        }

        private static class LeftComparator implements Comparator<Interval1D> {
            public int compare(Interval1D a, Interval1D b) {
                if (a.left < b.left) return -1;
                else if (a.left > b.left) return +1;
                else if (a.right < b.right) return -1;
                else if (a.right > b.right) return +1;
                else return 0;
            }

        }

    }
}


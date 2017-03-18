package main;

import template.collection.intervals.IntAbstractIntervalTree;
import template.debug.InputReader;

import java.io.PrintWriter;
import java.util.Arrays;

/*
 ID: hackint1
 LANG: JAVA
 TASK: picture
*/

public class Picture {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int MAX = 20000 + 1;
        int n = in.readInt();
        VerticalSegment[] verticals = new VerticalSegment[n * 2];
        HorizontalSegment[] horizontals = new HorizontalSegment[n * 2];
        for (int i = 0; i < n; ++i) {
            int x1 = in.readInt() + MAX / 2;
            int y1 = in.readInt() + MAX / 2;
            int x2 = in.readInt() + MAX / 2;
            int y2 = in.readInt() + MAX / 2;
            verticals[i * 2] = new VerticalSegment(x1, y1, y2, true);
            verticals[i * 2 + 1] = new VerticalSegment(x2, y1, y2, false);
            horizontals[i * 2] = new HorizontalSegment(y1, x1, x2, true);
            horizontals[i * 2 + 1] = new HorizontalSegment(y2, x1, x2, false);
        }
        Arrays.sort(verticals);
        Arrays.sort(horizontals);
        int[] array = new int[MAX];
        IntervalTree intervalTree = new IntervalTree(array);
        long perimeter = 0;
//        int[] cnt = new int[MAX];
        for (VerticalSegment segment : verticals) {
            int from = segment.y1, to = segment.y2;
            if (segment.left) {
                int zeros = intervalTree.query(from, to);
                //System.out.println(segment + " " + intervalTree.query(segment.y1, segment.y2 + 1));
                perimeter += zeros;
                intervalTree.update(from, to, 1);

//                int zeros2 = zeros(cnt, from, to);
//                if (zeros != zeros2) {
//                    throw new RuntimeException();
//                }
//                add(cnt, from, to, 1);
            } else {
                intervalTree.update(from, to, -1);
                int zeros = intervalTree.query(from, to);
                perimeter += zeros;

//                add(cnt, from, to, -1);
//                int zeros2 = zeros(cnt, from, to);
//                if (zeros != zeros2) {
//                    throw new RuntimeException();
//                }
                //System.out.println(segment + " " + intervalTree.query(segment.y1, segment.y2 + 1));
            }
        }
        System.out.println(perimeter);

//        cnt = new int[MAX];
        intervalTree = new IntervalTree(array);
        for (HorizontalSegment segment : horizontals) {
            int from = segment.x1, to = segment.x2;
            if (segment.lower) {
                int zeros = intervalTree.query(from, to);
                //System.out.println(verticalSegment + " " + intervalTree.query(verticalSegment.y1, verticalSegment.y2 + 1));
                perimeter += zeros;
                intervalTree.update(from, to, 1);

//                int zeros2 = zeros(cnt, from, to);
//                if (zeros != zeros2) {
//                    throw new RuntimeException();
//                }
//                add(cnt, from, to, 1);
            } else {
                intervalTree.update(from, to, -1);
                int zeros = intervalTree.query(from, to);
                perimeter += zeros;

//                add(cnt, from, to, -1);
//                int zeros2 = zeros(cnt, from, to);
//                if (zeros != zeros2) {
//                    throw new RuntimeException();
//                }
                //System.out.println(verticalSegment + " " + intervalTree.query(verticalSegment.y1, verticalSegment.y2 + 1));
            }
        }

        out.println(perimeter);

//        int MAX = 5;
//        int[] y = new int[MAX];
//        IntervalTree intervalTree = new IntervalTree(y);
//        intervalTree.update(0, 2, 1);
//        System.out.println(intervalTree.query(0, y.length));
//        intervalTree.update(1, 3, 1);
//        System.out.println(intervalTree.query(0, y.length));
//        intervalTree.update(1, 3, -1);
//        System.out.println(intervalTree.query(0, y.length));
//        intervalTree.update(1, 3, 1);
//        System.out.println(intervalTree.query(0, y.length));
//        intervalTree.update(0, 2, -1);
//        System.out.println(intervalTree.query(0, y.length));
//        intervalTree.update(1, 3, -1);
//        System.out.println(intervalTree.query(0, y.length));

    }

    private int zeros(int[] cnt, int from, int to) {
        int res = 0;
        for (int i = from; i < to; ++i) if (cnt[i] == 0) res++;
        return res;
    }

    private void add(int[] cnt, int from, int to, int delta) {
        for (int i = from; i < to; ++i) cnt[i] += delta;
    }

    class VerticalSegment implements Comparable<VerticalSegment> {
        int x, y1, y2;
        boolean left;

        public VerticalSegment(int x, int y1, int y2, boolean left) {
            this.x = x;
            this.y1 = y1;
            this.y2 = y2;
            this.left = left;
        }

        public int length() {
            return y2 - y1 + 1;
        }

        @Override
        public int compareTo(VerticalSegment o) {
            if (x != o.x) return x - o.x;
            if (left && !o.left) return -1;
            if (!left && o.left) return +1;
            return 0;
        }

        @Override
        public String toString() {
            return x + " " + y1 + " " + y2 + " " + left + " " + length();
        }
    }

    class HorizontalSegment implements Comparable<HorizontalSegment> {
        int y, x1, x2;
        boolean lower;

        public HorizontalSegment(int y, int x1, int x2, boolean lower) {
            this.y = y;
            this.x1 = x1;
            this.x2 = x2;
            this.lower = lower;
        }

        public int length() {
            return x2 - x1 + 1;
        }
        @Override
        public int compareTo(HorizontalSegment o) {
            if (y != o.y) return y - o.y;
            if (lower && !o.lower) return -1;
            if (!lower && o.lower) return +1;
            return 0;
        }

        @Override
        public String toString() {
            return y + " " + x1 + " " + x2 + " " + lower + " " + length();
        }
    }

    class IntervalTree extends IntAbstractIntervalTree<Integer> {
        int[] array;
        //int[] lines;
        int[] zeros;
        int[] delta;
        int nodeCount;

        public IntervalTree(int[] array) {
            this.array = array;
            this.nodeCount = nodeCount(array.length);
            this.delta = new int[nodeCount];
            //this.lines = new int[nodeCount];
            this.zeros = new int[nodeCount];
            init();
        }

        @Override
        public int arraySize() {
            return array.length;
        }

        @Override
        public int joinDelta(int delta1, int delta2) {
            return delta1 + delta2;
        }

        @Override
        public int getDelta(int root) {
            return delta[root];
        }

        @Override
        public int baseDelta() {
            return 0;
        }

        @Override
        public void setDelta(int root, int newDelta) {
            delta[root] = newDelta;
        }

        @Override
        public void initLeaf(int root, int arrayIndex) {
            zeros[root] = array[arrayIndex] == 0 ? 1 : 0;
        }

        @Override
        public void initAfter(int root, int left, int right) {
            zeros[root] = zeros[left] + zeros[right];
        }

        @Override
        protected void updateLeaf(int root, int from, int to, int left, int right, int updateDelta) {
            int newDelta = delta[root] + updateDelta;
            if (newDelta > 0) zeros[root] = 0;
            if (newDelta < 0) throw new RuntimeException();
            if (newDelta == 0) {
                if (from + 1 == to) zeros[root] = 1;
                else zeros[root] = zeros[left] + zeros[right];
            }
        }

        @Override
        protected void updateAfter(int root, int from, int to, int left, int right, int curDelta) {
            if (curDelta > 0) zeros[root] = 0;
            if (curDelta < 0) throw new RuntimeException();
            if (curDelta == 0) zeros[root] = zeros[left] + zeros[right];

        }

        @Override
        public Integer queryLeaf(int root, int from, int to, int left, int right, int deltaAcc) {
            int newDelta = delta[root] + deltaAcc;
            if (newDelta > 0) return 0;
            if (newDelta < 0) throw new RuntimeException();
            if (newDelta == 0) {
                if (from + 1 == to) return  1;
                return zeros[left] + zeros[right];
            }
            throw new RuntimeException();
        }

        @Override
        public Integer queryAfter(Integer leftResult, Integer rightResult) {
            return leftResult + rightResult;
        }
    }
}

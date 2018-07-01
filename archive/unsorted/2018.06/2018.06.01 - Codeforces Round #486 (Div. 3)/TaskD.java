package main;

import template.debug.InputReader;
import template.debug.OutputWriter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int N = in.readInt();
        long[] ints = new long[N];
        Set<Long> set = new HashSet<>();
        for (int i = 0; i < N; ++i) {
            ints[i] = in.readLong();
            set.add(ints[i]);
        }
        Answer ans = new Answer(1, ints[0]);
        for (int i = 0; i < N; ++i) {
            for (int p = 0; p < 35; ++p) {
                Answer res = new Answer(1, ints[i]);
                long left = ints[i] - (1L << p);
                long right = ints[i] + (1L << p);
                if (set.contains(left)) {
                    res.left = left;
                    res.size++;
                }
                if (set.contains(right)) {
                    res.right = right;
                    res.size++;
                }
                if (res.compareTo(ans) > 0)
                    ans = res;
            }
        }
        out.printLine(ans);
    }

    class Answer implements Comparable<Answer> {
        int size;
        Long left, mid, right;

        public Answer() {
        }

        public Answer(int size, Long mid) {
            this.size = size;
            this.mid = mid;
        }

        //        public Answer(int size, Long left, Long mid, Long right) {
//            this.size = size;
//            this.left = left;
//            this.mid = mid;
//            this.right = right;
//        }

        @Override
        public int compareTo(Answer o) {
            return size - o.size;
        }

        @Override
        public String toString() {
            String res = size + "\n" + (left != null ? left + " " : "") + mid;
            if (right != null)
                res += " " + right;
            return res;
        }
    }
}

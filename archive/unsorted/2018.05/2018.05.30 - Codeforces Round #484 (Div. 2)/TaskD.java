package main;

import template.collection.intervals.Interval;
import template.debug.InputReader;
import template.debug.OutputWriter;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeSet;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int N = in.readInt();
        Day[] days = new Day[N];
        for (int i = 0; i < N; ++i) {
            days[i] = new Day(in.readInt(), i);
        }
        if (N == 1) {
            out.printLine(days[0].speed + 1);
            return;
        }
        Arrays.sort(days, new Comparator<Day>() {
            @Override
            public int compare(Day o1, Day o2) {
                return o1.speed - o2.speed;
            }
        });
        TreeSet<Interval> intervals = new TreeSet<>(Interval.LEFT_ENDPOINT_ORDER);
        CountMap counts = new CountMap();
        int max = 0;
        int min = -1;
        for (int i = 1; i < N; ++i) {
            if (days[i - 1].speed == days[i].speed)
                continue;
            Interval pre = new Interval(days[i - 1].index, days[i - 1].index + 1);
            Interval[] border = {intervals.lower(pre), intervals.higher(pre)};
            for (int b = 0; b < 2; ++b) {
                if (border[b] != null && border[b].intersects(pre)) {
                    throw new RuntimeException();
                }
            }
            if (border[0] != null && border[1] != null && border[0].right() + 1 == border[1].left()) {
                for (int b = 0; b < 2; ++b) {
                    intervals.remove(border[b]);
                    counts.decrease(border[b].length());
                }
                intervals.add(new Interval(border[0].left(), border[1].right()));
                counts.increase(border[0].length() + 1 + border[1].length());
            } else {
                boolean joined = false;
                for (int b = 0; b < 2; ++b) {
                    if (border[b] != null && (b == 0 ? border[b].right() == pre.left() : pre.right() == border[b].left())) {
                        if (joined) {
                            throw new RuntimeException();
                        }
                        intervals.remove(border[b]);
                        intervals.add(new Interval(Math.min(border[b].left(), pre.left()), Math.max(border[b].right(), pre.right())));
                        counts.decrease(border[b].length());
                        counts.increase(border[b].length() + 1);
                        joined = true;
                    }
                }
                if (!joined) {
                    intervals.add(pre);
                    counts.increase(1);
                }
            }

            if (counts.size() == 1) {
                int count = counts.value();
                if (count > max) {
                    max = count;
                    min = days[i - 1].speed + 1;
                }
            }
        }
        out.printLine(min);
    }

    class CountMap {
        HashMap<Integer, Integer> map = new HashMap<>();

        void increase(int key) {
            map.put(key, map.containsKey(key) ? map.get(key) + 1 : 1);
        }

        void decrease(int key) {
            if (!map.containsKey(key)) {
                throw new RuntimeException();
            }
            int nc = map.get(key) - 1;
            if (nc == 0)
                map.remove(key);
            else
                map.put(key, nc);
        }

        int size() {
            return map.size();
        }

        int value() {
            return map.values().toArray(new Integer[0])[0];
        }
    }

    class Day {
        int speed, index;

        public Day(int speed, int index) {
            this.speed = speed;
            this.index = index;
        }
    }
}

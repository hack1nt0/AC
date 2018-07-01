package main;

import java.util.*;

public class RangeGame {
    class Interval {
        int left, right;
        int count;

        public Interval(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

    class Doors {
        List<Interval> intervals = new ArrayList<>();
        boolean valid = true;

        public Doors(List<Interval> intervals) {
            this.intervals = intervals;
        }

        boolean intersect(Doors o) {
            for (Interval a : intervals)
                for (Interval b : o.intervals)
                    if (!(a.right < b.left || b.right < a.left))
                        return true;
            return false;
        }
    }

    public int[] bestDoors(String[] possible, String[] hints) {
        List<Doors> coarses = parse(possible);
        List<Doors> hintList = parse(hints);
        int[] ans = new int[hints.length + 1];
        int ih = 0;
        do {
            TreeSet<Integer> xs = new TreeSet<>();
            for (Doors coarse : coarses) {
                if (!coarse.valid)
                    continue;
                for (Interval interval : coarse.intervals) {
                    xs.add(interval.left);
                    xs.add(interval.right);
                }
            }
            Integer[] sortedXs = xs.toArray(new Integer[0]);
            List<Interval> fines = new ArrayList<>();
            for (int i = 0; i < sortedXs.length; ++i) {
                fines.add(new Interval(sortedXs[i], sortedXs[i]));
                if (0 < i)
                    fines.add(new Interval(sortedXs[i - 1], sortedXs[i]));
            }
            for (Doors coarse : coarses) {
                if (!coarse.valid)
                    continue;
                for (Interval interval : coarse.intervals) {
                    for (Interval fine : fines) {
                        if (interval.left <= fine.left && fine.right <= interval.right)
                            ++fine.count;
                    }
                }
            }
            Collections.sort(fines, new Comparator<Interval>() {
                @Override
                public int compare(Interval o1, Interval o2) {
                    if (o1.count != o2.count)
                        return -o1.count + o2.count;
                    else
                        return o1.left - o2.left;
                }
            });
            ans[ih] = fines.get(0).left;

            if (ih < hints.length) {
                Doors hint = hintList.get(ih);
                for (Doors coarse : coarses) {
                    if (coarse.intersect(hint))
                        coarse.valid = false;
                }
            }
            ++ih;
        } while (ih <= hints.length);
        return ans;
    }

    private List<Doors> parse(String[] possible) {
        List<Doors> ans = new ArrayList<>();
        for (String p : possible) {
            List<Interval> intervals = new ArrayList<>();
            String[] tmp = p.split(" ");
            for (String t : tmp) {
                String[] tmp2 = t.split("-");
                int left, right; left = right = -1;
                if (tmp2.length == 2) {
                    left = Integer.parseInt(tmp2[0]);
                    right = Integer.parseInt(tmp2[1]);
                }
                if (tmp2.length == 1) {
                    left = Integer.parseInt(tmp2[0]);
                    right = Integer.parseInt(tmp2[0]);
                }
                intervals.add(new Interval(left, right));
            }
            ans.add(new Doors(intervals));
        }
        return ans;
    }
}

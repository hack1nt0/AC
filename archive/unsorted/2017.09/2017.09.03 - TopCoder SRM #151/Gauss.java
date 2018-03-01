package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Gauss {
    public String[] whichSums(String target) {
        /*
         * x + ... + (x + len - 1)
         * (x + x + len - 1) * len / 2
         */
        List<Pair> resultList = new ArrayList<>();
        long t = Long.valueOf(target);
        long len = 2;
        while (true) {
            if (t * 2 % len == 0 && (t * 2 / len - len + 1) % 2 == 0) {
                long from = (t * 2 / len - len + 1) / 2;
                if (from > 0) {
                    resultList.add(new Pair(from, from + len - 1));
                } else {
                    break;
                }
            }
            double from = (t * 2.0 / len - len + 1) / 2;
            if (from < 0) break;
            len++;
        }
        Collections.sort(resultList);
        String[] result = resultList.stream().map(Pair::toString).toArray(String[]::new);
        return result;
    }

    class Pair implements Comparable<Pair>{
        long from, to;

        public Pair(long from, long to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public String toString() {
            return "[" + from + ", " + to + "]";
        }

        @Override
        public int compareTo(Pair o) {
            if (from == o.from) return 0;
            return from > o.from ? +1 : -1;
        }
    }
}

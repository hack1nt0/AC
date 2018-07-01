package main;

import java.util.*;

//WA...dp on min-max function
public class LineColoring {
    final long MAX = (long) 1e6 + 1L;
    long min = Long.MAX_VALUE;

    public int minCost(int[] x) {
        int n = x.length;
        Map<State, Long> cache = new HashMap<>();
        long min = dfs(0, 0, 0, x, cache, 0);
        for (State key : cache.keySet()) {
            if (cache.get(key) == 113)
                throw new RuntimeException();
        }
        return (int) min;
    }

    private long dfs(int cur, int first, int second, int[] x, Map<State, Long> cache, long sum) {
        State state = new State(cur, first, second);
        if (cache.containsKey(state)) {
//            System.out.println("HI");
            return cache.get(state);
        }
//        if (sum >= this.min)
//            return MAX * x.length;
        if (cur == x.length) {
            this.min = Math.min(this.min, sum);
            return 0L;
        }
        long min = Long.MAX_VALUE;
        int nsecond = Math.max(first, x[cur]);
        int acc = nsecond - first;
        min = Math.min(min, dfs(cur + 1, second, nsecond, x, cache, sum + acc) + acc);

//        if (x[cur] <= first) {
//            min = Math.min(min, dfs((cur + 1L) * MAX * MAX + second * MAX + first, x, cache, sum));
//        } else {
//            min = Math.min(min, dfs((cur + 1L) * MAX * MAX + second * MAX + x[cur], x, cache, sum + x[cur] - first) + x[cur] - first);
//        }
//        if (x[cur] > first)
            min = Math.min(min, dfs(cur + 1, Math.max(first, second), x[cur], x, cache, sum + x[cur]) + x[cur]);
        cache.put(state, min);
        return min;
    }

    class State {
        int cur, first, second;

        public State(int cur, int first, int second) {
            this.cur = cur;
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            State state = (State) o;

            if (first != state.first) return false;
            if (second != state.second) return false;
            return cur == state.cur;
        }

        @Override
        public int hashCode() {
            int result = first;
            result = 31 * result + second;
            result = 31 * result + cur;
            return result;
        }

        @Override
        public String toString() {
            return "" + cur + " " + first + " " + second;
        }
    }

}

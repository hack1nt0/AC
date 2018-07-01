package main;

import java.util.HashMap;
import java.util.Map;

public class PolicePair {
    public int[] bestSquad(int[] skillStart, int[] skillEnd) {
        DefaultMap count = new DefaultMap();
        for (int i = 0; i < skillStart.length; ++i)
            for (int j = skillStart[i]; j <= skillEnd[i]; ++j)
                count.increase(j);
        int MAXV = 1000, n = skillStart.length;
        int[][] dp = new int[MAXV + 1][MAXV + 1];
        State ans = new State(0, 0);
        for (int offset = 0; offset < MAXV; ++offset) {
            for (int from = 1; from + offset <= MAXV; ++from) {
                int to = from + offset;
                int res = 0;
                if (count.get(from) > 0 && count.get(to) > 0) {
                    if (from == to)
                        res += count.get(from) / 2 * 2;
                    else
                        res += Math.min(count.get(from), count.get(to)) * 2;
                }
                if (from + 1 <= to - 1)
                    res += dp[from + 1][to - 1];
                dp[from][to] = res;
                State state = new State(res, from + to);
                if (state.compareTo(ans) > 0) {
//                    System.out.println(state);
                    ans = state;
                }
            }
        }
        return new int[] {count.nTotal - ans.nAssigned, (int) (ans.sumSkill / 2.0)};
    }

    class State implements Comparable<State> {
        int nAssigned, sumSkill;

        public State(int nAssigned, int sumSkill) {
            this.nAssigned = nAssigned;
            this.sumSkill = sumSkill;
        }

        @Override
        public int compareTo(State o) {
            if (nAssigned != o.nAssigned)
                return nAssigned - o.nAssigned;
            return sumSkill - o.sumSkill;
        }

        @Override
        public String toString() {
            return "State{" +
                    "nAssigned=" + nAssigned +
                    ", sumSkill=" + sumSkill +
                    '}';
        }
    }

    class DefaultMap {
        Map<Integer, Integer> count = new HashMap<>();
        int nTotal = 0;

        void increase(int key) {
            count.put(key, count.containsKey(key) ? count.get(key) + 1 : 1);
            nTotal++;
        }

        int get(int key) {
            return count.containsKey(key) ? count.get(key) : 0;
        }
    }

}

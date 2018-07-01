package main;

import java.util.Set;
import java.util.TreeSet;

public class Fifteen {
    public String outcome(int[] moves) {
        Set<Integer>[] moveSet = new TreeSet[2];
        for (int i = 0; i < 2; ++i)
            moveSet[i] = new TreeSet<>();
        boolean[] visited = new boolean[9 + 1];
        for (int i = 0; i < moves.length; ++i) {
            visited[moves[i]] = true;
            if (i % 2 == 0)
                moveSet[0].add(moves[i]);
            else
                moveSet[1].add(moves[i]);
        }
        String ans = dfs(1, moveSet, visited);
        return ans;
    }

    private String dfs(int cur, Set<Integer>[] moveSet, boolean[] visited) {
        String ans = "LOSE";
        if (moveSet[0].size() + moveSet[1].size() == 9)
            return ans;
        for (int i = 1; i <= 9; ++i) {
            if (visited[i])
                continue;
            visited[i] = true;
            moveSet[cur].add(i);
            Integer[] had = moveSet[cur].toArray(new Integer[0]);
            for (int S = 0; S < 1 << had.length; ++S) {
                if (Integer.bitCount(S) != 3) continue;
                int sum = 0;
                for (int j = 0; j < had.length; ++j)
                    if ((S >> j & 1) != 0)
                        sum += had[j];
                if (sum == 15)
                    ans = "WIN " + i;
            }
            if (ans.equals("LOSE") && dfs(cur ^ 1, moveSet, visited).equals("LOSE"))
                ans = "WIN " + i;
            visited[i] = false;
            moveSet[cur].remove(i);
            if (!ans.equals("LOSE"))
                break;
        }
        return ans;
    }
}

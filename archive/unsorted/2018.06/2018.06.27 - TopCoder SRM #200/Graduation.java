package main;

import template.collection.sequence.ArrayUtils;

import java.util.*;

public class Graduation {

    public String moreClasses(String classesTaken, String[] requirements) {
        String ERROR = "0";
        Map<Character, List<Integer>> adj = new TreeMap<>();
        int nr = requirements.length;
        int[] lb = new int[nr];
        int[] nf = new int[nr];
        for (int i = 0; i < nr; ++i) {
            int j = 0;
            while (j < requirements[i].length()) {
                int d = requirements[i].charAt(j) - '0';
                if (0 <= d && d < 10)
                    lb[i] = lb[i] * 10 + d;
                else
                    break;
                ++j;
            }
            while (j < requirements[i].length()) {
                char c = requirements[i].charAt(j);
                if (!adj.containsKey(c))
                    adj.put(c, new ArrayList<>());
                adj.get(c).add(i);
                ++j;
            }
        }
        String ans = "";
        List<Character>[] from = new ArrayList[nr];
        for (int i = 0; i < nr; ++i)
            from[i] = new ArrayList<>();
        for (char c : classesTaken.toCharArray()) {
            if (!adj.containsKey(c))
                continue;
            boolean[] visited = new boolean[nr];
            sat(c, adj, lb, nf, from, visited);
        }
        for (char c : adj.keySet()) {
            if (classesTaken.indexOf(c) != -1)
                continue;
            boolean[] visited = new boolean[nr];
            if (sat(c, adj, lb, nf, from, visited) && classesTaken.indexOf(c) == -1)
                ans += c;
        }
        for (int i = 0; i < nr; ++i)
            if (nf[i] != lb[i])
                return ERROR;
        return ans;
    }

    private boolean sat(char c, Map<Character, List<Integer>> adj, int[] lb, int[] nf, List<Character>[] from, boolean[] visited) {
        for (int r : adj.get(c)) {
            if (visited[r])
                continue;
            if (nf[r] < lb[r]) {
                from[r].add(c);
                nf[r]++;
                return true;
            }
            visited[r] = true;
            for (char f : from[r]) {
                if (sat(f, adj, lb, nf, from, visited)) {
                    from[r].remove((Character) f);
                    from[r].add(c);
                    return true;
                }
            }
        }
        return false;
    }
}

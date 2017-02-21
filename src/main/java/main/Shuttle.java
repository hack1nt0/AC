package main;

import template.collection.sequence.ArrayUtils;

import java.util.*;
import java.io.PrintWriter;

/*
 ID: hackint1
 LANG: JAVA
 TASK: shuttle
*/

/**
 * KISS: NO optimistic is necessary here.
 */
public class Shuttle {
    int T;
    int S;
    Map<Integer, Integer> minMoveMap = new HashMap<>();
    int minMove = Integer.MAX_VALUE;
    int n;
    Integer[] minMoveArr;
    int[] rank;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        n = in.nextInt();
        for (int i = 0; i < n; ++i) {
            S |= 1 << (n + 1 + i);
            T |= 1 << i;
        }
        S |= n << (n * 2 + 1);
        T |= n << (n * 2 + 1);

        rank = new int[n * 2 + 1];
        for (int i = 0; i < rank.length; ++i) rank[i] = n * 2 + 1 - i;
        dfs(S, 0, new ArrayList<Integer>());

        ArrayUtils.printlnConcisely(minMoveArr, out, 20);
    }

    private void dfs(int cur, int nMove, List<Integer> moveList) {
        int mask = (1 << (n * 2 + 1)) - 1;
        int empty = cur >> (n * 2 + 1);

        //System.out.println(Integer.toBinaryString(cur >> (n * 2 + 1)) + " " + Integer.toBinaryString(cur & mask));

        if (cur == T) {
            if (nMove < minMove) {
                minMove = nMove;
                minMoveArr = moveList.subList(0, nMove).toArray(new Integer[0]);
            }
            return;
        }
        if (nMove >= minMove) {
            return;
        }
//        if (minMoveMap.containsKey(cur)) {
//            int totMove = nMove + minMoveMap.get(cur);
//            if (totMove >= minMove) return;
//        }


        if (empty + 1 <= n * 2 && (cur & 1 << (empty + 1)) > 0) {
            if (nMove < moveList.size()) moveList.set(nMove, rank[empty + 1]);
            else moveList.add(rank[empty + 1]);
            int next = (cur | 1 << empty) & ~(1 << (empty + 1));
            next &= mask;
            next |= (empty + 1) << (n * 2 + 1);
            if (Integer.bitCount(next & mask) > n) {
                System.err.println(Integer.toBinaryString(cur));
                System.err.println(Integer.toBinaryString(next));
                throw new RuntimeException();
            }
            dfs(next, nMove + 1, moveList);
        }

        if (empty + 2 <= n * 2 && (cur & 1 << (empty + 1)) == 0 && (cur & 1 << (empty + 2)) > 0) {
            if (nMove < moveList.size()) moveList.set(nMove, rank[empty + 2]);
            else moveList.add(rank[empty + 2]);
            int next = (cur | 1 << empty) & ~(1 << (empty + 2));
            next &= mask;
            next |= (empty + 2) << (n * 2 + 1);
            if (Integer.bitCount(next & mask) > n) {
                System.err.println(Integer.toBinaryString(cur));
                System.err.println(Integer.toBinaryString(next));
                throw new RuntimeException();
            }
            dfs(next, nMove + 1, moveList);
        }

        if (empty - 1 >= 0 && (cur & 1 << (empty - 1)) == 0) {
            try {
                if (nMove < moveList.size()) moveList.set(nMove, rank[empty - 1]);
                else moveList.add(rank[empty - 1]);
            } catch (Exception e) {
                e.printStackTrace();
            }
            int next = (cur & mask) | (empty - 1) << (n * 2 + 1);
            if (Integer.bitCount(next & mask) > n) {
                System.err.println(Integer.toBinaryString(cur));
                System.err.println(Integer.toBinaryString(next));
                throw new RuntimeException();
            }
            dfs(next, nMove + 1, moveList);
        }

        if (empty - 2 >= 0 && (cur & 1 << (empty - 1)) > 0 && (cur & 1 << (empty - 2)) == 0) {
            if (nMove < moveList.size()) moveList.set(nMove, rank[empty - 2]);
            else moveList.add(rank[empty - 2]);
            int next = (cur & mask) | (empty - 2) << (n * 2 + 1);
            if (Integer.bitCount(next & mask) > n) {
                System.err.println(Integer.toBinaryString(cur));
                System.err.println(Integer.toBinaryString(next));
                throw new RuntimeException();
            }
            dfs(next, nMove + 1, moveList);
        }
    }
}

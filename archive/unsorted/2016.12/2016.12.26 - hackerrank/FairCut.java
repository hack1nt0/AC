package main;

import java.util.*;
import java.io.PrintWriter;

/**
 * WA : My Greedy Algo dont work.
 *
 *
 * A copied greedy approach:
 * I figured out the greedy strategy of this problem!
 * The optimal cut is independent of the numbers, but only depends on their order.
 * For example, if there are N=10 numbers to cut, and one person gets K=3 numbers,
 * the best cut will be 0001010100, which means we first sort the 10 numbers in ascending order A[1]<=A[2]<=...<=A[10],
 * and give the 0s to one person and the 1s to another.
 * The strategy is to place the 01-string repetition (the "010101" in the example) as close to the middle of the whole string as possible.
 * If K*2>N, redefine K to be N-K (switching the 0s and 1s).
 * The intuition behind this greedy strategy comes from physics.
 * The unfairness is like an interaction potential to be minimized.
 * The 0s and 1s attract each other and form an ionic crystal in the middle of a positive charge background.
 *
 * Can be solve with dp[][][], but not generalized.
 */
public class FairCut {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int N = in.nextInt();
        int K = in.nextInt();
        Set<Integer> A = new HashSet<>();
        final int[] a = new int[N];
        for (int i = 0; i < N; ++i) a[i] = in.nextInt();
        Integer[] rank = new Integer[N];
        for (int i = 0; i < rank.length; ++i) rank[i] = i;
        Arrays.sort(rank, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return a[o1] - a[o2];
            }
        });
        if (K % 2 == 1) {
            A.add(rank[N / 2]);
            for (int i = N / 2 - 1; i > N / 2 - 1 - K / 2; --i) A.add(rank[i]);
            for (int i = N / 2 + 1; i < N / 2 + 1 + K / 2; ++i) A.add(rank[i]);
        } else {
            for (int i = N / 2 - 1; i > N / 2 - 1 - K / 2; --i) A.add(rank[i]);
            for (int i = N / 2; i < N / 2 + K / 2; ++i) A.add(rank[i]);
        }
        long ret = 0;
        for (int i : A)
            for (int j = 0; j < N; ++j) if (!A.contains(j)) {
                ret += Math.abs(a[i] - a[j]);
            }
        out.println(ret);
    }
}

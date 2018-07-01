package main;

import template.debug.InputReader;
import template.debug.OutputWriter;

import java.util.Arrays;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int k = in.readInt();
        int[] power = new int[n];
        for (int i = 0; i < n; ++i)
            power[i] = in.readInt();
        long[] coins = new long[n];
        for (int i = 0; i < n; ++i)
            coins[i] = in.readLong();
        Integer[] order = new Integer[n];
        for (int i = 0; i < n; ++i)
            order[i] = i;
        Arrays.sort(order, (i, j) -> power[i] - power[j]);
        long[] ans = new long[n];
        long acc = 0;
        for (int rank = 0; rank < n; ++rank) {
            int which = order[rank];
            ans[which] = acc + coins[which];
            acc += coins[which];
            if (rank >= k)
                acc -= coins[order[rank - k]];
        }
        out.printLine(ans);
    }
}

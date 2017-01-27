package main;

import template.collection.sequence.ArrayUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.io.PrintWriter;
import java.util.Set;

public class Milk3 {
    int n = 3;
    int[] capacity;
    boolean[][][] visited;
    private Set<Integer> ans;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        capacity = new int[n];
        for (int i = 0; i < n; ++i) capacity[i] = in.nextInt();
        visited = new boolean[capacity[0] + 1][capacity[1] + 1][capacity[2] + 1];
        ans = new HashSet<>();
        dfs(new int[]{0, 0, capacity[2]});

        Integer[] res = ans.toArray(new Integer[0]);
        Arrays.sort(res);
        for (int i = 0; i < res.length; ++i) {
            if (i > 0) out.print(" ");
            out.print(res[i]);
        }
        out.println();
    }

    private void dfs(int[] milk) {
        if (visited[milk[0]][milk[1]][milk[2]]) return;
        visited[milk[0]][milk[1]][milk[2]] = true;
        if (milk[0] == 0) ans.add(milk[2]);

        for (int pourId = 0; pourId < n; ++pourId) {
            if (milk[pourId] == 0) continue;
            //only first
            int first = (pourId + 1) % n;
            int firstReceive = Math.min(milk[pourId], capacity[first] - milk[first]);
            milk[pourId] -= firstReceive;
            milk[first] += firstReceive;
            dfs(milk);
            milk[pourId] += firstReceive;
            milk[first] -= firstReceive;

            //only second
            int second = (pourId - 1 + n) % n;
            int secondReceive = Math.min(milk[pourId], capacity[second] - milk[second]);
            milk[pourId] -= secondReceive;
            milk[second] += secondReceive;
            dfs(milk);
            milk[pourId] += secondReceive;
            milk[second] -= secondReceive;

            //first, then second
            if (milk[pourId] > firstReceive) {
                milk[pourId] -= firstReceive;
                milk[first]  += firstReceive;
                secondReceive = Math.min(milk[pourId], capacity[second] - milk[second]);
                milk[pourId] -= secondReceive;
                milk[second] += secondReceive;
                dfs(milk);
                milk[pourId] += firstReceive;
                milk[first]  -= firstReceive;
                milk[pourId] += secondReceive;
                milk[second] -= secondReceive;
            }
            //second, then first
            if (milk[pourId] > secondReceive) {
                milk[pourId] -= secondReceive;
                milk[second] += secondReceive;
                firstReceive = Math.min(milk[pourId], capacity[first] - milk[first]);
                milk[pourId] -= firstReceive;
                milk[first]  += firstReceive;
                dfs(milk);
                milk[pourId] += secondReceive;
                milk[second] -= secondReceive;
                milk[pourId] += firstReceive;
                milk[first]  -= firstReceive;

            }
        }
    }
}

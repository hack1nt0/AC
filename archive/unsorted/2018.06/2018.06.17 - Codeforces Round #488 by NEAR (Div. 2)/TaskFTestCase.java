package main;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestCase;
import template.collection.sequence.ArrayUtils;
import template.debug.RandomUtils;
import template.graph_theory.Bipartite;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class TaskFTestCase {
    @TestCase
    public Collection<Test> createTests() {
        List<Test> list = new ArrayList<>();
        RandomUtils.setSeed(1);
        int T = 1000;
        for (int t = 0; t < T; ++t) {
            int N = 8;
            int[] power = new int[N];
            int[] cpus = new int[N];
            for (int i = 0; i < N; ++i) power[i] = (RandomUtils.uniform(1, (int) 1e1 + 1));
            for (int i = 0; i < N; ++i) cpus[i] = (RandomUtils.uniform(1, (int) 1e1 + 1));
            long ans = brute(power, cpus);
            StringBuilder sb = new StringBuilder();
            sb.append(N + "\n");
            for (int i = 0; i < N; ++i) sb.append(power[i] + " ");
            sb.append("\n");
            for (int i = 0; i < N; ++i) sb.append(cpus[i] + " ");
            sb.append("\n");
            list.add(new Test(sb.toString(), ans + ""));
        }
        return list;
    }

    private static long brute(int[] power, int[] cpus) {
        int n = power.length;
        long min = Long.MAX_VALUE;
        for (long set = (1L << (n + 1) / 2) - 1; set < 1L << n; ++set) {
            List<Integer>[] adj = new List[n];
            for (int i = 0; i < n; ++i) adj[i] = new ArrayList<>();
            int left = 0;
            for (int i = 0; i < n; ++i)
                if ((set >> i & 1) == 0) {
                    left++;
                    for (int j = 0; j < n; ++j)
                        if ((set >> j & 1) != 0 && power[i] < power[j]){
                            adj[i].add(j);
                        }
                }
            int[] to = Bipartite.maxMatch(adj, left);
            int nmatch = 0;
            for (int t : to) if (t >= 0) nmatch++;
            if (nmatch == left) {
                long denom = 0, nenom = 0;
                for (int i = 0; i < n; ++i) {
                    if ((set >> i & 1) == 0) {
                        continue;
                    }
                    denom += cpus[i];
                    nenom += power[i];
                }
                min = Math.min(min, Math.round((double) nenom / denom * 1000));
            }
        }
        return min;
    }

    public static void main(String[] args) {
        int[] power = {8, 7, 3, 7};
        int[] cpus = {3, 5, 1, 1};
        System.out.println(brute(power, cpus));
    }
}

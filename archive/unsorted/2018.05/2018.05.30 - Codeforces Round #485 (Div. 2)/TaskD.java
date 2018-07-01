package main;

import template.collection.sequence.ArrayUtils;
import template.debug.InputReader;
import template.debug.OutputWriter;
import template.debug.RandomUtils;

import java.util.*;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int N = in.readInt();
        int M = in.readInt();
        int K = in.readInt();
        int S = in.readInt();
        int[] goods = new int[N];
        for (int i = 0; i < N; ++i)
            goods[i] = in.readInt() - 1;
        int oo = Integer.MAX_VALUE;
        List<Integer>[] adj = new ArrayList[N];
        for (int i = 0; i < N; ++i) adj[i] = new ArrayList<>();
        for (int i = 0; i < M; ++i) {
            int u = in.readInt() - 1;
            int v = in.readInt() - 1;
            adj[u].add(v);
            adj[v].add(u);
        }
        int[][] costs = new int[N][K];
        int[] queue = new int[N];
        for (int g = 0; g < K; ++g) {
            int head = 0, tail = 0;
            for (int i = 0; i < N; ++i)
                if (goods[i] != g) {
                    for (int chd : adj[i])
                        if (goods[chd] == g) {
                            queue[tail++] = i;
                            costs[i][g] = 1;
                            break;
                        }
                }
            while (head < tail) {
                int h = queue[head++];
                for (int chd : adj[h])
                    if (costs[chd][g] == 0 && goods[chd] != g) {
                        costs[chd][g] = costs[h][g] + 1;
                        queue[tail++] = chd;
                    }
            }
        }
        for (int i = 0; i < N; ++i) {
//            Arrays.sort(costs[i]);
            firstK(costs[i], S);
            long cost = 0;
            for (int j = 0; j < S; ++j)
                cost += costs[i][j];
            out.print(cost);
            out.print(i < N - 1 ? " " : "\n");
        }
    }

    void firstK(int[] array, int k) {
        if (k >= array.length)
            return;
        RandomUtils.shuffle(array);
        int kk = k, l = 0, r = array.length;
        while (kk > 0) {
            if (l >= r) {
                throw new RuntimeException();
            }
//            int swap = RandomUtils.uniform(l, r);
//            ArrayUtils.swap(array, swap, l);
            int mid = array[l];
            int p = l + 1, q = r - 1;
            while (p <= q) {
                while (p <= q && array[p] < mid) p++;
                while (p <= q && array[q] >= mid) q--;
                if (p < q) {
                    ArrayUtils.swap(array, p, q);
                    p++;
                    q--;
                }
            }
            ArrayUtils.swap(array, q, l);
            q--;
            p--;
            int size = p - l;
            if (size == kk)
                return;
            if (size > kk) {
                r = p;
            } else {
                l = p + 1;
                kk -= size + 1;
            }
//            System.out.println(l + " " + r + " " + kk + " " + Arrays.toString(array));
        }
    }
}

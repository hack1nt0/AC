package main;

import net.egork.chelper.task.NewTopCoderTest;
import net.egork.chelper.tester.TestCase;
import template.debug.RandomUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MakingRegularGraphTestCase {
    @TestCase
    public Collection<NewTopCoderTest> createTests() {
        int T = 10;
        List<NewTopCoderTest> list = new ArrayList<>();
        for (int t = 0; t < T; ++t) {
            int n = 1000;
            int ne = RandomUtils.uniform(n);
            int[] x = new int[ne];
            int[] y = new int[ne];
            int[] degree = new int[n];
            int tail = n - 1;
            int[] index = new int[n];
            for (int i = 0; i < n; ++i)
                index[i] = i;
            for (int e = 0; e < ne; ++e) {
                int p = RandomUtils.uniform(tail);
                int q = p + 1 + RandomUtils.uniform(tail - p);
                int[] inode = {p, q};
                x[e] = index[p];
                y[e] = index[q];
                for (int i = 0; i < inode.length; ++i) {
                    if (++degree[index[inode[i]]] == 2) {
                        int swap = index[inode[i]];
                        index[inode[i]] = index[tail];
                        index[tail] = swap;
                        tail--;
                    }
                }
            }
            for (int i = 0; i < n; ++i)
                if (degree[i] > 2) {
                    throw new RuntimeException();
                }
            list.add(new NewTopCoderTest(new Object[]{n, x, y}));
        }
        return list;
    }
}

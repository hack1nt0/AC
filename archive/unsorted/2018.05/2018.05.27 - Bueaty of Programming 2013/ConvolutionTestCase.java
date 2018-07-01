package main;


import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestCase;
import template.debug.RandomUtils;

import java.util.*;

public class ConvolutionTestCase {
    @TestCase
    public Collection<Test> createTests() {
        List<Test> list = new ArrayList<>();
        RandomUtils.setSeed(1);
        int T = 10;
        for (int t = 0; t < T; ++t) {
            int n = RandomUtils.uniform(50000) + 1;
            long[] a = new long[n];
            for (int i = 0; i < n; ++i) a[i] = RandomUtils.uniform(20);
            long[] b = new long[n];
            for (int i = 0; i < n; ++i) b[i] = RandomUtils.uniform(20);
            StringBuilder sb = new StringBuilder();
            sb.append(n + "\n");
            for (int i = 0; i < n; ++i) sb.append(a[i] + " ");
            sb.append("\n");
            for (int i = 0; i < n; ++i) sb.append(b[i] + " ");
            sb.append("\n");
            list.add(new Test(sb.toString(), Arrays.toString(Convolution.brute(a, b))));
        }
        return list;
    }
}

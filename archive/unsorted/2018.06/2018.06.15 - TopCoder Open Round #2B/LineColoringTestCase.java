package main;

import net.egork.chelper.task.NewTopCoderTest;
import net.egork.chelper.tester.TestCase;
import template.debug.RandomUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class LineColoringTestCase {
    @TestCase
    public Collection<NewTopCoderTest> createTests() {
        List<NewTopCoderTest> list = new ArrayList<>();
        RandomUtils.setSeed(1);
        int T = 100;
        for (int t = 0; t < T; ++t) {
            int N = 1000;
            int[] x = new int[N];
            for (int i = 0; i < N; ++i)
                x[i] = RandomUtils.uniform((int) 1e6 + 1);
            list.add(new NewTopCoderTest(new Object[] {x}));
        }
        return list;
    }
}


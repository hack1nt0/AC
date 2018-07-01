package main;

import net.egork.chelper.task.NewTopCoderTest;
import net.egork.chelper.tester.TestCase;
import template.debug.RandomUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class NCMultiplicationTestCase {
    @TestCase
    public Collection<NewTopCoderTest> createTests() {
        List<NewTopCoderTest> list = new ArrayList<>();
        RandomUtils.setSeed(1);
        int T = 100;
        for (int t = 0; t < T; ++t) {
            int n = 15;
            int[] c = new int[n];
            for (int i = 0; i < n; ++i)
                c[i] = RandomUtils.uniform((int) 2e1);
            list.add(new NewTopCoderTest(new Object[] {c}));
        }
        return list;
    }
}

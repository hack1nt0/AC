package main;

import net.egork.chelper.task.NewTopCoderTest;
import net.egork.chelper.tester.TestCase;
import template.debug.RandomUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class AlienMultiplicationTestCase {
    @TestCase
    public Collection<NewTopCoderTest> createTests() {
        List<NewTopCoderTest> list = new ArrayList<>();
        int T = 1000;
        for (int t = 0; t < T; ++t) {
            int a = RandomUtils.uniform((int) 1e6);
            int b = RandomUtils.uniform((int) 1e6);
            int c = RandomUtils.uniform((int) 1e6);
            list.add(new NewTopCoderTest(new Object[] {a, b, c}));
        }
        return list;
    }
}

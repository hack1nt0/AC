package main;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestCase;
import template.debug.RandomUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TaskBTestCase {
    @TestCase
    public Collection<Test> createTests() {
        List<Test> list = new ArrayList<>();
        int T = 10, MAXN = (int) 1e6;
        for (int t = 0; t < T; ++t) {
            int halfN = (int) Math.sqrt(MAXN);
            int R = RandomUtils.uniform(halfN);
            int C = RandomUtils.uniform(halfN);
            int N = RandomUtils.uniform(R * C);
            list.add(new Test("1\n" + R + " " + C + " " + N + "\n"));
        }
        return list;
    }
}

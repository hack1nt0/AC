package main;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestCase;
import template.debug.RandomUtils;

import java.util.*;

public class BSTTestCase {
    @TestCase
    public Collection<Test> createTests() {
        int T = 1000;
        RandomUtils.setSeed(1);
        List<Test> list = new ArrayList<>();
        for (int t = 0; t < T; ++t) {
            StringBuilder sb = new StringBuilder();
            int n = 1000;
            sb.append(n + "\n");
            for (int i = 0; i < n; ++i)
                sb.append(RandomUtils.uniform(1000) + " ");
            sb.append("\n");
            list.add(new Test(sb.toString()));
        }
        return list;
    }
}

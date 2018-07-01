package main;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestCase;
import template.debug.RandomUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class RiverMarketTestCase {
    @TestCase
    public Collection<Test> createTests() {
        int T = 10;
        RandomUtils.setSeed(1);
        List<Test> list = new ArrayList<>();
        for (int t = 0; t < T; ++t) {
            StringBuilder sb = new StringBuilder();
            int N = 50000;
            sb.append("1\n" + N + "\n");
            int MAX = 10000;
            for (int i = 0; i < N; ++i) {
                sb.append(RandomUtils.uniform(-MAX, +MAX) + " ");
                sb.append(RandomUtils.uniform(0, +MAX) + "\n");
            }
            list.add(new Test(sb.toString()));
        }

        return list;
    }
}

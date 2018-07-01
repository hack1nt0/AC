package main;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestCase;
import template.debug.RandomUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class TaskDTestCase {
    @TestCase
    public Collection<Test> createTests() {
        RandomUtils.setSeed(1);
        List<Test> list = new ArrayList<>();
        int T = 10;
        for (int t = 0; t < T; ++t) {
            int N = (int)1e5;
            StringBuilder sb = new StringBuilder();
            sb.append(N + "\n");
            for (int i = 0; i < N; ++i)
                sb.append(RandomUtils.uniform((int)1e9 + 1) + " ");
            sb.append("\n");
            list.add(new Test(sb.toString()));
        }
        return list;
    }
}

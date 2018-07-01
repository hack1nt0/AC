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
        List<Test> list = new ArrayList<>();
        int T = 10;
        RandomUtils.setSeed(1);
        for (int t = 0; t < T; ++t) {
            StringBuilder sb = new StringBuilder();
            int N = (int)1e5;
            int M = (int)1e5;
            int K = (int)1e2;
            int S = (int)1e2;
            sb.append("" + N + " " + M + " " + K + " " + S + "\n");
            for (int i = 0; i < N; ++i)
                sb.append((RandomUtils.uniform(N) + 1) + " ");
            sb.append("\n");
            for (int i = 0; i < M; ++i) {
                int u = RandomUtils.uniform(N) + 1;
                int v = RandomUtils.uniform(N) + 1;
                sb.append("" + u + " " + v + "\n");
            }
            list.add(new Test(sb.toString()));
        }
        return list;
    }
}



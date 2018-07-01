package main;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestCase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class SimilarStringTestCase {
    @TestCase
    public Collection<Test> createTests() {
        List<Test> testList = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        int T = 1;
        sb.append(T).append("\n");
        for (int t = 0; t < T; ++t) {
            int N = 50000;
            for (int i = 0; i < N; ++i)
                sb.append((int)(Math.random() * 10));
            sb.append("\n");
            for (int i = 0; i < N / 2; ++i)
                sb.append((int)(Math.random() * 10));
            sb.append("\n");
        }
        testList.add(new Test(sb.toString()));
        return testList;
    }
}

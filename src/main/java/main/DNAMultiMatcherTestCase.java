package main;

import net.egork.chelper.task.NewTopCoderTest;
import net.egork.chelper.tester.TestCase;
import template.debug.RandomUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class DNAMultiMatcherTestCase {
    @TestCase
    public Collection<NewTopCoderTest> createTests() {
        List<NewTopCoderTest> list = new ArrayList<>();
        int T = 10;
        RandomUtils.setSeed(1);
        for (int t = 0; t < T; ++t) {
            int N = 50 * 500;
            String[][] strings = new String[3][1];
            for (int i = 0; i < 3; ++i) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < N; ++j)
                    sb.append(RandomUtils.choose('A', 'C', 'G', 'T'));
                strings[i][0] = sb.toString();
            }
            list.add(new NewTopCoderTest(new Object[]{strings[0], strings[1], strings[2]}));
        }
        return list;
    }
}

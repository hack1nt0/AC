package main;

import net.egork.chelper.task.NewTopCoderTest;
import net.egork.chelper.tester.TestCase;
import template.debug.RandomUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class AmoebaeTestCase {
    @TestCase
    public Collection<NewTopCoderTest> createTests() {
        List<NewTopCoderTest> res = new ArrayList<>();
        int n = 50, m = 50;
        for (int i = 0; i < 10; ++i) {
            Object[] arguments = new Object[2];
            for (int j = 0; j < 2; ++j) {
                String[] map = new String[n];
                for (int r = 0; r < n; ++r) {
                    map[r] = "";
                    for (int c = 0; c < m; ++c)
                        map[r] += RandomUtils.choose('.', 'X');
                }
                arguments[j] = map;
            }
            res.add(new NewTopCoderTest(arguments));
        }
        return res;
    }
}

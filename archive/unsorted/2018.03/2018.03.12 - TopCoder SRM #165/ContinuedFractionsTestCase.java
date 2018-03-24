package main;

import net.egork.chelper.task.NewTopCoderTest;
import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestCase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ContinuedFractionsTestCase {
    @TestCase
    public Collection<NewTopCoderTest> createTests() {
        List<NewTopCoderTest> tests = new ArrayList<>();
        for (int i = 1; i < 1001; ++i) {
            if (i != (int)Math.sqrt(i) * (int)Math.sqrt(i))
                tests.add(new NewTopCoderTest(new Integer[]{i}));
        }
        return tests;
    }
}

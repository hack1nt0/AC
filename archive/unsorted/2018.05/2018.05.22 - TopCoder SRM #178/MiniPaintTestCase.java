package main;


import net.egork.chelper.task.NewTopCoderTest;
import net.egork.chelper.tester.TestCase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MiniPaintTestCase {
    @TestCase
    public Collection<NewTopCoderTest> createTests() {
        List<NewTopCoderTest> ans = new ArrayList<>();
        for (int t = 0; t < 10; ++t) {
            String[] pic = new String[50];
            for (int r = 0; r < 50; ++r) {
                StringBuilder sb = new StringBuilder();
                for (int c = 0; c < 50; ++c)
                    sb.append(Math.random() < 0.5 ? 'B' : 'W');
                pic[r] = sb.toString();
            }
            Integer max = (int)(Math.random() * 3000);
            ans.add(new NewTopCoderTest(new Object[]{pic, max}));
        }
        return ans;
    }
}

package main;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestCase;
import template.debug.RandomUtils;
import template.numbers.IntUtils;

import java.util.*;

public class TaskATestCase {
    @TestCase
    public Collection<Test> createTests() {
        int T = 10;
        List<Test> list = new ArrayList<>();
        RandomUtils.setSeed(1);
        for (int t = 0; t < T; ++t) {
            StringBuilder sb = new StringBuilder();
            sb.append("1\n");
            int N = RandomUtils.uniform(1000000);
//            int N = 3002;
            sb.append(N + "\n");
            long oo = Long.MAX_VALUE;
            long[] min = new long[(int) N + 1];
            Arrays.fill(min, oo); min[1] = 1;
            long[] pre = new long[(int) N + 1];
            for (int i = 1; i < N; ++i) {
                long rev = IntUtils.reverse(i);
                if (i < rev && rev <= N && min[i] + 1 < min[(int) rev]) {
                    min[(int) rev] = min[i] + 1;
                    pre[(int) rev] = i;
                }
                if (min[i] + 1 < min[i + 1]) {
                    min[i + 1] = min[i] + 1;
                    pre[i + 1] = i;
                }
            }
//        long cur = N;
//        while (true) {
//            System.out.println(cur + " " + min[(int) cur]);
//            if (cur == 1)
//                break;
//            cur = pre[(int) cur];
//        }
            list.add(new Test(sb.toString(), "Case #1: " + min[N]));
        }
        return list;
    }
}

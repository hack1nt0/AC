package main;

import template.numbers.DoubleUtils;

import java.io.PrintStream;
import java.io.StringWriter;
import java.util.HashSet;
import java.util.Set;

public class AntiMatter {
    public String unstable(int[] xform) {
        Set<Integer> steps = new HashSet<>();
        for (int i = 0; i < xform.length; ++i)
            for (int j = i + 1; j < xform.length; ++j) {
                int step = Math.max(xform[i], xform[j]) - Math.min(xform[i], xform[j]);
                if (step != 0)
                    steps.add(step);
            }
        Set<Integer> diffs = new HashSet<>();
        long interval = (long)1e4;
        for (int step : steps) {
            for (int i = 1; i * step < interval; ++i)
                diffs.add(i * step);
//            for (int div : dividends(step))
//                diffs.add(div);
        }
        long tot = interval;
        for (int diff : diffs) {
            if (diff == 0) throw new RuntimeException();
            tot += (interval - diff) * 2;
        }
        double ans = (double)tot / (interval * interval);
        if (ans == 1.0) {
            String s = "1.";
            for (int i = 0; i < 8; ++i)
                s += "0";
            return s;
        }
        String s = ".";
        double tmp = ans * 10;
        for (int i = 0; i < 8; ++i) {
            int d = (int)tmp;
            s += d;
            tmp = (tmp - d) * 10;
        }
        return s;
    }
}

package main;

import java.util.DoubleSummaryStatistics;
import java.util.HashSet;
import java.util.Set;

//WA
public class TickTick {
    public int count(String[] events) {
        double[] offset = new double[events.length];
        for (int i = 0; i < events.length; ++i)
            offset[i] = Double.parseDouble(events[i]);
        Set<String> set = new HashSet<>();
        double eps = 1e-9;
        double tickp = offset[0] - eps;
        double tickq = tickp;
        while (true) {
            StringBuilder sb = new StringBuilder();
            double end = tickq;
            double min = Double.MAX_VALUE;
            for (int o = 0; o < offset.length; ) {
                while (o < offset.length && offset[o] < end) {
                    sb.append("S");
                    ++o;
                }
                if (o < offset.length) {
                    sb.append("D");
                    end += Math.floor(offset[o] - end);
                    min = Math.min(min, offset[o] - end + eps);
                    end += 1;
                    ++o;
                }
            }
            set.add(sb.toString());
            if (min == Double.MAX_VALUE)
                break;
            tickq += min;
            if (tickq - tickp > 1)
                break;
        }
        return set.size();
    }
}

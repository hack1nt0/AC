package main;

import template.string.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class CircleBugs {
    public int cycleLength(String formation) {
        Map<String, Integer> had = new HashMap<>();
        String curFormation = formation;
        int n = formation.length();
        for (int i = 0;; ++i) {
            for (int j = 0; j < 2; ++j) {
                if (j == 1) {
                    curFormation = n % 2 == 0 ? StringUtils.reverse(curFormation.substring(n / 2)) + StringUtils.reverse(curFormation.substring(0, n / 2))
                            : "" + curFormation.charAt(0) + StringUtils.reverse(curFormation.substring(n / 2 + 1)) + StringUtils.reverse(curFormation.substring(1, n / 2 + 1));
                }
                int minCycleStart = StringUtils.cyclicMin(curFormation);
                String minCycle = curFormation.substring(minCycleStart) + curFormation.substring(0, minCycleStart);
                if (had.containsKey(minCycle) && had.get(minCycle) < i)
                    return i - had.get(minCycle);
                else
                    had.put(minCycle, i);
            }
            StringBuffer newFormation = new StringBuffer();
            for (int j = 0; j < curFormation.length(); ++j) {
                char a = curFormation.charAt(j);
                char b = curFormation.charAt((j + 1) % n);
                newFormation.append(a == b ? 'R' : 'G');
            }
            curFormation = newFormation.toString();
        }
    }
}

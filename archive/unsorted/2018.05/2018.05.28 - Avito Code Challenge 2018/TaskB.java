package main;

import java.util.*;
import java.io.PrintWriter;

public class TaskB {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        long max = 0;
        Map<Integer, Integer> as = new HashMap<>();
        int na = in.nextInt();
        for (int i = 0; i < na; ++i) {
            int index = in.nextInt();
            int income = in.nextInt();
            as.put(index, income);
            max += income;
        }
        int nb = in.nextInt();
        for (int i = 0; i < nb; ++i) {
            int index = in.nextInt();
            int income = in.nextInt();
            Integer ai = as.get(index);
            if (ai != null) {
                max = max - ai + Math.max(ai, income);
            } else {
                max += income;
            }
        }
        out.println(max);
    }
}

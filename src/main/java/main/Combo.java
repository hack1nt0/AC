package main;

import template.collection.sets.SetUtils;

import java.util.*;
import java.io.PrintWriter;

/*
 ID: hackint1
 LANG: JAVA
 TASK: combo
*/

public class Combo {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int duals = 3;
        int[] john = new int[duals];
        for (int i = 0; i < 3; ++i) john[i] = in.nextInt() - 1;
        int[] master = new int[duals];
        for (int i = 0; i < 3; ++i) master[i] = in.nextInt() - 1;
        long totJohn = 1;
        long totMaster = 1;
        long common = 1;
        for (int i = 0; i < 3; ++i) {
            HashSet<Integer> validJohn = new HashSet<>();
            validJohn.add(john[i]);
            for (int offset = 1; offset <= 2; ++offset) {
                validJohn.add((john[i] + offset) % n);
                validJohn.add((john[i] - offset + n) % n);
            }

            HashSet<Integer> validMaster = new HashSet<>();
            validMaster.add(master[i]);
            for (int offset = 1; offset <= 2; ++offset) {
                validMaster.add((master[i] + offset) % n);
                validMaster.add((master[i] - offset + n) % n);
            }
            totJohn *= validJohn.size();
            totMaster *= validMaster.size();
            //validJohn.retainAll(validMaster);
            common *= SetUtils.intersect(validJohn, validMaster);
            //System.out.printlnTable(Arrays.toString(validJohn.toArray(new Integer[0])));
        }

        long ans = totJohn + totMaster - common;
        out.println(ans);
    }
}

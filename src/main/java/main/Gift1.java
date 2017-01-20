package main;

import java.util.*;
import java.io.PrintWriter;

/*
 ID: hackint1
 LANG: JAVA
 TASK: Gift1
*/

public class Gift1 {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int N = in.nextInt();
        Map<String, Integer> ret = new HashMap<>();
        List<String> rank = new ArrayList<>();
        for (int i = 0; i < N; ++i) {
            String name = in.next();
            ret.put(name, 0);
            rank.add(name);
        }
        for (int i = 0; i < N; ++i) {
            String giver = in.next();
            int money = in.nextInt();
            int c = in.nextInt();
            if (c == 0) continue;
            for (int j = 0; j < c; ++j) {
                String receiver = in.next();
                ret.put(receiver, ret.get(receiver) + money / c);
            }
            ret.put(giver, ret.get(giver) - money / c * c);
        }

        for (int i = 0; i < N; ++i) out.println(rank.get(i) + " " + ret.get(rank.get(i)));
    }
}

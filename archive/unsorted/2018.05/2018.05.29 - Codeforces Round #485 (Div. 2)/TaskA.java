package main;

import java.util.*;
import java.io.PrintWriter;

public class TaskA {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        Map<String, String> map = new HashMap<>();
        map.put("purple", "Power");
        map.put("green", "Time");
        map.put("blue", "Space");
        map.put("orange", "Soul");
        map.put("red", "Reality");
        map.put("yellow", "Mind");
        int n = in.nextInt();
        Set<String> has = new HashSet<>();
        for (int i = 0; i < n; ++i)
            has.add(in.next());
        out.println(map.size() - has.size());
        for (String color : map.keySet())
            if (!has.contains(color))
                out.println(map.get(color));
    }
}

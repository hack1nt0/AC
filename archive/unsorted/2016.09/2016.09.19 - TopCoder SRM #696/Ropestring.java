package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Ropestring {
    public String makerope(String s) {
        String[] tmp = s.split("\\.");
        ArrayList<String> ropes = new ArrayList<String>();
        for (String rope: tmp) {
            if (rope.length() == 0) continue;
            ropes.add(rope);
        }
        Collections.sort(ropes, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int a = o1.length() % 2;
                int b = o2.length() % 2;
                if (a == 0 && b == 0 || a == 1 && b == 1)
                    return o2.length() - o1.length();
                if (a == 0) return -1;
                return 1;

            }
        });
        String ans = "";
        for (int i = 0; i < ropes.size(); ++i) {
            if (i > 0) ans += ".";
            ans += ropes.get(i);
        }
        while (ans.length() < s.length()) ans += ".";
        return ans;
    }
}

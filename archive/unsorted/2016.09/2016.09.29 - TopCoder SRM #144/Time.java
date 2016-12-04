package main;

import java.util.ArrayList;
import java.util.List;

public class Time {
    public String whatTime(int seconds) {
        int MOD = 60;
        List<Integer> l = new ArrayList<Integer>();
        for (int i = 0; i < 3; ++i) {
            l.add(seconds % MOD);
            seconds /= MOD;
        }
        String ans = "";
        for (int i = l.size() - 1; i >= 0; --i) {
            ans += l.get(i);
            if (i > 0) ans += ":";
        }
        return ans;
    }
}

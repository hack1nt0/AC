package main;

import template.collection.sets.BitUtils;

import java.util.*;
import java.io.PrintWriter;
/*
 ID: hackint1
 LANG: JAVA
 TASK: lamps
*/
public class Lamps {
    char ON = '1', OFF = '0';
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int c = in.nextInt();
        char[] finalState = new char[n];
        while (true) {
            int lamp = in.nextInt();
            if (lamp == -1) break;
            finalState[lamp - 1] = ON;
        }
        while (true) {
            int lamp = in.nextInt();
            if (lamp == -1) break;
            finalState[lamp - 1] = OFF;
        }
        List<String> ans = new ArrayList<>();
        for (int s = 0; s < 1 << 4; ++s) {
            int lampN = Integer.bitCount(s);
            if (lampN > c || (c - lampN) % 2 != 0) continue;
            char[] state = new char[n];
            Arrays.fill(state, ON);
            for (int button : BitUtils.elements(s)) {
                switch (button) {
                    case 0: {
                        for (int i = 0; i < n; ++i) flip(state, i);
                        break;
                    }
                    case 1: {
                        for (int i = 0; i < n; i += 2) flip(state, i);
                        break;
                    }
                    case 2: {
                        for (int i = 1; i < n; i += 2) flip(state, i);
                        break;
                    }
                    case 3: {
                        for (int i = 0; i < n; i += 3) flip(state, i);
                        break;
                    }
                }
            }
            boolean ok = true;
            for (int i = 0; i < n; ++i) {
                if (finalState[i] == 0) continue;
                if (finalState[i] != state[i]) {
                    ok = false;
                    break;
                }
            }
            if (ok) ans.add(new String(state));
        }

        if (ans.size() == 0) {
            out.println("IMPOSSIBLE");
            return;
        }
        Collections.sort(ans);
        for (String conf : ans) out.println(conf);
    }

    private void flip(char[] state, int i) {
        if (state[i] == ON) state[i] = OFF;
        else state[i] = ON;
    }
}

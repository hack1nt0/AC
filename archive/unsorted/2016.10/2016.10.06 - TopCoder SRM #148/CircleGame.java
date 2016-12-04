package main;

import java.util.HashMap;

public class CircleGame {
    public int cardsLeft(String deck) {
        String ans = deck;
        HashMap<Character, Integer> V = new HashMap<Character, Integer>();
        for (int i = '2'; i < '9' + 1; ++i) V.put((char)i, i - '1' + 1);
        V.put('A', 1);
        V.put('T', 10);
        V.put('J', 11);
        V.put('Q', 12);
        V.put('K', 13);
        while (true) {
            boolean upd = false;
            for (int i = 0; i < ans.length(); ++i) {
                if (ans.charAt(i) == 'K') {
                    ans = ans.substring(0, i) + ans.substring(i + 1);
                    upd = true;
                    break;
                } else {
                    int nxt = (i + 1) % ans.length();
                    if (V.get(ans.charAt(i)) + V.get(ans.charAt(nxt)) == 13) {
                        upd = true;
                        if (i < nxt)
                            ans = ans.substring(0, i) + (nxt + 1 < ans.length() ? ans.substring(nxt + 1) : "");
                        else
                            ans = ans.substring(nxt + 1, i);
                        break;
                    }
                }
            }
            if (!upd) break;
        }
        return ans.length();
    }
}

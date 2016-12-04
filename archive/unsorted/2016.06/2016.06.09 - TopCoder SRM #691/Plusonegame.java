package main;

import java.util.Arrays;

public class Plusonegame {
    public String getorder(String s) {
        String res = "";
        char[] cards = s.toCharArray();
        Arrays.sort(cards);
        int digitIdx = 0;
        for (;digitIdx < cards.length; ++digitIdx)
            if (cards[digitIdx] != '+') break;
        if (digitIdx >= cards.length) return s;

        int plus_cnt = digitIdx;
        int inc = 0;
        for (int i = 0; i < cards.length; ++i) {
            if (inc < plus_cnt && (digitIdx >= cards.length || inc < cards[digitIdx] - '0')) {
                res += "+";
                inc++;
                continue;
            }
            res += cards[digitIdx++];
        }
        return res;
    }
}

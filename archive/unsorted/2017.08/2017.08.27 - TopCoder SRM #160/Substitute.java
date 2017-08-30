package main;

import java.util.HashMap;
import java.util.Map;

public class Substitute {
    public int getValue(String key, String code) {
        Map<Character, Integer> order = new HashMap<>();
        for (int i = 0; i < key.length(); ++i) order.put(key.charAt(i), i + 1);
        int value = 0;
        for (char c : code.toCharArray()) {
            if (!order.containsKey(c)) continue;
            value = value * 10 + order.get(c) % 10;
        }
        return value;
    }
}

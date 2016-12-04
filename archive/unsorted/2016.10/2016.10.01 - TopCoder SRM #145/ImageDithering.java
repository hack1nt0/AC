package main;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class ImageDithering {
    public int count(String dithered, String[] screen) {
        Set<Character> diths = new HashSet<Character>();
        for (char c : dithered.toCharArray()) diths.add(c);
        int ans = 0;
        for (int i = 0; i < screen.length; ++i)
            for (int j = 0; j < screen[i].length(); ++j)
                if (diths.contains(screen[i].charAt(j))) ans++;
        return ans;
    }
}

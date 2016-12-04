package main;

public class CeyKaps {
    public String decipher(String typed, String[] switches) {
        char[] map = new char[128];
        for (int i = 'A'; i < 'Z' + 1; ++i) map[i] = (char)i;
        for (String sw : switches) {
            String[] tmp = sw.split("[:]");
            char a = tmp[0].charAt(0);
            char b = tmp[1].charAt(0);
            //swap(map, a, b);
            char t = map[a];
            map[a] = map[b];
            map[b] = t;
        }

        String ans = "";
        for (char c : typed.toCharArray()) {
            for (int i = 'A'; i < 'Z' + 1; ++i) if (map[i] == c)
                ans += (char)i;
        }

        return ans;
    }
}

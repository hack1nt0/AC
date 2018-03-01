package main;

public class Substitute {
    public int getValue(String key, String code) {
        int ans = 0;
        for (int i = 0; i < code.length(); ++i) {
            int p = key.indexOf(code.charAt(i));
            if (p != -1)
                ans = ans * 10 + (p + 1) % 10;
        }
        return ans;
    }
}

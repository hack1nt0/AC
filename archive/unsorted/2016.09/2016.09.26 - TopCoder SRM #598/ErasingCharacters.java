package main;

public class ErasingCharacters {
    public String simulate(String s) {
        String ans = s;
        while (true) {
            boolean find = false;
            for (int i = 0; i < ans.length() - 1; ++i) if (ans.charAt(i) == ans.charAt(i + 1)) {
                find = true;
                ans = ans.substring(0, i) + (i + 2 < ans.length() ? ans.substring(i + 2) : "");
            }
            if (!find) break;
        }

        return ans;
    }
}

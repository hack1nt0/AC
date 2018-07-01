package main;

public class Rochambo {
    public int wins(String opponent) {
        int ans = 0;
        for (int i = 0; i < opponent.length(); ++i) {
            char o = opponent.charAt(i);
            char s = 'R';
            if (i >= 2) {
                char guess = opponent.charAt(i - 1) == opponent.charAt(i - 2) ? opponent.charAt(i - 1) :
                        (char)('R' + 'P' + 'S' - opponent.charAt(i - 1) - opponent.charAt(i - 2));
                if (guess == 'R') s = 'P';
                if (guess == 'P') s = 'S';
                if (guess == 'S') s = 'R';
            }
            if (s == 'R' && o == 'S') ans++;
            if (s == 'P' && o == 'R') ans++;
            if (s == 'S' && o == 'P') ans++;
        }
        return ans;
    }
}

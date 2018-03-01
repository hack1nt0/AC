package main;

public class ProblemWriting {
    public String myCheckData(String dotForm) {
        if (!(1 <= dotForm.length() && dotForm.length() <= 25)) {
            return "dotForm must contain between 1 and 25 characters, inclusive.";
        }
        if (!Character.isDigit(dotForm.charAt(0))) {
            return "dotForm is not in dot notation, check character 0.";
        }
        int[][] state = new int[][] {
                {0, 1, -1},
                {1, -1, 2},
                {0, 1, -1},
        };
        int s = 2;
        int i = 1;
        while (i < dotForm.length()) {
            char c = dotForm.charAt(i);
            if (c == '.') {
                s = state[s][0];
            } else if ("+-*/".indexOf(c) >= 0) {
                s = state[s][1];
            } else {
                s = state[s][2];
            }
            if (s == -1) {
                return "dotForm is not in dot notation, check character " + i + ".";
            }
            i++;
        }
        if (s != 2) {
            return "dotForm is not in dot notation, check character " + dotForm.length() + ".";
        }
        return "";
    }
}

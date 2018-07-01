package main;

import java.util.Arrays;

public class Matching {
    public String[] findMatch(String[] first, String[] second) {
        int N = 4;
        String[][] CHARACTERS = {
                {"CIRCLE", "SQUIGGLE", "DIAMOND"},
                {"RED", "BLUE", "GREEN"},
                {"SOLID", "STRIPED", "EMPTY"},
                {"ONE", "TWO", "THREE"}
        };
        String[] third = new String[N];
        for (int i = 0; i < N; ++i) {
            int cFirst = Arrays.asList(CHARACTERS[i]).indexOf(first[i]);
            int cSecond = Arrays.asList(CHARACTERS[i]).indexOf(second[i]);
            if (cFirst == cSecond) {
                third[i] = first[i];
            } else {
                third[i] = CHARACTERS[i][3 - cFirst - cSecond];
            }
        }
        return third;
    }
}

package main;

public class DoubleString {
    public String check(String S) {
        String SQUARE = "square";
        String NOTSQUARE = "not square";
        if (S.length() % 2 == 1) return NOTSQUARE;
        for (int i = 0; i < S.length() / 2; ++i)
            if (S.charAt(i) != S.charAt(i + S.length() / 2)) return NOTSQUARE;
        return SQUARE;
    }
}

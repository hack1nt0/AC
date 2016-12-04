package main;

public class SquareFreeString {
    public String isSquareFree(String s) {
        String FREE = "square-free";
        String NOFREE = "not square-free";
        for (int L = 0; L < s.length(); ++L)
            for (int R = L + 2; R <= s.length(); ++R) {
                if ((R - L) % 2 != 0) continue;
                int M = (R + L) / 2;
                if (s.substring(L, M).equals(s.substring(M, R))) return NOFREE;
            }

        return FREE;
    }
}

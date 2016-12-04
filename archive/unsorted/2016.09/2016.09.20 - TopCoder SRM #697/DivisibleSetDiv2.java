package main;

public class DivisibleSetDiv2 {
    public String isPossible(int[] b) {
        String GOOD = "Possible";
        String BAD = "Impossible";
        int tmp = 1;
        for (int i = 0; i < b.length; ++i) tmp = lcm(tmp, b[i]);
        int sum = 0;
        for (int i = 0; i < b.length; ++i) sum += tmp / b[i];
        if (sum <= tmp) return GOOD;
        return BAD;
    }

    private int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }

    private int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}

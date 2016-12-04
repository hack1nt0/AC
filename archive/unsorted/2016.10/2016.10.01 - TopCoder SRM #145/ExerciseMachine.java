package main;

public class ExerciseMachine {
    public int getPercentages(String time) {
        int tots = 0;
        String[] tmp = time.split("[:]");
        for (int i = 0; i < tmp.length; ++i)
            tots = tots * 60 + Integer.valueOf(tmp[i]);

        int minP = lcm(tots, 100) / tots;
        int ans = 100 / minP;
        if (100 % minP == 0) ans -= 1;
        return ans;
    }

    private int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }

    private int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}

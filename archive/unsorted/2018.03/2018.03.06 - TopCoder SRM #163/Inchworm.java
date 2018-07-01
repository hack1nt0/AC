package main;

public class Inchworm {
    public int lunchtime(int branch, int rest, int leaf) {
        int lcm = rest * leaf / gcd(rest, leaf);
        return branch / lcm + 1;
    }

    int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}

package main;

public class BinaryCalculator {
    public int minimumSteps(int a, int b) {
        int min = 0;
        int diff = b - a;
        if (diff > 0) {
            min += diff / 3;
            min += diff % 3 * 2;
        } else if (diff < 0) {
            diff = -diff;
            min += diff / 2;
            min += diff % 2 * 3;
        }
        return min;
    }
}

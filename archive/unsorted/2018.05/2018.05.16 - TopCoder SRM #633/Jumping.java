package main;

import java.util.Arrays;

public class Jumping {
    public String ableToGet(int x, int y, int[] jumpLengths) {
        String YES = "Able";
        String NO = "Not able";
        double dxy = Math.sqrt(x * x + y * y);
        Arrays.sort(jumpLengths);
        double totJumps = Arrays.stream(jumpLengths).sum();
        if (totJumps < dxy) {
            return NO;
        }
        if (totJumps > dxy && jumpLengths.length == 1) {
            return NO;
        }
        double max = Arrays.stream(jumpLengths).max().getAsInt();
        max = Math.max(max, dxy);
        double sum = totJumps + dxy;
        return max <= sum / 2.0 ? YES : NO;
    }
}

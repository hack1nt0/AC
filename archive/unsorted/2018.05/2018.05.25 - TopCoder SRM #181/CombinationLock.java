package main;

public class CombinationLock {
    public double degreesTurned(int[] combo, int size, int start) {
        double tot = 0;
        for (int i = 0; i < combo.length; ++i) {
            tot += (combo.length - i) * 360;
            if (i % 2 == 0) {
                tot += (combo[i] - start + size) % size * 360. / size;
            } else {
                tot += (start - combo[i]+ size) % size * 360. / size;
            }
            start = combo[i];
        }
        return tot;
    }
}
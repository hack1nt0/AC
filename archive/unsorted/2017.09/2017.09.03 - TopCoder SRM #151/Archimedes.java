package main;

public class Archimedes {
    public double approximatePi(int numSides) {
        double angle = 2 * Math.PI / numSides;
        double sideLen = Math.sqrt(2 - 2 * Math.cos(angle));
        double pi = sideLen * numSides / 2;
        return pi;
    }
}

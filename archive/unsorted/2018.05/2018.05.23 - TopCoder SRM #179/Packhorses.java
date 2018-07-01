package main;

public class Packhorses {
    public int horses(int p, int x, int y) {
        int min = Integer.MAX_VALUE;
        for (int px = 0; px <= p; ++px) {
            int lx = Math.max(0, x - px * 2);
            int ly = Math.max(0, y - (p - px) * 1);
            min = Math.min(min, p + (lx + 2) / 3 + (ly + 1) / 2);
        }
        return min;
    }
}

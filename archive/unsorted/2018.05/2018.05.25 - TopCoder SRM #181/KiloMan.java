package main;

public class KiloMan {
    public int hitsTaken(int[] pattern, String jumps) {
        int hits = 0;
        for (int i = 0; i < pattern.length; ++i) {
            char c = jumps.charAt(i);
            if (c == 'J' && pattern[i] > 2 || c == 'S' && pattern[i] <= 2)
                hits++;
        }
        return hits;
    }
}

package main;

public class LevelUp {
    public int toNextLevel(int[] expNeeded, int received) {
        for (int i = 0; i < expNeeded.length; ++i) {
            if (received < expNeeded[i]) return expNeeded[i] - received;
        }
        return -1;
    }
}

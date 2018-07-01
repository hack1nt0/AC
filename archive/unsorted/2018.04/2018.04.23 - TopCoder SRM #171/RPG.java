package main;

public class RPG {
    public int[] dieRolls(String[] dice) {
        int max = 0, min = 0, average = 0;
        for (String d : dice) {
            String[] tmp = d.split("d");
            int n = Integer.valueOf(tmp[0]);
            int x = Integer.valueOf(tmp[1]);
            int dmax = n * x, dmin = n;
            max += dmax;
            min += dmin;
            average += dmax + dmin;
        }
        average /= 2;
        return new int[]{min, max, average};
    }
}

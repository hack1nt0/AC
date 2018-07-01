package main;

public class Orchard {
    public int[] nextTree(String[] orchard) {
        int r, c;
        r = c = -1;
        int distance = 0;
        int n = orchard.length;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (orchard[i].charAt(j) != '-') continue;
                int curDistance = Math.min(Math.min(i + 1, n - i), Math.min(j + 1, n - j));
                for (int it = 0; it < n; ++it)
                    for (int jt = 0; jt < n; ++jt)
                        if (orchard[it].charAt(jt) == 'T')
                            curDistance = Math.min(curDistance, Math.abs(it - i) + Math.abs(jt - j));
                if (curDistance > distance) {
                    distance = curDistance;
                    r = i; c = j;
                }
            }
        }
        return new int[]{r + 1, c + 1};
    }
}

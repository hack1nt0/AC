package main;

public class MultiplicationTable2Easy {
    public String isGoodSet(int[] table, int[] t) {
        String GOOD = "Good";
        String NOTGOOD = "Not Good";
        int n = (int) Math.sqrt(table.length);
        for (int i = 0; i < t.length; ++i)
            for (int j = 0; j < t.length; ++j) {
                int te = table[t[i] * n + t[j]];
                boolean find = false;
                for (int k = 0; k < t.length; ++k)
                    if (t[k] == te) find = true;
                if (!find) return NOTGOOD;
            }
        return GOOD;
    }
}

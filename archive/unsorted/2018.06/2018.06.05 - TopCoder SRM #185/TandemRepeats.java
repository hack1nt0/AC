package main;

public class TandemRepeats {
    public int maxLength(String dna, int k) {
        int n = dna.length();
        for (int len = n / 2; len >= 1; --len) {
            for (int i = 0; i + 2 * len <= n; ++i) {
                int j = i + len;
                int ndiff = 0;
                for (int o = 0; o < len; ++o)
                    if (dna.charAt(i + o) != dna.charAt(j + o))
                        ndiff++;
                if (ndiff <= k) {
                    return len;
                }
            }
        }
        return 0;
    }
}

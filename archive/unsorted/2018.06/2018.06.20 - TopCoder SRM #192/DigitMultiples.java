package main;

public class DigitMultiples {
    public int getLongest(String single, String multiple) {
        int max = 0;
        for (int ss = 0; ss < single.length(); ++ss) {
            for (int sm = 0; sm < multiple.length(); ++sm) {
                int m = -1;
                for (int o = 0; ss + o < single.length() && sm + o < multiple.length(); ++o) {
                    int ds = single.charAt(ss + o) - '0';
                    int dm = multiple.charAt(sm + o) - '0';
                    if (m == -1 && !(ds == 0 && dm != 0) || ds * m == dm) {
                        max = Math.max(max, o + 1);
                        if (!(ds == 0 && dm == 0) && m == -1)
                            m = dm / ds;
                    } else
                        break;
                }
            }
        }
        return max;
    }
}

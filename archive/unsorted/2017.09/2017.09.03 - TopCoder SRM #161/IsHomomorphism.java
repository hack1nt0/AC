package main;

import java.util.ArrayList;
import java.util.List;

public class IsHomomorphism {
    public String[] numBad(String[] source, String[] target, int[] mapping) {
        List<String> bads = new ArrayList<>();
        int len = source.length;
        for (int a = 0; a < len; ++a) {
            for (int b = 0; b < len; ++b) {
                if (mapping[source[a].charAt(b) - '0'] != target[mapping[a]].charAt(mapping[b]) - '0') {
                    bads.add("(" + a + "," + b + ")");
                }
            }
        }
        return bads.toArray(new String[0]);
    }
}

package main;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Spamatronic {
    public int[] filter(String[] knownSpam, String[] newMail) {
        Set<String> tokens = new HashSet<>();
        for (String spam : knownSpam) {
            tokens.addAll(extract(spam));
        }
        List<Integer> notSpam = new ArrayList<>();
        for (int i = 0; i < newMail.length; ++i) {
            Set<String> nTokens = extract(newMail[i]);
            int nSpam = 0;
            for (String token : nTokens)
                if (tokens.contains(token))
                    ++nSpam;
            if ((double)nSpam / nTokens.size() >= 0.75) {
                tokens.addAll(nTokens);
            } else {
                notSpam.add(i);
            }
        }
        int[] ans = new int[notSpam.size()];
        for (int i = 0; i < ans.length; ++i)
            ans[i] = notSpam.get(i);
        return ans;
    }

    private Set<String> extract(String spam) {
        Set<String> tokens = new HashSet<>();
        int p = 0;
        while (p < spam.length()) {
            while (p < spam.length() && !Character.isLetter(spam.charAt(p)))
                ++p;
            if (p == spam.length())
                break;
            int q = p + 1;
            while (q < spam.length() && Character.isLetter(spam.charAt(q)))
                ++q;
            tokens.add(spam.substring(p, q).toLowerCase());
            p = q;
        }
        return tokens;
    }
}

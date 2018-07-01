package main;

import java.io.StringReader;

public class Poetry {
    public String rhymeScheme(String[] poem) {
        int n = poem.length;
        String[] lastPatterns = new String[n];
        for (int i = 0; i < n; ++i) {
            String p = null;
            if (poem[i].trim().length() == 0) {
                p = "";
            } else {
                String[] ws = poem[i].trim().split("[ ]+");
                String w = ws[ws.length - 1];
                int j = w.length() - 1;
                while (!isVowel(w, j))
                    --j;
                while (0 <= j && isVowel(w, j))
                    --j;
                p = w.substring(j + 1);
            }

            lastPatterns[i] = toLower(p);
        }
        char[] scheme = new char[n];
        char s = 'a';
        for (int i = 0; i < n; ++i) {
            if (scheme[i] == 0) {
                if (lastPatterns[i].length() == 0)
                    scheme[i] = ' ';
                else {
                    for (int j = i; j < n; ++j)
                        if (lastPatterns[j].equals(lastPatterns[i]))
                            scheme[j] = s;
                    if (s == 'z')
                        s = 'A';
                    else
                        ++s;
                }
            }
        }
        return new String(scheme);
    }

    boolean isVowel(String w, int i) {
        char c = Character.toLowerCase(w.charAt(i));
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || (c == 'y' && 0 < i && i < w.length() - 1);
    }

    String toLower(String s) {
        char[] res = s.toCharArray();
        for (int i = 0; i < res.length; ++i)
            res[i] = Character.toLowerCase(res[i]);
        return new String(res);
    }
}

package main;

public class PrefixCode {
    public String isOne(String[] words) {
        for (int i = 0; i < words.length; ++i)
            for (int j = 0; j < words.length; ++j) {
                if (j == i || words[i].length() > words[j].length()) continue;
                int ncomm = Math.min(words[i].length(), words[j].length());
                if (words[i].substring(0, ncomm).equals(words[j].substring(0, ncomm)))
                    return "No, " + i;
            }
        return "Yes";
    }
}

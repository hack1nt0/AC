package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Twain {
    public String getNewSpelling(int year, String phrase) {
        if (phrase.trim().length() == 0)
            return phrase;
        List<String> spaces = new ArrayList<>(Arrays.asList(phrase.split("[a-z]+")));
        if (!phrase.endsWith(" "))
            spaces.add("");
        List<String> words = split(phrase);
        if (spaces.size() != words.size() + 1) {
            throw new RuntimeException();
        }
        String[] curPhrase = words.toArray(new String[0]);
        for (int i = 1; i <= year; ++i) {
            String[] newPhrase = new String[curPhrase.length];
            switch (i) {
                case 1: {
                    int j = 0;
                    for (String word : curPhrase) {
                        if (word.startsWith("x"))
                            word = "z" + word.substring(1);
                        word = word.replace("x", "ks");
                        newPhrase[j++] = word;
                    }
                    break;
                }
                case 2: {
                    int j = 0;
                    for (String word : curPhrase) {
                        newPhrase[j++] = word.replaceAll("y", "i");
                    }
                    break;
                }
                case 3: {
                    int k = 0;
                    for (String word : curPhrase) {
                        String newWord = "";
                        for (int j = 0; j < word.length(); ++j) {
                            char c = word.charAt(j);
                            if (j + 1 < word.length()) {
                                char c2 = word.charAt(j + 1);
                                if (c == 'c' && (c2 == 'e' || c2 == 'i')) {
                                    newWord += 's';
                                    continue;
                                }
                            }
                            newWord += c;
                        }
                        newPhrase[k++] = newWord;
                    }
                    break;
                }
                case 4: {
                    int k = 0;
                    for (String word : curPhrase) {
                        newPhrase[k++] = word.replaceAll("c+k", "k");
                    }
                    break;
                }
                case 5 : {
                    int k = 0;
                    for (String word : curPhrase) {
                        if (word.startsWith("sch"))
                            word = "sk" + word.substring(3);
                        word = word.replaceAll("chr", "kr");
                        String tmp = "";
                        for (int j = 0; j < word.length(); ++j) {
                            char c = word.charAt(j);
                            if (c == 'c' && (j + 1 == word.length() || word.charAt(j + 1) != 'h'))
                                tmp += 'k';
                            else
                                tmp += c;
                        }
                        newPhrase[k++] = tmp;
                    }
                    break;
                }
                case 6 : {
                    int k = 0;
                    for (String word : curPhrase) {
                        if (word.startsWith("kn"))
                            word = "n" + word.substring(2);
                        newPhrase[k++] = word;
                    }
                    break;
                }
                case 7 :
                    int m = 0;
                    for (String word : curPhrase) {
                        String newWord = "";
                        for (int j = 0; j < word.length(); ) {
                            char c = word.charAt(j);
                            newWord += c;
                            if (isNotVower(c)) {
                                int k = j + 1;
                                while (k < word.length() && word.charAt(k) == c)
                                    ++k;
                                j = k;
                            } else
                                ++j;
                        }
                        newPhrase[m++] = newWord;
                    }
                    break;
                }
                curPhrase = newPhrase;
        }
        String ans = "";
        for (int i = 0; i < words.size(); ++i) {
            ans += spaces.get(i);
            ans += curPhrase[i];
        }
        ans += spaces.get(spaces.size() - 1);
        return ans;
    }

    boolean isNotVower(char c) {
        return !(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u');
    }

    List<String> split(String s) {
        List<String> words = new ArrayList<>();
        for (int i = 0; i < s.length();) {
            while (i < s.length() && s.charAt(i) == ' ')
                ++i;
            if (i == s.length())
                break;
            int j = i + 1;
            while (j < s.length() && s.charAt(j) != ' ')
                ++j;
            words.add(s.substring(i, j));
            i = j;
        }
        return words;
    }
}

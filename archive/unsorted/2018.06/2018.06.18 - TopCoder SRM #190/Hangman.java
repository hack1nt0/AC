package main;

import template.collection.CollectionUtils;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Hangman {
    public String guessWord(String feedback, String[] words) {
        int nmatch = 0;
        String ans = null;
        for (String word : words) {
            if (feedback.length() == word.length()) {
                boolean match = true;
                Set<Character> set1 = new HashSet<>();
                Set<Character> set2 = new HashSet<>();
                for (int i = 0; i < feedback.length(); ++i) {
                    if (feedback.charAt(i) != '-' && feedback.charAt(i) != word.charAt(i))
                        match = false;
                    if (feedback.charAt(i) != '-')
                        set1.add(word.charAt(i));
                    if (feedback.charAt(i) == '-')
                        set2.add(word.charAt(i));
                }
                if (match && Collections.disjoint(set1, set2)) {
                    nmatch++;
                    ans = word;
                }
            }
        }
        return nmatch != 1 ? "" : ans;
    }
}

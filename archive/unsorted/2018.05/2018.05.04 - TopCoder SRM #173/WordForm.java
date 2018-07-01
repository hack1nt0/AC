package main;

public class WordForm {
    public String getSequence(String _word) {
        String word = _word.toUpperCase();
        boolean[] vowel = new boolean[word.length()];
        for (int i = 0; i < vowel.length; ++i)
            vowel[i] = isVowel(word, i);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < vowel.length;) {
            char c = vowel[i] ? 'V' : 'C';
            while (i + 1 < vowel.length && vowel[i + 1] == vowel[i])
                ++i;
            sb.append(c);
            ++i;
        }
        return sb.toString();
    }

    private boolean isVowel(String word, int i) {
        char c = word.charAt(i);
        return c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U' || (c == 'Y' && 0 < i && !isVowel(word, i - 1));
    }
}

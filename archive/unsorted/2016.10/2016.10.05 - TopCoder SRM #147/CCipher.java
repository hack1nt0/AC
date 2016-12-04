package main;

public class CCipher {
    public String decode(String cipherText, int shift) {
        String ans = "";
        for (int i = 0; i < cipherText.length(); ++i)
            ans += preChar(cipherText.charAt(i), shift);
        return ans;
    }

    private char preChar(char c, int shift) {
        return (char) ((c - 'A' - shift + 26) % 26 + 'A');
    }
}

package main;


public class TeXLeX {
    public int[] getTokens(String input) {
        char[] ans = input.toCharArray();
        while (true) {
            boolean updated = false;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < ans.length; ) {
                if (!updated && ans[i] == '^' && i + 4 <= ans.length && ans[i + 1] == '^' && isHex(ans[i + 2]) && isHex(ans[i + 3])) {
                    sb.append(fromHex(ans[i + 2], ans[i + 3]));
                    i += 4;
                    updated = true;
                }
                else if (!updated && ans[i] == '^' && i + 3 <= ans.length && ans[i + 1] == '^') {
                    if (ans[i + 2] > 63)
                        sb.append((char)(ans[i + 2] - 64));
                    if (ans[i + 2] < 64)
                        sb.append((char)(ans[i + 2] + 64));
                    i += 3;
                    updated = true;
                }
                else {
                    sb.append(ans[i]);
                    i += 1;
                }
            }
            if (!updated)
                break;
            ans = sb.toString().toCharArray();
        }
        int[] intAns = new int[ans.length];
        for (int i = 0; i < ans.length; ++i)
            intAns[i] = ans[i];
        return intAns;
    }

    private char fromHex(char a, char b) {
        return (char)(value(a) * 16 + value(b));
    }

    private int value(char c) {
        if ('0' <= c && c <= '9')
            return c - '0';
        if ('a' <= c && c <= 'f')
            return c - 'a' + 10;
        throw new RuntimeException();
    }

    private boolean isHex(char c) {
        return '0' <= c && c <= '9' || 'a' <= c && c <= 'f';
    }
}

package main;

public class Justifier {
    public String[] justify(String[] textIn) {
        int maxLen = 0;
        for (int i = 0; i < textIn.length; ++i) maxLen = Math.max(maxLen, textIn[i].length());
        String[] ans = new String[textIn.length];
        for (int i = 0; i < textIn.length; ++i) {
            StringBuffer sb = new StringBuffer(textIn[i]).reverse();
            for (int j = 0; j < maxLen - textIn[i].length(); ++j) sb.append(' ');
            ans[i] = sb.reverse().toString();
        }
        return ans;
    }
}

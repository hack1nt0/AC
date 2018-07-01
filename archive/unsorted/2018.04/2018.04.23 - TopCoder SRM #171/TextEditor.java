package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TextEditor {
    public String[] twoColumn(String[] text, int width) {
        List<String> words = new ArrayList<>();
        for (String line : text) {
            if (line.trim().length() == 0)
                continue;
            words.addAll(Arrays.asList(line.trim().split("[ ]+")));
        }
        List<String> page = new ArrayList<>();
        StringBuffer sb = new StringBuffer();
        for (String word : words) {
            int nlen = sb.length() == 0 ? word.length() : sb.length() + 1 + word.length();
            if (nlen > width) {
                page.add(sb.toString());
                sb.setLength(0);
                sb.append(word);
            } else {
                sb.append(sb.length() == 0 ? word : " " + word);
            }
        }
        if (sb.length() > 0)
            page.add(sb.toString());
        String[] ans = new String[page.size()];
        int half = (page.size() + 1) / 2;
        for (int i = 0; i < half; ++i) {
            ans[2 * i] = page.get(i);
            if (i + half < page.size())
                ans[2 * i + 1] = page.get(i + half);
        }
        return ans;
    }
}

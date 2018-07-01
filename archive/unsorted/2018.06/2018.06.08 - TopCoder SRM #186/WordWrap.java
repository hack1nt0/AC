package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WordWrap {
    public String[] justify(String[] lines, int columnWidth) {
        List<String> tokens = new ArrayList<>();
        for (int i = 0; i < lines.length; ++i) {
            tokens.addAll(Arrays.asList(lines[i].trim().split("[ ]+")));
        }
        StringBuilder sb = new StringBuilder();
        List<String> ans = new ArrayList<>();
        int it = 0;
        while (it < tokens.size()) {
            while (it < tokens.size() && sb.length() + tokens.get(it).length() <= columnWidth) {
                sb.append(tokens.get(it++) + " ");
            }
            ans.add(sb.toString().trim());
            sb.setLength(0);
        }
        return ans.toArray(new String[0]);
    }
}

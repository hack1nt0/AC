package main;

import java.util.HashSet;
import java.util.Set;

public class UserId {
    public String id(String[] inUse, String first, String middle, String last) {
        first = shrink(first);
        middle = shrink(middle);
        last = shrink(last);
        if (first == null || middle == null || last == null || first.length() < 2 || last.length() < 2)
            return "BAD DATA";
        Set<String> used = new HashSet<>();
        for (String n : inUse) used.add(n);
        String ans = "" + first.charAt(0) + last;
        ans = ans.substring(0, Math.min(8, ans.length()));
        if (!used.contains(ans))
            return ans;
        if (middle.length() > 0) {
            ans = "" + first.charAt(0) + middle.charAt(0) + last;
            ans = ans.substring(0, Math.min(8, ans.length()));
            if (!used.contains(ans))
                return ans;
        }
        for (int i = 1; i < 100; ++i) {
            ans = "" + first.charAt(0) + last;
            ans = ans.substring(0, Math.min(6, ans.length()));
            ans += (i / 10) + "" + (i % 10);
            if (!used.contains(ans))
                return ans;
        }
        return "";
    }

    String shrink(String s) {
        StringBuffer res = new StringBuffer();
        for (char c : s.toCharArray()) {
            if (Character.isLetter(c))
                res.append(Character.toLowerCase(c));
            else if (c != ' ' && c != '\'')
                return null;
        }
        return res.toString();
    }
}

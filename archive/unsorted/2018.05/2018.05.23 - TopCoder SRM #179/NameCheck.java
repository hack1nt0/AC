package main;

import java.util.ArrayList;
import java.util.List;

public class NameCheck {
    public String[] formatList(String[] namelist) {
        List<String> list = new ArrayList<>();
        for (String name : namelist) {
            if (name.trim().length() != name.length())
                continue;
            String[] tmp = name.split("[ ]+");
            if (tmp.length != 2 && tmp.length != 3)
                continue;
            boolean good = true;
            for (int i = 0; i < tmp.length; ++i) {
                if (i < tmp.length - 1 && (isInitial(tmp[i]) || isName(tmp[i])) || isName(tmp[i]))
                    continue;
                else {
                    good = false;
                    break;
                }
            }
            if (good) {
                StringBuilder sb = new StringBuilder();
                for (String part : tmp) {
                    String np = "";
                    for (int i = 0; i < part.length(); ++i)
                        if (i == 0) np += Character.toUpperCase(part.charAt(i));
                        else np += Character.toLowerCase(part.charAt(i));
                    sb.append((sb.length() > 0 ? " " : "") + np);
                }
                list.add(sb.toString());
            }
        }
        return list.toArray(new String[0]);
    }

    private boolean isInitial(String s) {
        return s.length() == 2 && Character.isLetter(s.charAt(0)) && s.charAt(1) == '.';
    }

    private boolean isName(String s) {
        for (int i = 0; i < s.length(); ++i)
            if (!Character.isLetter(s.charAt(i)))
                return false;
        return s.length() >= 2;
    }
}

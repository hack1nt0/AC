package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class PartySeats {
    public String[] seating(String[] attendees) {
        List<String> girls = new ArrayList<>();
        List<String> boys = new ArrayList<>();
        for (String att : attendees) {
            String[] tmp = att.split(" ");
            if (tmp[1].equals("girl"))
                girls.add(tmp[0]);
            else
                boys.add(tmp[0]);
        }
        if (girls.size() == boys.size() && girls.size() >= 2 && girls.size() % 2 == 0) {
            Collections.sort(girls);
            Collections.sort(boys);
            List<String> ans = new LinkedList<>();
            ans.add("HOST");
            boolean left = true;
            for (int i = 0; i < girls.size(); ++i) {
                if (left) {
                    ans.add(girls.get(i));
                    ans.add(boys.get(i));
                } else {
                    ans.add(boys.get(i));
                    ans.add(girls.get(i));
                }
                if ((i + 1) * 2 == girls.size()) {
                    ans.add("HOSTESS");
                    left = false;
                }
            }
            return ans.toArray(new String[0]);
        }
        return new String[0];
    }
}

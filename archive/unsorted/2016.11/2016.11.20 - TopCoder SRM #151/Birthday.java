package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Birthday {
    public String getNext(String date, String[] birthdays) {
        List<String> births = new ArrayList<String>();
        for (int i = 0; i < birthdays.length; ++i) births.add(birthdays[i].split("[ ]")[0]);
        Collections.sort(births);
        //if (date.compareTo(births.get(births.size() - 1)) > 0) return births.get(0);
        for (String b : births) if (date.compareTo(b) <= 0) return b;
        return births.get(0);
    }
}

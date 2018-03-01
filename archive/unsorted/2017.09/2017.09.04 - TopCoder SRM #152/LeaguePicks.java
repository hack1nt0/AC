package main;

import java.util.ArrayList;
import java.util.List;

public class LeaguePicks {
    public int[] returnPicks(int position, int friends, int picks) {
        if (picks < position) {
            return new int[0];
        }
        List<Integer> result = new ArrayList<>();
        int which = position;
        result.add(which);
        int span = (friends - position) * 2 + 1;
        while (which + span <= picks) {
            which += span;
            result.add(which);
            span = 2 * friends - span;
        }
        return result.stream().mapToInt(i -> i).toArray();
    }
}

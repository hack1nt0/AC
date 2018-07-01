package main;

import java.util.HashMap;
import java.util.Map;

public class GolfScore {
    public int tally(int[] parValues, String[] scoreSheet) {
        Map<String, Integer> map = new HashMap<>();
        map.put("triple bogey", +3);  //three strokes over par
        map.put("double bogey", +2); //  two strokes over par
        map.put("bogey", +1); //         one stroke over par
        map.put("par", 0);       //    exactly par
        map.put("birdie", -1); //        one stroke under par
        map.put("eagle", -2); //         //two strokes under par
        map.put("albatross", -3);//     three strokes under par
        map.put("hole in one", -4);//  exactly one stroke
        int score = 0;
        for (int i = 0; i < parValues.length; ++i) {
            score += Math.max(1, parValues[i] + map.get(scoreSheet[i]));
        }
        return score;
    }
}

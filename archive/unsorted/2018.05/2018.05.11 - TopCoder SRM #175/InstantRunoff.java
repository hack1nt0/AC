package main;

import java.util.Arrays;

public class InstantRunoff {
    public String outcome(String candidates, String[] ballots) {
        int[] votes = new int[128];
        for (int order = 0; order < candidates.length(); ++order) {
            for (int voter = 0; voter < ballots.length; ++voter) {
                char which = ballots[voter].charAt(order);
                if (order > 0 && votes[which] == 0)
                    continue;
                votes[which]++;
            }
            for (char candidate : candidates.toCharArray())
                if (votes[candidate] > ballots.length / 2)
                    return candidate + "";
            int minVotes = Integer.MAX_VALUE;
            for (char candidate : candidates.toCharArray()) {
                if (votes[candidate] > 0)
                    minVotes = Math.min(minVotes, votes[candidate]);
            }
            if (minVotes == Integer.MAX_VALUE)
                return "";
            for (char candidate : candidates.toCharArray()) {
                if (votes[candidate] == minVotes)
                    votes[candidate] = 0;
            }
        }

        return "";
    }
}

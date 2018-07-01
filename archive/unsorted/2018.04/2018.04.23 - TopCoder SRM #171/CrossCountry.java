package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CrossCountry {
    class Team implements Comparable<Team> {
        char name;
        List<Integer> ranks = new ArrayList<>();

        boolean valid(int n) { return ranks.size() >= n; }

        int score(int n) {
            int res = 0;
            for (int i = 0; i < n; ++i)
                res += ranks.get(i);
            return res;
        }

        @Override
        public int compareTo(Team o) {
            for (int first = 5; first <= 6; ++first) {
                if (valid(first) && o.valid(first)) {
                    int s1 = score(first);
                    int s2 = o.score(first);
                    if (s1 != s2)
                        return s1 - s2;
                }
                else if (!valid(first))
                    return +1;
                else if (!o.valid(first))
                    return -1;
                else
                    return first == 5 ? 0 : name - o.name;
            }
            throw new RuntimeException();
        }
    }

    public String scoreMeet(int numTeams, String finishOrder) {
        Team[] teams = new Team[numTeams];
        for (int i = 0; i < numTeams; ++i) {
            teams[i] = new Team();
            teams[i].name = (char)('A' + i);
        }
        for (int i = 0; i < finishOrder.length(); ++i) {
            int iteam = finishOrder.charAt(i) - 'A';
            teams[iteam].ranks.add(i + 1);
        }
        Arrays.sort(teams);
        String ans = "";
        for (Team team : teams)
            if (team.valid(5))
                ans += team.name;
        return ans;
    }
}

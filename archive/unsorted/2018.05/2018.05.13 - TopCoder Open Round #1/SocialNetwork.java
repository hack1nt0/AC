package main;

public class SocialNetwork {
    public double averageFriends(String[] interests) {
        double tot = 0;
        for (int i = 0; i < interests.length; ++i) {
            for (int j = i + 1; j < interests.length; ++j) {
                for (char c : interests[i].toCharArray())
                    if (interests[j].indexOf(c) != -1) {
                        tot++;
                        break;
                    }
            }
        }
        return tot * 2 / interests.length;
    }
}

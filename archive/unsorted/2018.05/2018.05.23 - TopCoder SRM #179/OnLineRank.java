package main;

public class OnLineRank {
    public int calcRanks(int k, int[] scores) {
        int sum = 0;
        for (int i = 0; i < scores.length; ++i) {
            int rank = 1;
            for (int j = i - 1; j >= 0 && i - j <= k; --j)
                if (scores[j] > scores[i])
                    ++rank;
            sum += rank;
        }
        return sum;
    }
}

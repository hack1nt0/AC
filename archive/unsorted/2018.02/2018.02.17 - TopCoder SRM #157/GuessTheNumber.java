package main;

public class GuessTheNumber {
    public int noGuesses(int upper, int answer) {
        int res = 0;
        int lower = 1;
        while (true) {
            res++;
            int mid = (lower + upper) / 2;
            if (mid == answer) break;
            if (mid < answer) lower = mid + 1;
            else upper = mid - 1;
        }
        return res;
    }
}

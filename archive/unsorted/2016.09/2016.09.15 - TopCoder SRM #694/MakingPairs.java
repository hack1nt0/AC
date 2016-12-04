package main;

public class MakingPairs {
    public int get(int[] card) {
        int res = 0;
        for (int n: card) {
            res += n / 2;
        }
        return res;
    }
}

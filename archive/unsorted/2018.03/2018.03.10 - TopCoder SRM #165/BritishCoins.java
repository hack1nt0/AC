package main;

public class BritishCoins {
    public int[] coins(int pence) {
        int[] ans = new int[3];
        ans[0] = pence / (20 * 12);
        ans[1] = pence / 12 % 20;
        ans[2] = pence % 12;
        return ans;
    }
}

package main;

public class BigBurger {
    public int maxWait(int[] arrival, int[] service) {
        int ans = 0;
        int T = 0;
        for (int i = 0; i < arrival.length; ++i) {
            T = Math.max(arrival[i], T);
            ans = Math.max(ans, T - arrival[i]);
            T += service[i];
        }

        return ans;
    }
}

package main;

public class PeopleCircle {
    public String order(int numMales, int numFemales, int K) {
        char[] ans = new char[numFemales + numMales];
        int S = 0;
        int N = numFemales + numMales;

        for (int i = 0; i < numFemales; ++i) {
            int curL = N - i;
            int P = (K - 1 + curL) % curL;
            for (int j = i; j > 0; --j) {
                P = (P + K) % (N - j + 1);
            }
            ans[P] = 'F';
        }
        for (int i = 0; i < ans.length; ++i) if (ans[i] != 'F') ans[i] = 'M';
        return new String(ans);
    }
}

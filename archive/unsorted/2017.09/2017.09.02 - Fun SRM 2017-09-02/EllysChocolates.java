package main;

public class EllysChocolates {
    public int getCount(int P, int K, int N) {
        int count = N / P;
        int wrappers = count;
        while (wrappers >= K) {
            count += wrappers / K;
            wrappers = wrappers % K + wrappers / K;
        }
        return count;
    }
}

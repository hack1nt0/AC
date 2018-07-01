package main;

public class EngineersPrimes {
    public long smallestNonPrime(int N) {
        int count = 0;
        int MAXN = N * 100;
        boolean[] visited = new boolean[MAXN];
        for (int i = 2; i < MAXN; ++i) {
            if (!visited[i]) {
                count++;
                if (count == N + 1)
                    return (long)i * i;
                for (int j = i; j < MAXN; j += i)
                    visited[j] = true;
            }
        }
        throw new RuntimeException();
    }
}

package main;

public class GerrymanderEasy {
    public double getmax(int[] A, int[] B, int K) {
        int N = A.length;
        double[] accA = new double[N + 1];
        double[] accB = new double[N + 1];
        for (int i = 1; i <= N; ++i) {
            accA[i] = accA[i - 1] + A[i - 1];
            accB[i] = accB[i - 1] + B[i - 1];
        }
        double maxSup = 0;
        for (int i = 0; i < N; ++i)
            for (int j = i + K; j <= N; ++j) {
                double X = accB[j] - accB[i];
                double Y = accA[j] - accA[i];
                maxSup = Math.max(maxSup, X / Y);
            }
        return maxSup;
    }
}

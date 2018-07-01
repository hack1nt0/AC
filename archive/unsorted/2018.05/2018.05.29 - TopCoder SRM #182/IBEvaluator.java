package main;

public class IBEvaluator {
    public int[] getSummary(int[] predictedGrades, int[] actualGrades) {
        int n = actualGrades.length;
        int[] diff = new int[n];
        for (int i = 0; i < n; ++i) diff[i] = Math.abs(predictedGrades[i] - actualGrades[i]);
        int[] ans = new int[7];
        for (int d = 0; d < 7; ++d) {
            int count = 0;
            for (int i = 0; i < n; ++i)
                if (d == diff[i])
                    ++count;
            ans[d] = (int)((float)count / n * 100);
        }
        return ans;
    }
}

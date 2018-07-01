package main;

import template.collection.sequence.ArrayUtils;
import template.debug.InputReader;
import template.debug.OutputWriter;

import java.util.Arrays;

public class TaskF {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        Task[] tasks = new Task[n];
        for (int i = 0; i < n; ++i) tasks[i] = new Task();
        for (int i = 0; i < n; ++i) tasks[i].power = in.readInt();
        for (int i = 0; i < n; ++i) tasks[i].cpus = in.readInt();
        Arrays.sort(tasks, (i, j) -> j.power - i.power);
        for (int i = 0; i < n; ++i) {
            tasks[i].equals++;
            if (i > 0 && tasks[i].power == tasks[i - 1].power)
                tasks[i].equals += tasks[i - 1].equals;
            tasks[i].acc = tasks[i].cpus;
            if (i > 0)
                tasks[i].acc += tasks[i - 1].acc;
        }
        long oo = Long.MAX_VALUE;
        long[][][][] dp = new long[2][tasks[n - 1].acc + 1][n + 1][n + 1];
        ArrayUtils.fill(dp, oo);
        dp[0][tasks[0].cpus][1][1] = tasks[0].power;
        dp[0][0][0][0] = oo;
        for (int i = 1; i < n; ++i) {
            int cur = i % 2, pre = cur ^ 1;
            for (int k = 0; k <= tasks[i].acc; ++k) {
                for (int left = 0; left <= i + 1; ++left) {
                    if (tasks[i].power == tasks[i - 1].power) {
                        for (int ne = 0; ne <= Math.min(left, tasks[i].equals); ++ne) {
                            long res = oo;
                            if (left < n && left + 1 - ne > 0)
                                res = Math.min(res, dp[pre][k][left + 1][ne]);
                            if (k >= tasks[i].cpus && left > 0 && ne > 0 && dp[pre][k - tasks[i].cpus][left - 1][ne - 1] != oo)
                                res = Math.min(res, dp[pre][k - tasks[i].cpus][left - 1][ne - 1] + tasks[i].power);
                            dp[cur][k][left][ne] = res;
                        }
                    } else {
                        if (left < n) {
                            long res = oo;
                            for (int pne = 0; pne <= Math.min(left + 1, tasks[i - 1].equals); ++pne) {
                                res = Math.min(res, dp[pre][k][left + 1][pne]);
                            }
                            dp[cur][k][left][0] = res;
                        }
                        if (left > 0) {
                            long res = oo;
                            for (int pne = 0; pne <= Math.min(left - 1, tasks[i - 1].equals); ++pne) {
                                if (k >= tasks[i].cpus && dp[pre][k - tasks[i].cpus][left - 1][pne] != oo)
                                    res = Math.min(res, dp[pre][k - tasks[i].cpus][left - 1][pne] + tasks[i].power);
                            }
                            dp[cur][k][left][1] = res;
                        }
                    }
                }
            }
        }
        double min = Double.MAX_VALUE;
        for (int k = 1; k <= tasks[n - 1].acc; ++k)
            for (int left = 0; left <= n; ++left)
                for (int ne = 0; ne <= Math.min(left, tasks[n - 1].equals); ++ne)
                    if (dp[(n - 1) % 2][k][left][ne] != oo) {
                        long t = Math.round((double) dp[(n - 1) % 2][k][left][ne] / k * 1000);
//                        if (t <= 705)
//                            System.out.println(t + " " + k + " " + left + " " + ne);
                        min = Math.min(min, (double) dp[(n - 1) % 2][k][left][ne] / k);
                    }
        out.printLine(Math.round(min * 1000));
    }

    class Task {
        int power, cpus, equals, acc;
    }
}

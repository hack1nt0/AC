package main;

import template.collection.sequence.ArrayUtils;

import java.util.Arrays;

public class ProgressBar {
    public String showProgress(int[] taskTimes, int tasksCompleted) {
        double s = Arrays.stream(taskTimes).sum();
        double f = Arrays.stream(taskTimes, 0, tasksCompleted).sum();
        int n = (int)Math.floor(f / s * 20);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 20; ++i)
            sb.append(i < n ? '#' : '.');
        return sb.toString();
    }
}

package main;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;

//WA
public class TeamPhoto {
    class Person implements Comparable<Person>{
        int height;
        int tag;
        Person(int height, int tag) {
            this.height = height;
            this.tag = tag;
        }

        @Override
        public int compareTo(Person o) {
            return height - o.height;
        }
    }
    public int minDiff(int[] height) {
        int n = height.length;
        Person[] line = new Person[n];
        for (int i = 0; i < n; ++i) line[i] = new Person(height[i], i);
        Arrays.sort(line);
        if (height[1] < height[2]) {
            swap(line, 0, which(line, 1));
            swap(line, n - 1, which(line, 2));
        } else {
            swap(line, 0, which(line, 2));
            swap(line, n - 1, which(line, 1));
        }
        if (n % 2 == 1) {
            swap(line, which(line, 0), n / 2);
        } else {
            if (height[0] < line[n / 2 - 1].height)
                swap(line, which(line, 0), n / 2 - 1);
            else if (line[n / 2].height < height[0])
                swap(line, which(line, 0), n / 2);
        }
        int diff = 0;
        for (int i = 1; i < n; ++i)
            diff += Math.abs(line[i].height - line[i - 1].height);
        return diff;
    }

    void swap(Person[] line, int i, int j) {
        Person t = line[i];
        line[i] = line[j];
        line[j] = t;
    }

    int which(Person[] line, int tag) {
        for (int i = 0; i < line.length; ++i)
            if (line[i].tag == tag)
                return i;
        throw new RuntimeException();
    }
}

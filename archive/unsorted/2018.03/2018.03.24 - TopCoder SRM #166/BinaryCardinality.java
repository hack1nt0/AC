package main;

import template.collection.sequence.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class BinaryCardinality {
    public int[] arrange(int[] numbers_) {
        Integer[] numbers = (Integer[]) ArrayUtils.inbox(numbers_);
        Arrays.sort(numbers, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (Integer.bitCount(o1) != Integer.bitCount(o2))
                    return Integer.bitCount(o1) - Integer.bitCount(o2);
                return o1 - o2;
            }
        });
        return ArrayUtils.unbox(numbers);
    }
}

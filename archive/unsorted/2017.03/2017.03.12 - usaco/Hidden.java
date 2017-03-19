package main;



import template.collection.sequence.ArrayUtils;
import template.debug.InputReader;
import template.string.StringUtils;

import java.io.PrintWriter;
import java.util.*;

/*
 ID: hackint1
 LANG: JAVA
 TASK: hidden
*/

/**
 * Cyclic Minimum Representation of String
 */
public class Hidden {
    int lenPower;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        solve2(testNumber, in, out);
    }

    /**
     * Executing...
     Test 1: TEST OK [0.086 secs, -1194644 KB]
     Test 2: TEST OK [0.079 secs, -1194644 KB]
     Test 3: TEST OK [0.086 secs, -1194644 KB]
     Test 4: TEST OK [0.086 secs, -1194644 KB]
     Test 5: TEST OK [0.086 secs, -1194644 KB]
     Test 6: TEST OK [0.086 secs, -1194644 KB]
     Test 7: TEST OK [0.108 secs, -1194644 KB]
     Test 8: TEST OK [0.151 secs, -1194644 KB]
     Test 9: TEST OK [0.194 secs, -1194644 KB]
     Test 10: TEST OK [0.173 secs, -1194644 KB]
     Test 11: TEST OK [0.151 secs, -1194644 KB]
     Test 12: TEST OK [0.122 secs, -1194644 KB]
     Test 13: TEST OK [0.086 secs, -1194644 KB]
     Test 14: TEST OK [0.130 secs, -1194644 KB]

     All tests OK.
     * @param testNumber
     * @param in
     * @param out
     */
    public void solve2(int testNumber, InputReader in, PrintWriter out) {
        int n = in.readInt();
        StringBuilder stringBuilder = new StringBuilder();
        while (!in.isExhausted()) {
            stringBuilder.append(in.readString());
        }
        int from = StringUtils.cyclicMin(stringBuilder);
        out.println(from);
    }

    /**
     * Executing...
     Test 1: TEST OK [0.086 secs, -1194644 KB]
     Test 2: TEST OK [0.086 secs, -1194644 KB]
     Test 3: TEST OK [0.086 secs, -1194644 KB]
     Test 4: TEST OK [0.115 secs, -1194644 KB]
     Test 5: TEST OK [0.086 secs, -1194644 KB]
     Test 6: TEST OK [0.086 secs, -1194644 KB]
     Test 7: TEST OK [0.115 secs, -1194644 KB]
     Test 8: TEST OK [0.158 secs, -1194644 KB]
     Test 9: TEST OK [0.187 secs, -1194644 KB]
     Test 10: TEST OK [0.216 secs, -1194644 KB]
     Test 11: TEST OK [0.281 secs, -1194644 KB]
     Test 12: TEST OK [0.122 secs, -1194644 KB]
     Test 13: TEST OK [0.086 secs, -1228444 KB]
     Test 14: TEST OK [0.634 secs, -1194644 KB]

     All tests OK.
     * @param testNumber
     * @param in
     * @param out
     */
    public void solve1(int testNumber, InputReader in, PrintWriter out) {
        int n = in.readInt();
        StringBuilder stringBuilder = new StringBuilder();
        while (!in.isExhausted()) {
            stringBuilder.append(in.readString());
        }
        stringBuilder.append(stringBuilder);
        List<Integer> starts = new ArrayList<>();
        int min = 'z';
        for (int i = 0; i < n; ++i) min = Math.min(min, stringBuilder.charAt(i));
        for (int i = 0; i < n; ++i) if (stringBuilder.charAt(i) == min && (i == 0 || stringBuilder.charAt(i - 1) > min)) starts.add(i);
        if (starts.size() < n / 2) out.println(solveStupidly(starts, stringBuilder));
        else out.println(solveSuffixArray(stringBuilder));
    }

    public int solveSuffixArray(StringBuilder stringBuilder) {
        int n = stringBuilder.length() / 2;
        Integer[][] rank = new Integer[(int)Math.ceil(Math.log(n) / Math.log(2)) + 1][n * 2];
//        List<Integer> index = new ArrayList<>();
//        for (int i = 0; i < n * 2; ++i) index.add(i);
        Integer[] index = (Integer[]) ArrayUtils.inbox(ArrayUtils.index(n * 2));
//        int[] index = ArrayUtils.index(n * 2);
//        int[] aux = new int[n * 2];

        Comparator<Integer> comparator1 = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                char c1 = stringBuilder.charAt(o1);
                char c2 = stringBuilder.charAt(o2);
                if (c1 > c2) return +1;
                if (c1 < c2) return -1;
                if (o1 > o2) return +2;
                if (o1 < o2) return -2;
                return 0;
            }
        };

        Comparator<Integer> comparator2 = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                int lastLenPower = lenPower - 1;
                int rank11 = rank[lastLenPower][o1];
                int rank21 = rank[lastLenPower][o2];
                if (rank11 > rank21) return +1;
                if (rank11 < rank21) return -1;
                int rank12 = o1 + (1 << lastLenPower) < n * 2 ? rank[lastLenPower][o1 + (1 << lastLenPower)] : -1;
                int rank22 = o2 + (1 << lastLenPower) < n * 2 ? rank[lastLenPower][o2 + (1 << lastLenPower)] : -1;
                if (rank12 > rank22) return +1;
                if (rank12 < rank22) return -1;
                if (o1 > o2) return +2;
                if (o1 < o2) return -2;
                return 0;
            }
        };

        for (lenPower = 0; 1 << lenPower <= n; ++lenPower) {
            Comparator<Integer> comparator = lenPower == 0 ? comparator1 : comparator2;
            Arrays.sort(index, 0, n * 2 - (1 << lenPower), comparator);
            //ArrayUtils.printlnConcisely(index);
            for (int i = 0; i < index.length; ++i) {
                if (i > 0 && Math.abs(comparator.compare(index[i - 1], index[i])) == 2) rank[lenPower][index[i]] = rank[lenPower][index[i - 1]];
                else rank[lenPower][index[i]] = i;
            }
            //ArrayUtils.printlnConcisely(rank[lenPower]);

//            if (lenPower == 0) {
//                ArrayUtils.IntToIntMapper mapper = new ArrayUtils.IntToIntMapper() {
//                    @Override
//                    public int map(int i) {
//                        return stringBuilder.charAt(i);
//                    }
//                };
//                ArrayUtils.countingSort(index, mapper, 128, aux);
//                for (int i = 0; i < index.length; ++i) {
//                    if (i > 0 && stringBuilder.charAt(index[i - 1]) == stringBuilder.charAt(index[i])) rank[lenPower][index[i]] = rank[lenPower][index[i - 1]];
//                    else rank[lenPower][index[i]] = i;
//                }
//            } else {
//                final int lastLenPower = lenPower - 1;
//                ArrayUtils.IntToIntMapper mapper1 = new ArrayUtils.IntToIntMapper() {
//                    @Override
//                    public int map(int i) {
//                        return rank[lastLenPower][i];
//                    }
//                };
//                ArrayUtils.IntToIntMapper mapper2 = new ArrayUtils.IntToIntMapper() {
//                    @Override
//                    public int map(int i) {
//                        return i + (1 << lastLenPower) < 2 * n ? rank[lastLenPower][i + (1 << lastLenPower)] : -1;
//                    }
//                };
//                int W = n * 2 + 1;
//                ArrayUtils.countingSort(index, mapper1, W, aux);
//                ArrayUtils.printlnConcisely(index);
//                ArrayUtils.printlnConcisely(rank[lenPower - 1]);
//                ArrayUtils.countingSort(index, mapper2, W, aux);
//                ArrayUtils.printlnConcisely(index);
//                for (int i = 0; i < index.length; ++i) {
//                    if (i > 0 && mapper1.map(index[i - 1]) == mapper1.map(index[i]) && mapper2.map(index[i - 1]) == mapper2.map(index[i])) rank[lenPower][index[i]] = rank[lenPower][index[i - 1]];
//                    else rank[lenPower][index[i]] = i;
//                }
//            }
//            ArrayUtils.printlnConcisely(index);
//            //ArrayUtils.printlnConcisely(rank[lenPower]);
        }

        int first = 0;
        for (int i = 1; i < n; ++i) {
            for (int bit = 0; (1 << bit) <= n; ++bit) if ((n >> bit & 1) != 0) {
                if (rank[bit][first] > rank[bit][i]) {
                    first = i;
                    break;
                }
                if (rank[bit][first] < rank[bit][i]) break;
            }
        }
        return first;
    }

    public int solveStupidly(List<Integer> starts, StringBuilder stringBuilder) {
        int n = stringBuilder.length() / 2;
        for (int offset = 1; offset < n; ++offset) {
            if (starts.size() == 1) {
                return starts.get(0);
            }
            if (offset > 0) {
                int min = 'z';
                for (int s : starts) min = Math.min(min, stringBuilder.charAt(s + offset));
                List<Integer> nstarts = new ArrayList<>();
                for (int s : starts) if (stringBuilder.charAt(s + offset) == min) nstarts.add(s);
                starts = nstarts;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int n = 100000;
        String random = StringUtils.random(n, 'a', 'b' + 1);
        System.out.println(n);
        for (int i = 0; i < n; ++i) {
            if (i != 0 && i % 75 == 0) System.out.println();
            System.out.print(random.charAt(i));
        }
    }
}

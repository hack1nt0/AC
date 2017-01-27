package template.collection.sequence;

import template.numbers.DoubleUtils;
import template.numbers.IntegerUtils;

import java.util.*;

/**
 * Created by dy on 17-1-12.
 *
 * generic on primitive type is prefer...
 *
 * This class maybe slow and space overhead for very long sequence, because of every element of seq is a object not primitive type.
 * Damn java generic...
 */
public class SuffixArray<T extends Comparable> {
    private T[] text;
    private Integer[] sortedIndex;   // sortedIndex[i] = j means text.substring(j) is ith largest suffix
    private int[] rank;   // rank[i] = j means text.substring(i) is jth largest suffix
    private int N;         // number of characters in text
    private int[] whichStr;
    private int nStr = 1;
    //private final T DELIMITER = null;
    private int[] height;
    private IntervalTree rmqHeight;

//    public T getDELIMITER() {
//        return DELIMITER;
//    }

    //
    public SuffixArray(T[]... texts) {
        buildSA(texts);
    }

    public void buildSA(T[]... texts) {
        for (int i = 0; i < texts.length; ++i) {
            assert texts[i] != null && texts[i].length > 0;
            N += texts[i].length;
        }
        this.nStr = texts.length;
        N += nStr - 1;
        text = Arrays.copyOf(texts[0], N);
        int textIdx = 0;
        for (int i = 0; i < texts.length; ++i) {
            if (i > 0) text[textIdx++] = null;
            for (int j = 0; j < texts[i].length; ++j) {
                text[textIdx++] = texts[i][j];
            }
        }
        if (this.nStr > 1) {
            whichStr = new int[N];
            int whichStrIdx = 0;
            for (int i = 0; i < texts.length; ++i) {
                if (i > 0) whichStr[whichStrIdx++] = -1;
                for (int j = 0; j < texts[i].length; ++j) {
                    whichStr[whichStrIdx++] = i;
                    //whichStrIdx++;
                }
            }
        }

        this.sortedIndex = new Integer[N];
        for (int i = 0; i < N; ++i)
            sortedIndex[i] = i;
        Arrays.sort(sortedIndex, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                T a = text[o1], b = text[o2];
                if (a == null && b == null) return whichStr[o1] - whichStr[o2];
                if (a == null) return -1;
                if (b == null) return 1;
                return compareT(a, b);
            }
        });
        this.rank = new int[N];
        for (int i = 0; i < N; ++i) {
            T cur = text[sortedIndex[i]];
            if (cur == null) rank[sortedIndex[i]] = 0;
            else if (i > 0) {
                T pre = text[sortedIndex[i - 1]];
                if (pre == null || compareT(cur, pre) != 0) {
                    rank[sortedIndex[i]] = i;
                    continue;
                } else {
                    rank[sortedIndex[i]] = rank[sortedIndex[i - 1]];
                }
            }
        }

        int[] tmp = new int[N];
        PairComparator pairComparator = new PairComparator(rank);
        for (int l = 1; l < N; l *= 2) {
            pairComparator.L = l;
            Arrays.sort(sortedIndex, pairComparator);

            tmp[sortedIndex[0]] = 0;
            for (int i = 1; i < N; ++i) {
                int cmp = pairComparator.compare(sortedIndex[i], sortedIndex[i - 1]);
                assert cmp >= 0;
                if (cmp == 0) tmp[sortedIndex[i]] = tmp[sortedIndex[i - 1]];
                else tmp[sortedIndex[i]] = i;
            }
            for (int i = 0; i < N; ++i) rank[i] = tmp[i];

            //small optimization
            boolean unique = true;
            for (int i = 1; i < N; ++i) if (rank[sortedIndex[i]] == rank[sortedIndex[i - 1]]) {
                unique = false;
                break;
            }
            if (unique) break;
        }
    }


    private class PairComparator implements Comparator<Integer> {
        int L;
        int[] rank;
        public PairComparator(int[] rank) {
            this.rank = rank;
        }

        @Override
        public int compare(Integer o1, Integer o2) {
            if (rank[o1] != rank[o2]) return rank[o1] - rank[o2];
            int a = o1 + L < rank.length ? rank[o1 + L] : -1;
            int b = o2 + L < rank.length ? rank[o2 + L] : -1;
            return a - b;
        }
    }

    private int compareT(T a, T b) {
        if (a instanceof Double || a instanceof Float)
            return DoubleUtils.compare((Double)a, (Double)b);
        return a.compareTo(b);
    }

    // longest common prefix of text[i..N) and text[j..N)
    private int lcp(int i, int j) {
        if (i == j) return N - i;
        IntervalTree rmq = getRmqHeight();
        int L = Math.min(rank[i], rank[j]) + 1;
        int R = Math.max(rank[i], rank[j]);
        return rmq.RMinQ(L, R);
    }

    public int longestCommonSubstrings(int nRepeated, List<Integer[]> subArrays) {
        assert nStr > 1;
        assert nRepeated > 1;
        List<Integer> indics = new ArrayList<>();
        int maxL = 0;
        int nStr = 0;
        int[] vis = new int[this.nStr];

        int from = 0;
        while (text[sortedIndex[from]] == null) from++;
        int to = from;
        while (true) {
            if (to >= N) break;
            while (true) {
                if (to >= N || nStr == nRepeated) break;
                int whichStr = this.whichStr[sortedIndex[to]];
                if (vis[whichStr] == 0) nStr++;
                vis[whichStr]++;
                to++;
            }
            if (nStr == nRepeated) {
                int curMaxL = lcp(sortedIndex[from], sortedIndex[to - 1]);
                if (curMaxL > maxL) {
                    maxL = curMaxL;
                    indics.clear();
                    indics.add(from);
                } else if (curMaxL == maxL) {
                    indics.add(from);
                }
                int whichStr = this.whichStr[sortedIndex[from]];
                vis[whichStr]--;
                if (vis[whichStr] == 0) nStr--;
                from++;
            }
        }

        if (subArrays != null) {
            // TODO: 17-1-17
        }

        return maxL;
    }

    public int longestRepeatedSubstrings(int nRepeated, List<Integer> starts) {
        assert nRepeated >= 2;
        assert this.nStr == 1;

        int maxL = 0;
        int p = 0;
        while (text[sortedIndex[p]] == null) p++;
        p += nRepeated - 1;

        while (true) {
            if (p >= N) break;
            int curMaxL = lcp(sortedIndex[p], sortedIndex[p - nRepeated + 1]);
            if (curMaxL > maxL) {
                maxL = curMaxL;
                if (starts != null) {
                    starts.clear();
                    starts.add(p);
                }
            } else if (curMaxL == maxL) {
                if (starts != null) {
                    starts.add(p);
                }
            }
            p++;
        }

        return maxL;
    }

    public int rank(int rk) {
        return sortedIndex[rk];
    }

    public int index(int i) {
        return rank[i];
    }

    public T getText(int i) {
        return text[i];
    }
    /**
     * Copied from
     * http://www.nocow.cn/index.php/%E5%90%8E%E7%BC%80%E6%95%B0%E7%BB%84
     */
    private int[] getHeight() {
        if (height == null) {
            height = new int[N];

            for (int i=0,j=0; i < N; i++)
            {
                if (rank[i]==0) continue;
                for (; i + j < text.length && sortedIndex[rank[i] - 1] + j < text.length && text[i + j] == text[sortedIndex[rank[i] - 1] + j]; j++);
                height[rank[i]]=j;
                if (j > 0) j--;
            }
        }

        return height;
    }

    private IntervalTree getRmqHeight() {
        if (rmqHeight == null) {
            rmqHeight = new IntervalTree(getHeight());
        }
        return rmqHeight;
    }

    public Integer[] suffixArrayBruteforce() {
        Integer[] index = new Integer[N];
        for (int i = 0; i < N; ++i) index[i] = i;
        Arrays.sort(index, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                int offset = 0;
                while(true) {
                    if (offset < Math.min(N - o1, N - o2)) {
                        if (text[o1 + offset] != text[o2 + offset])
                            return compareT(text[o1 + offset], text[o2 + offset]);
                        offset++;
                        continue;
                    }
                    if (offset >= N - o1 && offset >= N - o2) {
                        throw new RuntimeException();
                    }
                    if (offset >= N - o1) {
                        return -1;
                    }
                    if (offset >= N - o2) {
                        return 1;
                    }
                }
            }
        });

        return index;
    }

    public static void main(String[] args) {
        Integer[] arr1 = new Integer[2];
        Integer[][] arr2 = new Integer[2][2];
        SuffixArray<Integer> suffixArray = new SuffixArray<Integer>(new Integer[]{1,2,1}, new Integer[]{2});
        for (int i = 0; i < suffixArray.N; ++i) {
            int h = i == 0 ? 0 : suffixArray.getHeight()[i];
            System.out.print(h + "\t[");
            for (int k = suffixArray.sortedIndex[i]; k < suffixArray.N; ++k)
                System.out.print(suffixArray.text[k] + ",");
            System.out.println("]");
        }
        System.out.println(suffixArray.longestCommonSubstrings(2, null));
        //System.out.printlnTable(suffixArray.longestRepeatedSubstrings(2, null));
    }

    static void test4() {
        Integer[] t1 = (Integer[]) ArrayUtils.inbox(IntegerUtils.randomInts(5, 0, 10));
        Integer[] sa1 = new SuffixArray(t1).sortedIndex;
        Integer[] sa2 = new SuffixArray(t1).suffixArrayBruteforce();
        if (!Arrays.deepEquals(sa1, sa2)) {
            ArrayUtils.printlnTableH(t1, sa1, sa2);
            Integer[] tmp = new SuffixArray(t1).sortedIndex;
        }
    }
}

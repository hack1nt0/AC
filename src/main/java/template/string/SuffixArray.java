package template.string;

import template.collection.sequence.ArrayUtils;
import template.collection.sequence.IntervalTree;

import java.util.*;

/**
 * Created by dy on 17-1-12.
 */
public class SuffixArray {
    private int[] text;
    private Integer[] sortedIndex;   // sortedIndex[i] = j means text.substring(j) is i-th largest suffix
    private int[] rank;   // rank[i] = j means text.substring(i) is j-th largest suffix
    private int N;         // number of characters in text
    private int[] whichStr;
    //private int[] suffixLen; //suffixLen[i] means text[i]'s suffix length of the string it belongs to
    private int nStr;
    //private static final char DELIMITER = Character.MIN_VALUE;
    private int[] height;
    private IntervalTree rmqHeight;

    /**
     * Initializes from suffix array for the given <tt>text</tt> string.
     * @param texts the input string(s)
     */
//    public SuffixArray(String text) {
//        buildSA(text);
//    }

    public SuffixArray(CharSequence... texts) {
        buildSA(texts);
    }

    public void buildSA(CharSequence... texts) {
        for (int i = 0; i < texts.length; ++i) {
            assert texts[i] != null && texts[i].length() > 0;
            N += texts[i].length();
        }
        this.nStr = texts.length;
        N += nStr - 1;
        text = new int[N];
        int textIdx = 0;
        for (int i = 0; i < texts.length; ++i) {
            if (i > 0) text[textIdx++] = -i;
            for (int j = 0; j < texts[i].length(); ++j) {
                text[textIdx++] = texts[i].charAt(j);
            }
        }
        if (this.nStr > 1) {
            whichStr = new int[N];
            int whichStrIdx = 0;
            for (int i = 0; i < texts.length; ++i) {
                if (i > 0) whichStr[whichStrIdx++] = -1;
                for (int j = 0; j < texts[i].length(); ++j) {
                    whichStr[whichStrIdx++] = i;
                }
            }
        }
        this.sortedIndex = new Integer[N];
        for (int i = 0; i < N; ++i)
            sortedIndex[i] = i;
        this.rank = new int[N];
        for (int i = 0; i < N; ++i)
            rank[i] = this.text[i];

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

//            System.err.printlnTable(l * 2);
//            for (int i = 0; i < N; ++i) {
//                System.err.printlnTable(rank[sortedIndex[i]] + " " + text.substring(sortedIndex[i]));
//            }
//            System.err.printlnTable("========================");

            //small optimization
            boolean unique = true;
            for (int i = 1; i < N; ++i) if (rank[sortedIndex[i]] == rank[sortedIndex[i - 1]]) {
                unique = false;
                break;
            }
            if (unique) break;
            //isString

        }
    }

    public SuffixArray(List<String> texts) {
        buildSA(texts.toArray(new String[0]));
    }

    private class PairComparator implements Comparator<Integer> {
        int L;
        int[] index;
        public PairComparator(int[] index) {
            this.index = index;
        }

        @Override
        public int compare(Integer o1, Integer o2) {
            if (index[o1] != index[o2]) return index[o1] - index[o2];
            int a = o1 + L < index.length ? index[o1 + L] : -1;
            int b = o2 + L < index.length ? index[o2 + L] : -1;
            return a - b;
        }
    }

    /**
     * Returns the length of the input string.
     * @return the length of the input string
     */
    public int length() {
        return N;
    }


    /**
     * Returns the sortedIndex into the original string of the <em>i</em>th smallest suffix.
     * That is, <tt>text.substring(sa.sortedIndex(i))</tt> is the <em>i</em> smallest suffix.
     * @param i an integer between 0 and <em>N</em>-1
     * @return the sortedIndex into the original string of the <em>i</em>th smallest suffix
     * @throws java.lang.IndexOutOfBoundsException unless 0 &le; <em>i</em> &lt; <Em>N</em>
     */
    public int index(int i) {
        if (i < 0 || i >= N) throw new IndexOutOfBoundsException();
        return rank[i];
    }

    /**
     * Returns the length of the longest common prefix of the <em>i</em>th
     * smallest suffix and the <em>i</em>-1st smallest suffix.
     * @param rk an integer between 1 and <em>N</em>-1
     * @return the length of the longest common prefix of the <em>i</em>th
     * smallest suffix and the <em>i</em>-1st smallest suffix.
     * @throws java.lang.IndexOutOfBoundsException unless 1 &le; <em>i</em> &lt; <em>N</em>
     */
    public int lcp(int rk) {
        assert 1 <= rk && rk < N;
        return getHeight()[rk];
    }


    // longest common prefix of text[i..N) and text[j..N)
    private int lcp(int i, int j) {
        if (i == j) return N - i;
        IntervalTree rmq = getRmqHeight();
        int L = Math.min(rank[i], rank[j]) + 1;
        int R = Math.max(rank[i], rank[j]);
        return rmq.RMinQ(L, R);
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
                for (; sortedIndex[rank[i] - 1] + j < text.length && text[i + j] == text[sortedIndex[rank[i] - 1] + j]; j++) ;
                //for (; text[i + j] == text[sortedIndex[rank[i] - 1] + j]; j++);
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

    /**
     * Returns the number of suffixes strictly less than the <tt>query</tt> string.
     * We note that <tt>sortedIndex(select(i))</tt> equals <tt>i</tt> for each <tt>i</tt>
     * between 0 and <em>N</em>-1.
     * @param query the query string
     * @return the number of suffixes strictly less than <tt>query</tt>
     */
    public int rank(String query) {
        int lo = 0, hi = N - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = compare(query, sortedIndex[mid]);
            if      (cmp < 0) hi = mid - 1;
            else if (cmp > 0) lo = mid + 1;
            else return mid;
        }
        return lo;
    }

    public int rank(int rk) {
        return sortedIndex[rk];
    }

    // is query < text[i..N) ?
    private int compare(String query, int i) {
        int M = query.length();
        int j = 0;
        while (i < N && j < M) {
            if (query.charAt(j) != text[i]) return query.charAt(j) - text[i];
            i++;
            j++;

        }
        if (i < N) return -1;
        if (j < M) return +1;
        return 0;
    }

    /**
     * Longest Common Substring
     * @param nRepeated
     * @return
     */
    public int longestCommonSubstrings(int nRepeated, Set<String> substrings) {
        assert nStr > 1;
        assert nRepeated > 1;
        List<Integer> indics = new ArrayList<>();
        int maxL = 0;
        int nStr = 0;
        int[] vis = new int[this.nStr];

        int from = 0;
        while (text[sortedIndex[from]] < 0) from++;
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

        if (substrings != null) {
            for (int idx : indics) {
                StringBuilder sb = new StringBuilder();
                for (int j = sortedIndex[idx]; j < sortedIndex[idx] + maxL; ++j) sb.append((char) text[j]);
                substrings.add(sb.toString());
            }
        }
        return maxL;
    }

    public Set<String> longestRepeatedSubstrings(int nRepeated) {
        assert nRepeated >= 2;
        assert this.nStr == 1;

        Set<String> res = new HashSet<>();
        List<Integer> Ps = new ArrayList<>();
        int maxL = 0;
        int p = 0;
        //while (text[sortedIndex[next]] == DELIMITER) next++;
        p += nRepeated - 1;

        while (true) {
            if (p >= N) break;
            int curMaxL = lcp(rank(p), rank(p - nRepeated + 1));
            if (curMaxL > maxL) {
                maxL = curMaxL;
                Ps.clear();
                Ps.add(p);
            } else if (curMaxL == maxL) {
                Ps.add(p);
            }
            p++;
        }

        for (int i : Ps)
            res.add(new String(text, sortedIndex[i], maxL));
        return res;

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
                            return text[o1 + offset] - text[o2 + offset];
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
        ArrayUtils.printlnTableV((int)Character.MIN_VALUE, (int)Character.MAX_VALUE);
        String text1 = "ABRACADABRA!";
        SuffixArray suffixArray1 = new SuffixArray(text1);
        for (int r = 0; r < text1.length(); ++r) {
            System.out.println("\t" + (r == 0 ? 0 : suffixArray1.getHeight()[r]) + "\t" + text1.substring(suffixArray1.rank(r)));
        }
        System.out.println("========================");
        String text2 = "ABRACADABRA!";
        String text3 = text1 + '-' + text2;
        //String text = "A!A";
        SuffixArray suffixArray2 = new SuffixArray(text1, text2);
        for (int r = 0; r < text3.length(); ++r) {
            System.out.println("\t" + (r == 0 ? 0 : suffixArray2.getHeight()[r]) + "\t" + text3.substring(suffixArray2.rank(r)));
        }
        System.out.println("========================");
/*
        for (int i = 0; i < text.length(); ++i)
            for (int j = i; j < text.length(); ++j) {
                Out.printlnTableV(text.substring(i), text.substring(j), suffixArray.lcp(i, j));
            }
        System.out.printlnTable("========================");*/
        test3();

        while (true) test4();

    }

    static void test3() {
        String text1 = "bbbbbbb";
        String text2 = "bbbbbbbbbbb";
        String text3 = text1 + '-' + text2;
        //String text = "A!A";
        SuffixArray suffixArray = new SuffixArray(text1, text2);
        for (int r = 0; r < text3.length(); ++r) {
            System.out.println("\t" + (r == 0 ? 0 : suffixArray.getHeight()[r]) + "\t" + text3.substring(suffixArray.rank(r)));
        }
        Set<String> lcsss = new HashSet<>();
        suffixArray.longestCommonSubstrings(2, lcsss);
        System.out.println(lcsss);
        System.out.println("========================");
    }

    static void test4() {
        String t1 = StringUtils.random(5, 'a', 'd' + 1);
        Integer[] sa1 = new SuffixArray(t1).sortedIndex;
        Integer[] sa2 = new SuffixArray(t1).suffixArrayBruteforce();
        if (!Arrays.deepEquals(sa1, sa2)) {
            ArrayUtils.printlnTableH(sa1, sa2);
        }
    }
}

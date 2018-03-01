package template.string;

import template.collection.CollectionUtils;
import template.collection.tuple.Tuple2;
import java.util.*;

/**
 * Created by dy on 17-1-11.
 *
 */
public class StringUtils {

    public static String reverse(String s) {
        StringBuilder res = new StringBuilder(s);
        return res.reverse().toString();
    }

    public static String random(int W, int from, int to) {
        assert from < to;
        StringBuilder res = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < W; ++i)
            res.append((char)(from + random.nextInt(to - from)));
        return res.toString();
    }

    public static String repeat(String str, int n) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < n; ++i) res.append(str);
        return res.toString();
    }

    public static List<Tuple2<Integer, Integer>> search(String text, String pattern) {
        List<Tuple2<Integer, Integer>> res = new ArrayList<>();
        for (int i = 0; i + pattern.length() <= text.length();) {
            int p = text.indexOf(pattern, i);
            if (p == -1) {
                i++;
            } else {
                res.add(new Tuple2<>(p, p + pattern.length()));
                i = p + 1;
            }
        }
        return res;
    }


    public static class PrefixAutoMationNode {
        public PrefixAutoMationNode prefix;
        public PrefixAutoMationNode[] childs;
        public int id;
        public int depth;
        public boolean terminal;
        String repr = ""; // DEBUG
        PrefixAutoMationNode(int childs) {
            this.childs = new PrefixAutoMationNode[childs];
        }

        @Override
        public String toString() {
            return repr;
        }
    }

    public static PrefixAutoMationNode[] buildPrefixAutomation(List<String> strings, int radix) {
        int size = 1;
        PrefixAutoMationNode trie = new PrefixAutoMationNode(radix);
        trie.prefix = trie;
        for (String pattern : strings) {
            PrefixAutoMationNode curNode = trie;
            for (int i = 0; i < pattern.length(); ++i) {
                int c = pattern.charAt(i);
                if (curNode.childs[c] == null) {
                    ++size;
                    curNode.childs[c] = new PrefixAutoMationNode(radix);
                    curNode.childs[c].depth = curNode.depth + 1;
                    curNode.childs[c].repr = curNode.repr + c; //DEBUG
                }
                curNode = curNode.childs[c];
            }
            curNode.terminal = true;
        }
        PrefixAutoMationNode[] que = new PrefixAutoMationNode[size];
        int id = 0;
        que[0] = trie;
        trie.id = id++;
        int head = 0, tail = 1;
        while (head < tail) {
            PrefixAutoMationNode curNode = que[head++];
            for (int i = 0; i < radix; ++i) {
                PrefixAutoMationNode chd = curNode.childs[i];
                if (chd == null) continue;
                que[tail++] = chd;
                chd.id = id++;
                if (chd.depth == 1) {
                    chd.prefix = curNode;
                } else {
                    PrefixAutoMationNode prefix = curNode.prefix;
                    chd.prefix = trie;
                    while (prefix != trie) {
                        if (prefix.childs[i] != null) {
                            chd.prefix = prefix.childs[i];
                            break;
                        }
                        prefix = prefix.prefix;
                    }
                    if (prefix == trie && trie.childs[i] != null) {
                        chd.prefix = trie.childs[i];
                    }
                }
            }
        }
        return que;
    }

    public static int[] buildPrefixAutomation(String s) {
        int[] prefix = new int[s.length() + 1];
        for (int i = 1; i <= s.length(); ++i) {
            int p = prefix[i - 1];
            while (p != 0) {
                if (s.charAt(p) == s.charAt(i)) {
                    prefix[i] = p + 1;
                    break;
                }
                p = prefix[p];
            }
            if (p == 0 && s.charAt(p) == s.charAt(i)) {
                prefix[i] = 1;
            }
        }
        return prefix;
    }

    public int[] buildSuffixArray(String s) {
        int[] indexes = new int[s.length()];
        return null;
    }

    /**
     * Invariable
     *         (1) s[i] < s[i+1] <...< s[j-1] (?) s[j]
     *         (2) i is the answer
     *
     * NOTE lots of implementation on the internet are wrong.
     * @param origin
     * @return Cyclic Minimum Representation of String.
     */
    public static int cyclicMin(CharSequence origin) {
        if (origin == null || origin.length() == 0) throw new IllegalArgumentException();
        if (origin.length() == 1) return 0;
        int n = origin.length();
//        StringBuilder stringBuilder = new StringBuilder(origin);
//        stringBuilder.append(origin);
        int i = 0, j = 1;
        while (i < n && j < n) {
            if (origin.charAt(i) < origin.charAt(j)) {j++; continue;}
            if (origin.charAt(i) > origin.charAt(j)) {
                i = j;
                j++;
                continue;
            }
            int offset = 0;
            while (offset < n) {
                int ii = i + offset;
                if (ii >= n) ii -= n;
                int jj = j + offset;
                if (jj >= n) jj -= n;
                char ichar = origin.charAt(ii);
                char jchar = origin.charAt(jj);
                if (ichar < jchar) {
                    j += offset + 1;
                    break;
                }
                if (ichar > jchar) {
                    i = j;
                    j += Math.min(j - i, offset);
                    if (j == i) j++;
                    break;
                }
                offset++;
            }
            if (offset == n) {
                break;
            }
        }
        if (i >= n) throw new RuntimeException();
        return i;
    }

    /**
     * 'ABCABCAB' -> 'ABC'
     * @param origin
     * @return Minimum Repeating Substring of origin.
     */
    public static int minRepeatedSubstring(String origin) {
        int[] back = new KMP(origin).getBack();
        return origin.length() - back[origin.length()];
    }

    /**
     * BKDR Hash Function (https://www.byvoid.com/zhs/blog/string-hash-compare)
     */
    @SuppressWarnings("overflow")
    public static int hash(String string, int from, int to) {
        int hash = 0;
        int seed = 131; // 31 131 1313 13131 131313 etc..
        for (int i = from; i < to; ++i) hash = hash * seed + string.charAt(i);
        hash &= 0x7FFFFFFF;
        return hash;
    }

    public static int hash(String string) { return hash(string, 0, string.length());}

    public static <T extends Comparable<T>> int compare(Iterable<T> a, Iterable<T> b) {
        Iterator<T> ita = a.iterator();
        Iterator<T> itb = b.iterator();
        while (ita.hasNext() && itb.hasNext()) {
            int cmp = ita.next().compareTo(itb.next());
            if (cmp != 0) return cmp;
        }
        if (!ita.hasNext() && !itb.hasNext()) return 0;
        return ita.hasNext() ? +1 : -1;
    }

    public List<Integer> toCodePoints(CharSequence string) {
        List<Integer> codes = new ArrayList<>();
        for (int i = 0; i < string.length(); ++i) {
            char c = string.charAt(i);
            if (Character.isHighSurrogate(c)) {
                codes.add(Character.toCodePoint(c, string.charAt(i + 1)));
                i++;
            } else {
                codes.add((int) c);
            }
        }
        return codes;
    }

    public String fromCodePoints(List<Integer> codePoints) {
        return new String(CollectionUtils.toIntArray(codePoints), 0, codePoints.size());
    }


    /** Unit Tests **/
    public static void main(String[] args) {
        testCyclicMin();
    }

    private static void testCyclicMin() {
        while (true) {
            String s = random(10, 'a', 'b' + 1);
            StringBuilder sb = new StringBuilder(s);
            sb.append(s);
            int from = cyclicMin(s);
            cyclicMin("bbabb");
            String min = sb.substring(from, from + s.length());
            for (int i = 0; i < s.length(); ++i) {
                String t = sb.substring(i, i + s.length());
                if (t.compareTo(min) < 0) {
                    throw new RuntimeException();
                }
            }
        }
    }

    private static void testMinRepeated() {
        while (true) {
            String s = random(10, 'a', 'b' + 1);
            int to = minRepeatedSubstring(s);
            System.out.println(s + '\t' + s.substring(0, to));
            for (int i = to; i < s.length(); ++i) {
                if (s.charAt(i % to) != s.charAt(i)) throw new RuntimeException();
            }
        }
    }

}

package template.string;

import java.util.*;

/**
 * Modified by dy on 17-1-13.
 *
 * /**
 *  The <tt>TrieSET</tt> class represents an ordered set of strings over
 *  the extended ASCII alphabet.
 *  It supports the usual <em>add</em>, <em>contains</em>, and <em>delete</em>
 *  methods. It also provides character-based methods for finding the string
 *  in the set that is the <em>longest prefix</em> of a given prefix,
 *  finding all strings in the set that <em>start with</em> a given prefix,
 *  and finding all strings in the set that <em>match</em> a given pattern.
 *  <next>
 *  This implementation uses a 256-way trie.
 *  The <em>add</em>, <em>contains</em>, <em>delete</em>, and
 *  <em>longest prefix</em> methods take time proportional to the length
 *  of the key (in the worst case). Construction takes constant time.
 *  <next>
 *  For additional documentation, see
 *  <a href="http://algs4.cs.princeton.edu/52trie">Section 5.2</a> of
 *  <i>Algorithms in Java, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class TrieSet extends AbstractSet<String> implements Iterable<String> {
    private int R = 127;
    private int N;
    private class Node {
        Node[] next;
        boolean isString;
        public Node() {
            next = new Node[R];
        }
    }
    private Node root = new Node();

    public TrieSet(String[] strings) {
        for (String s : strings) add(s);
    }

    public TrieSet() {}

    /**
     * Returns all of the keys in the set, as an iterator.
     * To iterate over all of the keys in a set named <tt>set</tt>, use the
     * foreach notation: <tt>for (Key key : set)</tt>.
     * @return an iterator to all of the keys in the set
     */
    public Iterator<String> iterator() {
        return keysWithPrefix("").iterator();
    }

    /**
     * Returns all of the keys in the set that start with <tt>prefix</tt>.
     * @param prefix the prefix
     * @return all of the keys in the set that start with <tt>prefix</tt>,
     *     as an iterable
     */
    public Iterable<String> keysWithPrefix(String prefix) {
        Queue<String> results = new LinkedList<>();
        Node x = get(root, prefix, 0);
        collect(x, new StringBuilder(prefix), results);
        return results;
    }


    private Node get(Node x, String key, int d) {
        if (x == null) return null;
        if (d == key.length()) return x;
        char c = key.charAt(d);
        return get(x.next[c], key, d+1);
    }

    private void collect(Node x, StringBuilder prefix, Queue<String> results) {
        if (x == null) return;
        if (x.isString) results.add(prefix.toString());
        for (char c = 0; c < R; c++) {
            prefix.append(c);
            collect(x.next[c], prefix, results);
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }

    /**
     * Returns all of the keys in the set that match <tt>pattern</tt>,
     * where . symbol is treated as a wildcard character.
     * @param pattern the pattern
     * @return all of the keys in the set that match <tt>pattern</tt>,
     *     as an iterable, where . is treated as a wildcard character.
     */
    public Iterable<String> keysThatMatch(String pattern) {
        Queue<String> results = new LinkedList<>();
        StringBuilder prefix = new StringBuilder();
        collect(root, prefix, pattern, results);
        return results;
    }

    private void collect(Node x, StringBuilder prefix, String pattern, Queue<String> results) {
        if (x == null) return;
        int d = prefix.length();
        if (d == pattern.length() && x.isString)
            results.add(prefix.toString());
        if (d == pattern.length())
            return;
        char c = pattern.charAt(d);
        if (c == '.') {
            for (char ch = 0; ch < R; ch++) {
                prefix.append(ch);
                collect(x.next[ch], prefix, pattern, results);
                prefix.deleteCharAt(prefix.length() - 1);
            }
        }
        else {
            prefix.append(c);
            collect(x.next[c], prefix, pattern, results);
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }

    /**
     * Returns the string in the set that is the longest prefix of <tt>query</tt>,
     * or <tt>null</tt>, if no such string.
     * @param query the query string
     * @return the string in the set that is the longest prefix of <tt>query</tt>,
     *     or <tt>null</tt> if no such string
     * @throws NullPointerException if <tt>query</tt> is <tt>null</tt>
     */
    public String longestPrefixOf(String query) {
        int length = longestPrefixOf(root, query, 0, -1);
        if (length == -1) return null;
        return query.substring(0, length);
    }

    // returns the length of the longest string key in the subtrie
    // rooted at x that is a prefix of the query string,
    // assuming the first d character match and we have already
    // found a prefix match of length length
    private int longestPrefixOf(Node x, String query, int d, int length) {
        if (x == null) return length;
        if (x.isString) length = d;
        if (d == query.length()) return length;
        char c = query.charAt(d);
        return longestPrefixOf(x.next[c], query, d+1, length);
    }

    @Override
    public int size() {
        return N;
    }

    public boolean add(String string) {
        boolean res = false;
        Node treep = root;
        for (int i = 0; i < string.length(); ++i) {
            char c = string.charAt(i);
            if (treep.next[c] == null) {
                treep.next[c] = new Node();
                res = true;
                treep = treep.next[c];
            } else {
                treep = treep.next[c];
                //treep.cnt++;
            }
        }
        treep.isString = true;
        N++;
        return res;
    }

    public boolean contains(String string) {
        Node treep = root;
        for (int i = 0; i < string.length(); ++i) {
            char c = string.charAt(i);
            if (treep.next[c] == null) {
                return false;
            }
            treep = treep.next[c];
        }
        return treep.isString;
    }

/*    public boolean remove(String string) {
        if (!contains(string)) return false;
        Node treep = root;
        for (int i = 0; i < string.length(); ++i) {
            char c = string.charAt(i);
            treep.next[c].cnt--;
            if (treep.next[c].cnt == 0) {
                treep.next[c] = null;
                return true;
            }
        }
        N--;
        return true;
    }*/

    /**
     * Removes the key from the set if the key is present.
     * @param key the key
     * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>
     */
    public void remove(String key) {
        root = remove(root, key, 0);
    }

    private Node remove(Node x, String key, int d) {
        if (x == null) return null;
        if (d == key.length()) {
            if (x.isString) N--;
            x.isString = false;
        }
        else {
            char c = key.charAt(d);
            x.next[c] = remove(x.next[c], key, d+1);
        }

        // remove subtrie rooted at x if it is completely empty
        if (x.isString) return x;
        for (int c = 0; c < R; c++)
            if (x.next[c] != null)
                return x;
        return null;
    }
    public void setR(int R) {
        this.R = R;
    }
    public int getR() {
        return this.R;
    }

    private Node nodePointer = root;
    public int accept(char c) {
        //if (nodePointer == null) nodePointer = root;
        nodePointer = nodePointer.next[c];
        if (nodePointer == null) return -1;
        if (nodePointer.isString) return 1;
        return 0;
    }

    public void backToRoot() {
        nodePointer = root;
    }
}

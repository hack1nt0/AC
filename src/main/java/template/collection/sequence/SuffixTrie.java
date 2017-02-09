package template.collection.sequence;

import template.collection.tuple.Tuple2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dy on 2017/2/4.
 */
public class SuffixTrie {
    private int R;
    private class Node {
        Node[] next;
        int cnt;
        public Node(int radix) {
            next = new Node[radix];
        }
    }
    private Node root;

    public SuffixTrie(int radix) {
        this.R = radix;
        this.root = new Node(radix);
    }

    public boolean add(CharSequence string) {
        boolean res = false;
        Node treep = root;
        for (int i = 0; i < string.length(); ++i) {
            char c = string.charAt(i);
            if (treep.next[c] == null) {
                treep.next[c] = new Node(R);
                res = true;
                treep = treep.next[c];
            } else {
                treep = treep.next[c];
                //treep.cnt++;
            }
            treep.cnt++;
        }
        return res;
    }

    public boolean add(int[] arr) {
        return add(arr, 0, arr.length);
    }

    public boolean add(int[] arr, int from, int to) {
        boolean res = false;
        Node treep = root;
        for (int i = from; i < to; ++i) {
            int c = arr[i];
            if (treep.next[c] == null) {
                treep.next[c] = new Node(R);
                res = true;
                treep = treep.next[c];
            } else {
                treep = treep.next[c];
                //treep.cnt++;
            }
            treep.cnt++;
        }
        return res;
    }

    public List<Tuple2<String, Integer>> allSubstrings(int fromLen, int toLen) {
        List<Tuple2<String, Integer>> res = new ArrayList<>();
        allSubstrings(0, root, fromLen, toLen, res, new StringBuilder());
        return res;
    }

    public void allSubstrings(int depth, Node curNode, int fromLen, int toLen, List<Tuple2<String, Integer>> res, StringBuilder acc) {
        if (toLen <= depth) return;
        if (fromLen <= depth && depth < toLen) {
            //System.err.printlnConcisely(acc.toString() + " " + curNode.cnt);
            res.add(new Tuple2<String, Integer>(acc.toString(), curNode.cnt));
        }
        for (int r = 0; r < R; ++r) {
            if (curNode.next[r] == null) continue;
            acc.append(r);
            allSubstrings(depth + 1, curNode.next[r], fromLen, toLen, res, acc);
            acc.setLength(acc.length() - 1);
        }
    }

}

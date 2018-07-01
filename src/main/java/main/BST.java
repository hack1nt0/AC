package main;

import template.collection.sequence.ArrayUtils;
import template.debug.InputReader;
import template.debug.OutputWriter;

import java.util.Arrays;

public class BST {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        BST1 bst = new BST1(n);
        int[] array = new int[n];
        for (int i = 0; i < n; ++i) {
            int d = in.readInt();
            if (i > 0) {
                Arrays.sort(array, 0, i);
                int ilb = ArrayUtils.lowerBound(array, 0, i, d);
                int nls = bst.nLessThan(d);
                if (ilb != nls)
                    throw new RuntimeException();
            }
            bst.insert(d);
            array[i] = d;
        }
    }

    static class BST1 extends template.collection.sets.AbstractBST<Integer> {
        int[] size, num;

        public BST1(int size) {
            super(size);
            this.size = new int[size + 2];
            this.num = new int[size + 2];
        }

        @Override
        public void found(int cur, Integer nobj) {
            num[cur]++;
            size[cur]++;
        }

        @Override
        public void create(int cur, Integer nobj) {
            size[cur] = num[cur] = 1;
        }

        @Override
        public void before(int cur, Integer nobj) {

        }

        @Override
        public void after(int cur, Integer nobj) {
            size[cur] = size[left[cur]] + size[right[cur]] + num[cur];
        }

        public int nLessThan(int d) {
            return nLessThan(ROOT, d);
        }

        private int nLessThan(int root, Integer d) {
            if (root == 0)
                return 0;
            int cmp = d.compareTo(getValue(root));
            if (cmp == 0)
                return size[left[root]];
            if (cmp < 0)
                return nLessThan(left[root], d);
            else
                return size[left[root]] + num[root] + nLessThan(right[root], d);
        }
    }
}

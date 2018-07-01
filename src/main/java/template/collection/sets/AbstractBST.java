package template.collection.sets;

import java.util.Arrays;

/**
 * Created by dy on 2018/6/30.
 */
public abstract class AbstractBST<T extends Comparable<T>> {
    public int[] left, right, height;
    public Object[] values;
    int size = 1;
    public int ROOT = 1;
    int acc, maxAcc = 5;

    public AbstractBST(int size) {
        left = new int[size + 2];
        right = new int[size + 2];
        height = new int[size + 2];
        values = new Object[size + 2];
    }

    public T getValue(int index) {
        return (T) values[index];
    }

    public void clear() {
        size = 1;
        Arrays.fill(left, 0);
        Arrays.fill(right, 0);
        Arrays.fill(height, 0);
        Arrays.fill(values, null);
        ROOT = 1;
    }

    public boolean insert(T nobj) {
        boolean inserted = insert(ROOT, nobj);
        if (inserted && ++acc > maxAcc) {
            ROOT = rotate(ROOT, nobj);
            acc = 0;
        }
        return inserted;
    }

    private boolean insert(int root, T nobj) {
        boolean inserted = false;
        before(root, nobj);
        if (size == 1) {
            values[1] = nobj;
            create(1, nobj);
            size++;
        } else {
            int cmp = nobj.compareTo(getValue(root));
            if (cmp == 0) {
                found(root, nobj);
            } else if (cmp < 0) {
                if (left[root] == 0) {
                    values[size] = nobj;
                    create(size, nobj);
                    left[root] = size;
                    size++;
                    inserted = true;
                } else {
                    inserted = insert(left[root], nobj);
                }
            } else {
                if (right[root] == 0) {
                    values[size] = nobj;
                    create(size, nobj);
                    right[root] = size;
                    size++;
                    inserted = true;
                } else {
                    inserted = insert(right[root], nobj);
                }
            }
        }
        after(root, nobj);
        return inserted;
    }

    public abstract void found(int cur, T nobj);
    public abstract void create(int cur, T nobj);
    public abstract void before(int cur, T nobj);
    public abstract void after(int cur, T nobj);

    private int rotate(int root, T nobj) {
        int cmp = nobj.compareTo(getValue(root));
        if (cmp == 0)
            return root;
        if (cmp < 0)
            left[root] = rotate(left[root], nobj);
        else
            right[root] = rotate(right[root], nobj);
        int lh = height[left[root]];
        int rh = height[right[root]];
        if (Math.abs(lh - rh) <= 1) {
            height[root] = Math.max(lh, rh) + 1;
            return root;
        }
        int pivot, split, old;
        pivot = split = old = -1;
        if (lh < rh) {
            pivot = right[root];
            old = left[root];
            split = left[pivot];
            right[root] = split;
            left[pivot] = root;
        } else {
            pivot = left[root];
            old = right[root];
            split = right[pivot];
            left[root] = split;
            right[pivot] = root;
        }
        after(root, nobj);
        after(pivot, nobj);
        height[root] = Math.max(height[old], height[split]) + 1;
        height[pivot] = Math.max(height[root] + 1, height[pivot]);
        return pivot;
    }
}



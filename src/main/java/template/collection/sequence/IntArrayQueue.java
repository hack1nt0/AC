package template.collection.sequence;

import java.util.*;

/**
 * Created by dy on 2017/2/8.
 *
 *  Queue for Integer or Indexes.
 */
public class IntArrayQueue implements Iterable<Integer>{
    private int[] arr;
    private int from, to;
    private int N;

    public IntArrayQueue(int initCapacity) {
        arr = new int[initCapacity];
    }

    public IntArrayQueue() {
        arr = new int[1];
    }

    public int getFirst() {
        if (isEmpty()) throw new NoSuchElementException("The queue underflow.");
        return arr[from];
    }

    public int getLast() {
        if (isEmpty()) throw new NoSuchElementException("The queue underflow.");
        return arr[to - 1];
    }

    public int removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("The queue underflow.");
        int first = arr[from++];
        if (from == arr.length) from = 0;
        N--;
        if (N > 0 && N == arr.length / 4) resize(arr.length / 2);
        return first;
    }

    public Object removeLast() {
        if (isEmpty()) throw new NoSuchElementException("The queue underflow.");
        int last = arr[--to];
        if (to < 0) to = arr.length - 1;
        N--;
        if (N > 0 && N == arr.length / 4) resize(arr.length / 2);
        return last;
    }

    public boolean isEmpty () {
        return N == 0;
    }

    public void addFirst(int o) {
        if (size() == arr.length) resize(arr.length * 2);
        from--;
        if (from < 0) from = arr.length - 1;
        arr[from] = o;
        N++;
    }

    public void addLast(int o) {
        if (size() == arr.length) resize(arr.length * 2);
        arr[to] = o;
        to++;
        if (to == arr.length) to = 0;
        N++;
    }

    public boolean contains(int o) {
        for (int i : arr) if (i == o) return true;
        return false;
    }

    public int size() {
        return N;
    }

    private void resize(int capacity) {
        //System.err.println(capacity + " " + N + " " + arr.length);
        int[] temp = new int[capacity];
        for (int i = 0; i < N; ++i) temp[i] = arr[(from + i) % arr.length];
        arr = temp;
        from = 0;
        to = N;
    }

    public void clear() {
        from = to = 0;
        N = 0;
    }

    public int peek() {
        return getFirst();
    }

    public int poll() {
        return removeFirst();
    }

    public Object peekLast() {
        return getLast();
    }

    public Object pollLast() {
        return removeLast();
    }

    public void add(int o) {
        addLast(o);
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            int first = from, last = to;
            int size = N;
            @Override
            public boolean hasNext() {
                return size-- > 0;
            }

            @Override
            public Integer next() {
                int n = arr[first];
                first++;
                if (first == arr.length) first = 0;
                return n;
            }
        };
    }
}
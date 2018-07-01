package template.collection;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 * @author dy[jealousing@gmail.com] on 17-3-20.
 */
public class CollectionUtils {

    public static int[] toIntArray(Collection<Integer> collection) {
        int[] array = new int[collection.size()];
        int index = 0;
        for (int element : collection) {
            array[index++] = element;
        }
        return array;
    }

    public static double[] toDoubleArray(Collection<Double> collection) {
        double[] array = new double[collection.size()];
        int index = 0;
        for (double element : collection) {
            array[index++] = element;
        }
        return array;
    }

    public static List<Integer> range(int from, int to) {
        List<Integer> result = new ArrayList<Integer>(Math.max(from, to) - Math.min(from, to) + 1);
        if (to > from) {
            for (int i = from; i <= to; i++) {
                result.add(i);
            }
        } else {
            for (int i = from; i >= to; i--) {
                result.add(i);
            }
        }
        return result;
    }

    public static void rotate(List<Integer> list) {
        list.add(list.remove(0));
    }

    public static <T> List<T> asList(Iterable<T> iterable) {
        List<T> list = new ArrayList<T>();
        for (T element : iterable) {
            list.add(element);
        }
        return list;
    }

    public static <K, V> TreeMap<V, List<K>> reverse(TreeMap<K, V> kvMap) {
        TreeMap<V, List<K>> res = new TreeMap<>();
        for (K key : kvMap.keySet()) {
            V old = kvMap.get(key);
            List<K> list = res.getOrDefault(old, new ArrayList<K>());
            list.add(key);
            res.put(old, list);
        }
        return res;
    }

    public static <K, V> HashMap<V, List<K>> reverse(HashMap<K, V> kvMap) {
        HashMap<V, List<K>> res = new HashMap<>();
        for (K key : kvMap.keySet()) {
            V old = kvMap.get(key);
            List<K> list = res.getOrDefault(old, new ArrayList<K>());
            list.add(key);
            res.put(old, list);
        }
        return res;
    }

    public static <V> boolean deepEquals(List<V> a, List<V> b) {
        if (a.size() != b.size())
            return false;
        for (int i = 0; i < a.size(); ++i)
            if (!a.get(i).equals(b.get(i)))
                return false;
        return true;
    }

    public static <V> long count(Collection<V> collection, V i) {
        long ans = 0;
        for (V v : collection)
            if (v.equals(i))
                ans++;
        return ans;
    }

    public static <T> Set<T> union(Collection<T>... sets) {
        Set<T> join = new HashSet<T>();
        for (Collection<T> set : sets) {
            for (T e : set)
                join.add(e);
        }
        return join;
    }
}

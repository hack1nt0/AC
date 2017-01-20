package template.collection.sets;

/**
 * Created by dy on 16-11-26.
 */
public class UnionFind {
    private int[] parent;
    private int[] top;
    private int count;

    public UnionFind(int N) {
        parent = new int[N];
        top = new int[N];
        count = N;
        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }
    }
    public int count() {
        return count;
    }
    public int find(int p) {
        if (p != parent[p])
            parent[p] = find(parent[p]);
        return parent[p];
    }
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public void union(int p, int q) {
        if (p == q) return;
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;
        if (top[rootP] == top[rootQ]) {
            parent[rootP] = rootQ;
            top[rootP]++;
        } else if (top[rootP] > top[rootQ]) {
            parent[rootQ] = rootP;
        } else {
            parent[rootP] = rootQ;
        }
        count--;
    }
}

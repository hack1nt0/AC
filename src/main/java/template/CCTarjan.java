package template;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * Created by dy on 16-12-20.
 */
public class CCTarjan {
    public Set<Integer>[] adj;
    int ncc, nscc, N, M;
    private boolean[] marked;        // marked[v] = has v been visited?
    private int[] idscc;                // id[v] = id of strong component containing v
    private int[] low;               // low[v] = low number of v
    private int pre;                 // preorder number counter
    private Stack<Integer> stack;

    public CCTarjan(int N) {
        this.N = N;
        adj = new HashSet[N];
        for (int i = 0; i < N; ++i) adj[i] = new HashSet<Integer>();

        marked = new boolean[N];
        stack = new Stack<Integer>();
        idscc = new int[N];
        low = new int[N];
    }

    public void addE(int a, int b) {
        adj[a].add(b);
        M++;
    }

    public void removeE(int a, int b) {
        adj[a].remove(b);
        M--;
    }

    public int[] cc() {
        int N = adj.length;
        int[] idcc = new int[N];
        Arrays.fill(idcc, -1);
        ncc = 0;
        for (int i = 0; i < N; ++i) if (idcc[i] == -1)
            cchelper(i, idcc, ncc++, adj);
        return idcc;
    }

    public int ncc() {
        cc();
        return ncc;
    }

    private void cchelper(int cur, int[] idcc, int ncc, Set<Integer>[] adj) {
        idcc[cur] = ncc;
        for (int chd : adj[cur]) if (idcc[chd] == -1)
            cchelper(chd, idcc, ncc, adj);
    }

    public int[] scc() {
        pre = 0;
        Arrays.fill(marked, false);

        for (int v = 0; v < N; v++) if (!marked[v])
            dfs(v);
        return idscc;
    }

    private void dfs(int v) {
        marked[v] = true;
        low[v] = pre++;
        int min = low[v];
        stack.push(v);
        for (int w : adj[v]) {
            if (!marked[w]) dfs(w);
            if (low[w] < min) min = low[w];
        }
        if (min < low[v]) {
            low[v] = min;
            return;
        }
        int w;
        do {
            w = stack.pop();
            idscc[w] = nscc;
            low[w] = N;
        } while (w != v);
        nscc++;
    }

    public int nscc() {
        scc();
        return nscc;
    }

    public static void main(String[] args) {
        CCTarjan cc = new CCTarjan(3);
        cc.addE(0, 1);
        cc.addE(0, 0);
        System.out.println("nscc " + cc.nscc());
        System.out.println("ncc " + cc.ncc());
    }
}

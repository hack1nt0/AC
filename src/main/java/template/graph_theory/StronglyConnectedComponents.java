package template.graph_theory;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * Created by dy on 2018/6/5.
 */
public class StronglyConnectedComponents {

    public int[] tarjan(List<Integer>[] adj) {
        int n = adj.length;
        int[] which = new int[n];
        int count = 0;
        int[] preOrder = new int[n];
        Arrays.fill(preOrder, -1);
        int[] lowLink = new int[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; ++i)
            if (preOrder[i] == -1)
                count += tarjan(i, count, preOrder, adj, which, lowLink, stack);
        return which;
    }

    private int tarjan(int cur, int count, int[] preOrder, List<Integer>[] adj, int[] which, int[] lowLink, Stack<Integer> stack) {
        preOrder[cur] = count++;
        lowLink[cur] = preOrder[cur];
        stack.push(cur);
        for (int nxt : adj[cur]) {
            if (preOrder[nxt] == -1)
                count += tarjan(nxt, count, preOrder, adj, which, lowLink, stack);
            lowLink[cur] = Math.min(lowLink[cur], lowLink[nxt]);
        }
        if (lowLink[cur] == preOrder[cur]) {
            while (true) {
                int top = stack.pop();
                which[top] = cur;
                lowLink[top] = Integer.MAX_VALUE;
                if (top == cur)
                    break;
            }
        }
        return count;
    }
}

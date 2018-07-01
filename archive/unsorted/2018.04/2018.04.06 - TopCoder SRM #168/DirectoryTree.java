package main;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class DirectoryTree {
    class Node {
        String name;
        TreeMap<String, Node> child = new TreeMap<>();
        int size = 1;
        Node(String name) {
            this.name = name;
        }
    }
    Node trie = new Node("ROOT");
    public String[] display(String[] files) {
        for (String file : files) {
            String[] tmp = file.split("/");
            Node root = trie;
            for (String t : tmp) if (!t.equals("")){
                if (!root.child.containsKey(t))
                    root.child.put(t, new Node(t));
                root = root.child.get(t);
            }
        }
        dfs1(trie);
        StringBuffer[] ans = new StringBuffer[trie.size];
        for (int i = 0; i < ans.length; ++i) ans[i] = new StringBuffer();
        dfs2(trie, ans, 0, ans.length);

        String[] ans2 = new String[ans.length];
        for (int i = 0; i < ans.length; ++i) ans2[i] = ans[i].toString();
        return ans2;
    }

    private void dfs2(Node root, StringBuffer[] ans, int from, int to) {
        ans[from].append(root.name);
        int from1 = from + 1;
        for (Map.Entry<String, Node> child : root.child.entrySet()) {
            int to1 = from1 + child.getValue().size;
            int i = from1;
            ans[i++].append("+-");
            String prefix = child.getKey().equals(root.child.lastEntry().getKey()) ? "  " : "| ";
            while (i < to1)
                ans[i++].append(prefix);
            dfs2(child.getValue(), ans, from1, to1);
            from1 = to1;
        }
    }

    private int dfs1(Node root) {
        for (Map.Entry<String, Node> child : root.child.entrySet()) {
            root.size += dfs1(child.getValue());
        }
        return root.size;
    }
}

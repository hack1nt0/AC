package main;


public class BinaryQuipu {
    class Node {
        int count;
        Node[] childs = new Node['s' + 1];
    }
    public int fewestKnots(String[] inventory) {
        int ans = 0;
        Node trie = new Node();
        for (String item : inventory) {
            ans += item.length();
            Node root = trie;
            for (char c : item.toCharArray()) {
                if (root.childs[c] == null)
                    root.childs[c] = new Node();
                else
                    ans--;
                root = root.childs[c];
            }
        }
        Node reverseTrie = new Node();
        for (String item : inventory) {
            Node root = reverseTrie;
            for (int i = item.length() - 1; i >= 0; --i) {
                char c = item.charAt(i);
                if (root.childs[c] == null)
                    root.childs[c] = new Node();
                else
                    ans--;
                root = root.childs[c];
            }
        }
        return ans;
    }
}

package template.string;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dy on 2018/6/10.
 */
public class SuffixTree {

    class Edge {
        int from, to;
        int id = -1;
        Edge[] childs = new Edge[128];
        Edge parent;

        public Edge(int from, int to) {
            this.from = from;
            this.to = to;
        }

        public char charAt(int index) {
            return string.charAt(from + index);
        }

        public int length() {
            return to - from;
        }

        @Override
        public String toString() {
            return string.substring(from, to);
        }
    }

    String string;
    Edge root = new Edge(0, 0);
    List<Edge> edges = new ArrayList<>();

    public SuffixTree(String s) {
        string = s;
        for (int i = 0; i < s.length(); ++i) {
            int pc = i;
            Edge ce = root;
            while (pc < s.length()) {
                char c = s.charAt(pc);
                if (ce.childs[c] == null) {
                    Edge ne = new Edge(pc, s.length());
                    addEdge(ce, c, ne);
                    break;
                }
                ce = ce.childs[c];
                int npc = pc;
                while (npc < s.length() && npc - pc < ce.length() && s.charAt(npc) == ce.charAt(npc - pc))
                    npc++;
                if (npc == s.length())
                    break;
                if (npc - pc < ce.length()) {
                    int split = ce.from + npc - pc;
                    Edge se = new Edge(ce.from, split);
                    addEdge(ce.parent, c, se);
                    ce.from = split;
                    addEdge(se, s.charAt(split), ce);
                    ce = se;
                }
                pc = npc;
            }
        }
    }

    private void addEdge(Edge parent, char c, Edge newEdge) {
        parent.childs[c] = newEdge;
        newEdge.parent = parent;
        if (newEdge.id == -1) {
            newEdge.id = edges.size();
            edges.add(newEdge);
        }
    }

    @Override
    public String toString() {
        return edges.toString();
    }
}

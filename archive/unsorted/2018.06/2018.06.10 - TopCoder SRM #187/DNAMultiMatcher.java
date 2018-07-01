package main;

import java.util.ArrayList;
import java.util.List;

//MLE
public class DNAMultiMatcher {
    public int longestMatch(String[] sequence1, String[] sequence2, String[] sequence3) {
        String[][] seqs = {sequence1, sequence2, sequence3};
        int max = 0;
        SuffixTree stree = null;
        int[] edgeLen = null;
        for (int i = 0; i < seqs.length; ++i) {
            StringBuilder sb = new StringBuilder();
            for (String s : seqs[i])
                sb.append(s);
            if (i == 0) {
                stree = new SuffixTree(sb.toString());
                edgeLen = new int[stree.edges.size()];
                for (int j = 0; j < edgeLen.length; ++j)
                    edgeLen[j] = stree.edges.get(j).length();
            } else {
                int[] nedgeLen = new int[stree.edges.size()];
                for (int s = 0; s < sb.length(); ++s) {
                    int len = 0;
                    SuffixTree.Edge ce = stree.root;
                    int pos = s;
                    while (pos < sb.length()) {
                        char c = sb.charAt(pos);
                        if (ce == null || ce.childs[c] == null)
                            break;
                        ce = ce.childs[c];
                        int npos = pos;
                        while (npos < sb.length() && npos - pos < edgeLen[ce.id] && sb.charAt(npos) == ce.charAt(npos - pos)) {
                            npos++;
                            len++;
                        }
                        nedgeLen[ce.id] = Math.max(nedgeLen[ce.id], npos - pos);
                        if (npos == sb.length() || npos - pos < ce.length())
                            break;
                        pos = npos;
                    }
                    if (i == seqs.length - 1) {
                        max = Math.max(max, len);
                    }
                }
                for (int j = 0; j < edgeLen.length; ++j)
                    edgeLen[j] = Math.min(edgeLen[j], nedgeLen[j]);
            }
        }
        return max;
    }

    class SuffixTree {

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

}

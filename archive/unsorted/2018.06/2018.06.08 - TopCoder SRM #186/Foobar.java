package main;

import template.string.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//WA...describe unclear
public class Foobar {
    public String censor(String plain, String code, String text) {
        Map<Character, List<Character>> encode = new HashMap<>();
        for (int i = 0; i < code.length(); ++i) {
            List<Character> value = encode.containsKey(plain.charAt(i)) ? encode.get(plain.charAt(i)) : new ArrayList<>();
            value.add(code.charAt(i));
            encode.put(plain.charAt(i), value);
        }
        List<String> bads = new ArrayList<>();
        for (String forbidden : new String[] {"heck", "gosh", "dang", "shucks", "fooey", "snafu", "fubar"}) {
            List<String> list = new ArrayList<>();
            dfs(0, forbidden, encode, new StringBuilder(), list);
            bads.addAll(list);
        }
        StringUtils.PrefixAutomatonNode[] trie = StringUtils.buildPrefixAutomaton(bads, 128);
        int tnode = 0;
        StringBuilder ntext = new StringBuilder();
        for (int i = 0; i < text.length(); ++i) {
            char c = text.charAt(i);
            ntext.append(c);
            if (c != ' ') {
                if (trie[tnode].childs[c] != null) {
                    tnode = trie[tnode].childs[c].id;
                } else {
                    while (tnode != 0 && trie[tnode].childs[c] == null)
                        tnode = trie[tnode].prefix.id;
                    if (trie[tnode].childs[c] != null)
                        tnode = trie[tnode].childs[c].id;
                }
                if (trie[tnode].terminal) {
                    int ptr = i, nospaces = 0;
                    while (nospaces < trie[tnode].depth) {
                        if (text.charAt(ptr) != ' ')
                            nospaces++;
                        ptr--;
                    }
                    ptr++;
                    if (nospaces != trie[tnode].depth)
                        throw new RuntimeException();
                    ntext.setLength(ptr);
                    for (int j = ptr; j <= i; ++j)
                        ntext.append("*");
                    tnode = trie[tnode].prefix.id;
                }
            }
        }
        return ntext.toString();
    }

    private void dfs(int cur, String origin, Map<Character, List<Character>> encode, StringBuilder acc, List<String> list) {
        if (cur == origin.length()) {
            String n = acc.toString();
            list.add(acc.toString());
//            StringBuilder n2 = new StringBuilder();
//            for (int i = 0; i < n.length(); ++i)
//                n2.append((i > 0 ? " " : "") + n.charAt(i));
//            list.add(n2.toString());
            return;
        }
        char c = origin.charAt(cur);
        acc.append(c);
        dfs(cur + 1, origin, encode, acc, list);
        acc.setLength(acc.length() - 1);
        if (encode.containsKey(c)) {
            for (char t : encode.get(c)) {
                acc.append(t);
                dfs(cur + 1, origin, encode, acc, list);
                acc.setLength(acc.length() - 1);
            }
        }
    }
}

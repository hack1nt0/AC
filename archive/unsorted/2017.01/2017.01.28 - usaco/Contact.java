package main;

import template.collection.sequence.ArrayUtils;
import template.collection.sequence.SuffixTrie;
import template.collection.tuple.Tuple2;

import java.util.*;
import java.io.PrintWriter;
/*
 ID: hackint1
 LANG: JAVA
 TASK: contact
*/
public class Contact {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int fromLen = in.nextInt();
        int toLen = in.nextInt() + 1;
        int n = in.nextInt();
        PriorityQueue<Tuple2<String, Integer>> heap = new PriorityQueue<>(new Comparator<Tuple2<String, Integer>>() {
            @Override
            public int compare(Tuple2<String, Integer> o1, Tuple2<String, Integer> o2) {
                int cnt1 = o1.getSecond();
                int cnt2 = o2.getSecond();
                if (cnt1 != cnt2) return -cnt1 + cnt2;
                String string1 = o1.getFirst();
                String string2 = o2.getFirst();
                if (string1.length() != string2.length()) return string1.length() - string2.length();
                else return string1.compareTo(string2);
            }
        });

        StringBuilder messageBuf = new StringBuilder();
        while (true) {
            if (!in.hasNext()) break;
            messageBuf.append(in.nextLine());
        }
        int[] message = new int[messageBuf.length()];
        for (int i = 0; i < message.length; ++i) message[i] = messageBuf.charAt(i) - '0';
        SuffixTrie suffixTrie = new SuffixTrie(2);
        for (int i = 0; i < message.length; ++i) suffixTrie.add(message, i, Math.min(i + toLen, message.length));

        for (Tuple2<String, Integer> substring : suffixTrie.allSubstrings(fromLen, toLen))
            heap.add(substring);

        int preCnt = 0;
        List<String> combinedPatterns = new ArrayList<>();
        while (true) {
            if (heap.size() <= 0) break;
            Tuple2<String, Integer> substring = heap.poll();
            int cnt = substring.getSecond();
            String pattern = substring.getFirst();
            if ((preCnt == 0 || cnt < preCnt)) {
                if (n <= 0) break;
                n--;
                if (combinedPatterns.size() != 0) {
                    ArrayUtils.printlnConcisely(combinedPatterns, out, 6);
                    combinedPatterns.clear();
                }
                //if (preCnt != 0) out.printlnConcisely();
                out.println(cnt);
                combinedPatterns.add(pattern);
                preCnt = cnt;
                continue;
            }
            combinedPatterns.add(pattern);
        }
        if (combinedPatterns.size() != 0) {
            ArrayUtils.printlnConcisely(combinedPatterns, out, 6);
            combinedPatterns.clear();
        }
        //out.printlnConcisely();
    }
}

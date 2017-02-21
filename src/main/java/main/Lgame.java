package main;

import template.collection.tuple.Tuple2;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.io.PrintWriter;

/*
 ID: hackint1
 LANG: JAVA
 TASK: lgame
*/

public class Lgame {

    private String NONE = "" + (char)('z' + 1);
    private Map<String, List<String>> dict = new HashMap<>();
    int maxScore = 0;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        try {
            Scanner dictIn = new Scanner(new FileReader("lgame.dict"));
            while (true) {
                if (!dictIn.hasNext()) break;
                String w = dictIn.next();
                char[] wc = w.toCharArray();
                Arrays.sort(wc);
                String key = new String(wc);
                if (!dict.containsKey(key)) dict.put(key, new ArrayList<>());
                dict.get(key).add(w);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        dict.put(NONE, Arrays.asList(NONE));

        String input = in.next();
        char[] inputChars = input.toCharArray();
        Arrays.sort(inputChars);
        int n = inputChars.length;
        List<Tuple2<String, String>> splits = new ArrayList<>();

/*        for (int S = 0; S < (1 << n); ++S) {
            int nfirst = Integer.bitCount(S);
            int nsecond = n - nfirst;
            char[] first = new char[nfirst];
            char[] second = new char[nsecond];
            int j = 0, k = 0;
            for (int i = 0; i < n; ++i) {
                if ((S >> i & 1) == 0) second[k++] = inputChars[i];
                else first[j++] = inputChars[i];
            }
            String firstStr = new String(first);
            String secondStr = new String(second);
            int curScore = 0;
            boolean matched = false;
            if (dict.containsKey(firstStr)) {
                matched = true;
                curScore += score(first);
            } else {
                firstStr = NONE;
            }
            if (dict.containsKey(secondStr)) {
                matched = true;
                curScore += score(second);
            } else {
                secondStr = NONE;
            }
            if (curScore < maxScore) continue;
            if (!matched) continue;
            if (curScore == maxScore) splits.add(new Tuple2(firstStr, secondStr));
            else if (curScore > maxScore) {
                maxScore = curScore;
                splits.clear();
                splits.add(new Tuple2(firstStr, secondStr));
            }
        }*/

        backtrace(0, inputChars, new StringBuilder(), new StringBuilder(), splits);

        TreeSet<Tuple2<String, String>> ans = new TreeSet<>(Tuple2.FIRST_ELEMENT_ORDER);

        for (Tuple2<String, String> split : splits) {
            String first = split.getFirst();
            String second = split.getSecond();
            for (String w1 : dict.get(first))
                for (String w2 : dict.get(second)) {
                    String f = w1, s = w2;
                    if (w1.compareTo(w2) > 0) {
                        f = w2;
                        s = w1;
                    }
                    ans.add(new Tuple2<>(f, s));
                }
        }

        out.println(maxScore);
        for (Tuple2<String, String> w : ans) {
            if (!w.getSecond().equals(NONE)) {
                out.println(w.getFirst() + " " + w.getSecond());
            } else {
                out.println(w.getFirst());
            }
        }
    }

    private void backtrace(int cur, char[] inputChars, StringBuilder left, StringBuilder right, List<Tuple2<String, String>> splits) {
        if (cur == inputChars.length) {
            int score = 0;
            boolean match = false;
            String l = left.toString();
            if (!dict.containsKey(l)) l = NONE;
            else {
                score += score(left);
                match = true;
            }
            String r = right.toString();
            if (!dict.containsKey(r)) r = NONE;
            else {
                score += score(right);
                match = true;
            }
            if (!match || score < maxScore) return;
            if (score == maxScore && l.compareTo(r) <= 0) splits.add(new Tuple2<>(l, r));
            else if (score > maxScore) {
                maxScore = score;
                splits.clear();
                if (l.compareTo(r) <= 0) splits.add(new Tuple2<>(l, r));
            }
            return;
        }
        backtrace(cur + 1, inputChars, left, right, splits);

        left.append(inputChars[cur]);
        backtrace(cur + 1, inputChars, left, right, splits);
        left.setLength(left.length() - 1);

        right.append(inputChars[cur]);
        backtrace(cur + 1, inputChars, left, right, splits);
        right.setLength(right.length() - 1);

    }

    private static Map<Character, Integer> keyboard = new HashMap<>();
    static {
        keyboard.put('q', 7);
        keyboard.put('w', 6);
        keyboard.put('e', 1);
        keyboard.put('r', 2);
        keyboard.put('t', 2);
        keyboard.put('y', 5);
        keyboard.put('u', 4);
        keyboard.put('i', 1);
        keyboard.put('o', 3);
        keyboard.put('p', 5);
        keyboard.put('a', 2);
        keyboard.put('s', 1);
        keyboard.put('d', 4);
        keyboard.put('f', 6);
        keyboard.put('g', 5);
        keyboard.put('h', 5);
        keyboard.put('j', 7);
        keyboard.put('k', 6);
        keyboard.put('l', 3);
        keyboard.put('z', 7);
        keyboard.put('x', 7);
        keyboard.put('c', 4);
        keyboard.put('v', 6);
        keyboard.put('b', 5);
        keyboard.put('n', 2);
        keyboard.put('m', 5);
    }

    private int score(char[] chars) {
        int res = 0;
        for (char c : chars) res += keyboard.get(c);
        return res;
    }

    private int score(CharSequence chars) {
        int res = 0;
        for (int i = 0; i < chars.length(); ++i) res += keyboard.get(chars.charAt(i));
        return res;
    }
}


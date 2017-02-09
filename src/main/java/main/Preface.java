package main;

import template.numbers.IntUtils;

import java.util.*;
import java.io.PrintWriter;
/*
 ID: hackint1
 LANG: JAVA
 TASK: preface
*/
public class Preface {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        Map<Character, Integer> cnt = new HashMap<>();
        for (int i = 1; i <= n; ++i) {
            String romanNumeral = IntUtils.toRoman(i);
            for (char c : romanNumeral.toCharArray()) cnt.put(c, cnt.containsKey(c) ? cnt.get(c) + 1 : 1);
        }
        String keys = "IVXLCDM";
        for (char key : keys.toCharArray()) {
            if (!cnt.containsKey(key)) continue;
            out.println(key + " " + cnt.get(key));
        }
//        Map.Entry<Character, Integer>[] ans = cnt.entrySet().toArray(new Map.Entry[0]);
//        Arrays.sort(ans, Map.Entry.comparingByValue(Collections.reverseOrder()));
//        for (int i = 0; i < ans.length; ++i) out.printlnConcisely(ans[i].getKey() + " " + ans[i].getFromValue());
    }
}

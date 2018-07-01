package main;

import template.collection.CollectionUtils;

import java.util.*;

//WA...difficult
public class CounterfeitCoin {
    public String nextWeighing(int N, String[] left, String[] right, String result) {
        Set<Character>[] kindSet = new TreeSet[2];
        Set<Character> notUsed = new TreeSet<>();
        for (int kind = 0; kind < 2; ++kind) kindSet[kind] = new TreeSet<>();
        for (int which = 0; which < N; ++which) {
            for (int kind = 0; kind < 2; ++kind) {
                boolean good = true;
                boolean used = false;
                if (result.trim().length() != 0) {
                    for (int i = 0; i < left.length; ++i) {
                        int cmp = result.charAt(i);
                        int lset = makeSet(left[i]);
                        int rset = makeSet(right[i]);
                        if ((lset >> which & 1) != 0 || (rset >> which & 1) != 0)
                            used = true;
                        if (!((lset >> which & 1) != 0 && (kind == 0 && cmp == 'R' || kind == 1 && cmp == 'L')
                                || (rset >> which & 1) != 0 && (kind == 0 && cmp == 'L' || kind == 1 && cmp == 'R')
                                || (lset >> which & 1) == 0 && (rset >> which & 1) == 0 && cmp == 'E')) {
                            good = false;
                            break;
                        }
                    }
                }
                if (!used)
                    notUsed.add((char) (which + 'A'));
                else if (good)
                    kindSet[kind].add((char) (which + 'A'));
            }
        }
        Set<Character> join = CollectionUtils.union(kindSet);
        if (join.size() == 1)
            return "";
        if (join.size() == 0 && notUsed.size() == 0)
            return "error";
        int oo = Integer.MAX_VALUE;
        int[][] dp = new int[N + 1][2];
        for (int i = 0; i <= N; ++i)
            Arrays.fill(dp[i], oo);
        dp[1][1] = 0;
        dp[1][0] = 1;
        int[][] chooses = new int[N + 1][2]; chooses[1][0] = 1;
        for (int howMany = 2; howMany <= N; ++howMany) {
            for (int known = 0; known < 2; ++known) {
//                int minMax = dp[howMany][known];
                int minMax = oo;
                for (int choose = 1; choose < howMany && choose * 2 <= N; ++choose) {
                    int max = -1;
                    int cmp = choose * 2 - howMany;
                    if (cmp < 0) {
                        max = 1 + Math.max(dp[choose][1], dp[howMany - choose * 2][known]);
                    }
                    if (cmp == 0) {
                        max = 1 + dp[choose][1];
                    }
                    if (cmp > 0) {
                        max = 1 + Math.max(dp[choose][1], dp[howMany - choose][1]);
                    }
                    if (max < minMax) {
                        minMax = max;
                        chooses[howMany][known] = choose;
                    }
                    System.out.println(howMany + " " + choose + " " + known + " = " + max);
                }
                dp[howMany][known] = minMax;
            }
        }

        Set<Character> must = null;
        if (join.size() > 0) {
            must = join;
        } else {
            must = notUsed;
        }
        List<Character> maybe = new ArrayList<>();
        for (int i = 0; i < N; ++i) if (!must.contains((char) (i + 'A'))) maybe.add((char) (i + 'A'));
        Collections.sort(maybe);
        int need = chooses[must.size()][0] * 2;
        if (join.size() > 0)
                need = chooses[must.size()][1] * 2;
        List<Character> list = new ArrayList<>(must);
        for (int i = 0; i + must.size() < need; ++i)
            list.add(maybe.get(i));
        Collections.sort(list);
        String ans = "";
        for (int i = 0; i < need; ++i)
            ans += list.get(i);
        return ans.substring(0, ans.length() / 2) + "-" + ans.substring(ans.length() / 2);
    }

    private int makeSet(String s) {
        int set = 0;
        for (int i = 0; i < s.length(); ++i)
            set |= 1 << s.charAt(i) - 'A';
        return set;
    }
}

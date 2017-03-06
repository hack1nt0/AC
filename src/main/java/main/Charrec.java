package main;

import template.collection.sequence.ArrayUtils;
import template.collection.tuple.Tuple2;
import template.collection.tuple.Tuple3;
import template.debug.InputReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

/*
 ID: hackint1
 LANG: JAVA
 TASK: charrec
*/

/**
 * some little bugs in... hard to debug.
 */
public class Charrec {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int nLetter = 27;
        int[][] dict = new int[nLetter][20];
        try {
            InputReader dictIn = new InputReader(new FileInputStream("font.in"));
            int nDict = dictIn.readInt();
            if (nDict != 540) throw new IllegalArgumentException();
            for (int i = 0; i < nLetter; ++i) {
                for (int j = 0; j < dict[i].length; ++j) dict[i][j] = Integer.valueOf(dictIn.readString(), 2);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int n = in.readInt();
        int[] input = new int[n];
        for (int i = 0; i < n; ++i) input[i] = Integer.valueOf(in.readString(), 2);
        StringBuilder ans = new StringBuilder();
        char[] minChar = new char[n + 1];
        int[] minExtend = new int[n + 1];
        int[] minCorrupt = new int[n + 1];
        int oo = Integer.MAX_VALUE / 2;
        Arrays.fill(minCorrupt, oo);
        minCorrupt[0] = 0;
        int[][][][] minMatch = new int[21 + 1][dict[0].length + 1][2][2];
        for (int i = 0; i < minMatch.length; ++i)
            for (int j = 0; j < 2; ++j)
                for (int k = 0; k < 2; ++k) minMatch[i][0][j][k] = oo;
        for (int i = 0; i < minMatch[0].length; ++i)
            for (int j = 0; j < 2; ++j)
                for (int k = 0; k < 2; ++k) minMatch[0][i][j][k] = oo;
        minMatch[0][0][0][0] = 0;
        for (int toInput = 0; toInput < n; ++toInput) {
            if (toInput < 18) {
                minCorrupt[toInput + 1] = oo;
                continue;
            }
            int fromInput = Math.max(0, toInput - 20);
            int lenInput = toInput - fromInput + 1;
            for (int d = 0; d < dict.length; ++d) {
                for (int i = 0; i < lenInput; ++i) {
                    for (int j = 0; j < dict[d].length; ++j) if (Math.abs(i - j) <= 1) {
                        for (int duplicate = 0; duplicate <= 1; ++duplicate)  {
                            for (int missing = 0; missing <= 1; ++missing) {
                                minMatch[i + 1][j + 1][duplicate][missing] = oo;
                                if (duplicate == 1 && missing == 1) continue;
                                int diff = Integer.bitCount(input[toInput - i] ^ dict[d][dict[d].length - 1 - j]);
                                int res = minMatch[i][j][duplicate][missing] + diff;
                                if (duplicate == 1 && i > 0) {
                                    res = Math.min(res, minMatch[i - 1][j][0][0] + diff);
                                    res = Math.min(res, minMatch[i][j + 1][0][0]);
                                }
                                if (missing == 1) {
                                    res = Math.min(res, minMatch[i + 1][j][0][0] + 20);
                                }
                                minMatch[i + 1][j + 1][duplicate][missing] = res;
                            }
                        }
                    }
                }
                if (lenInput >= 19 && minCorrupt[toInput + 1] > minMatch[19][dict[d].length][0][1] + minCorrupt[toInput + 1 - 19]) {
                    minCorrupt[toInput + 1] = minMatch[19][dict[d].length][0][1] + minCorrupt[toInput + 1 - 19];
                    minChar[toInput + 1] = id2Char(d);
                    minExtend[toInput + 1] = 19;
                }
                if (lenInput >= 20 && minCorrupt[toInput + 1] > minMatch[20][dict[d].length][0][0] + minCorrupt[toInput + 1 - 20]) {
                    minCorrupt[toInput + 1] = minMatch[20][dict[d].length][0][0] + minCorrupt[toInput + 1 - 20];
                    minChar[toInput + 1] = id2Char(d);
                    minExtend[toInput + 1] = 20;
                }
                if (lenInput >= 21 && minCorrupt[toInput + 1] > minMatch[21][dict[d].length][1][0] + minCorrupt[toInput + 1 - 21]) {
                    minCorrupt[toInput + 1] = minMatch[21][dict[d].length][1][0] + minCorrupt[toInput + 1 - 21];
                    minChar[toInput + 1] = id2Char(d);
                    minExtend[toInput + 1] = 21;
                }
            }
        }
        for (int indexInput = n; indexInput > 0;) {
            ans.append(minChar[indexInput]);
            indexInput -= minExtend[indexInput];
        }
        out.println(ans.reverse().toString());
    }


    public void solveWrongly(int testNumber, InputReader in, PrintWriter out) {
        //int[] dict = new int[540];
        int nLetter = 27;
        Integer[][] dict = new Integer[nLetter][20];
        try {
            InputReader dictIn = new InputReader(new FileInputStream("font.in"));
            int nDict = dictIn.readInt();
            if (nDict != 540) throw new IllegalArgumentException();
            for (int i = 0; i < nLetter; ++i) {
                for (int j = 0; j < dict[i].length; ++j) dict[i][j] = Integer.valueOf(dictIn.readString(), 2);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int n = in.readInt();
        int[] input = new int[n];
        for (int i = 0; i < n; ++i) input[i] = Integer.valueOf(in.readString(), 2);
        StringBuilder ans = new StringBuilder();
        int cnt = 0;
        for (int from = 0; from < n; ++cnt) {
            int to = Math.min(from + 21, n);
            int len = to - from;
            if (len < 19) break;
            Integer[][] minD = new Integer[nLetter][3];
            for (int d = 0; d < nLetter; ++d) {
                Integer[] dictD = dict[d];
                int[][][][] minCorrupt = new int[len + 1][dictD.length + 1][2][2];
                int oo = Integer.MAX_VALUE / 2;
                for (int i = 0; i < minCorrupt.length; ++i)
                    for (int j = 0; j < 2; ++j)
                        for (int k = 0; k < 2; ++k) minCorrupt[i][0][j][k] = oo;
                for (int i = 0; i < minCorrupt[0].length; ++i)
                    for (int j = 0; j < 2; ++j)
                        for (int k = 0; k < 2; ++k) minCorrupt[0][i][j][k] = oo;
                minCorrupt[0][0][0][0] = 0;

                for (int i = 0; i < len; ++i) {
                    for (int j = 0; j < dictD.length; ++j) {
                        for (int duplicate = 0; duplicate <= 1; ++duplicate) {
                            for (int missing = 0; missing <= 1; ++missing) {
                                minCorrupt[i + 1][j + 1][duplicate][missing] = oo;
                                if (duplicate == 1 && missing == 1) continue;

                                int res = minCorrupt[i][j][duplicate][missing] + Integer.bitCount(input[from + i] ^ dictD[j]);
                                if (duplicate == 1 && i > 0) {
                                    res = Math.min(res, minCorrupt[i - 1][j][0][0] + Integer.bitCount(input[from + i] ^ dictD[j]));
                                    res = Math.min(res, minCorrupt[i - 1][j][0][0] + Integer.bitCount(input[from + i - 1] ^ dictD[j]));
                                }
                                if (missing == 1) {
                                    res = Math.min(res, minCorrupt[i + 1][j][0][0] + 20);
                                }
                                minCorrupt[i + 1][j + 1][duplicate][missing] = res;
                            }
                        }
                    }
                }
                Arrays.fill(minD[d], oo);
                if (len >= 19) minD[d][0] = minCorrupt[19][dictD.length][0][1];
                if (len >= 20) minD[d][1] = minCorrupt[20][dictD.length][0][0];
                if (len >= 21) minD[d][2] = minCorrupt[21][dictD.length][1][0];

                if (len >= 19) {
                    int minDiff = oo;
                    for (int missing = 0; missing < 20; ++missing) {
                        int diff = 20;
                        int p = 0;
                        for (int i = from; i < from + 19; ++i, ++p) {
                            if (p == missing) p++;
                            diff += Integer.bitCount(input[i] ^ dictD[p]);
                        }
                        minDiff = Math.min(minDiff, diff);
                    }
                    if (minDiff != minD[d][0]) {
                        throw new RuntimeException();
                    }
                }
                if (len >= 20) {
                    int diff = 0;
                    for (int i = 0; i < 20; ++i)
                        diff += Integer.bitCount(input[from + i] ^ dictD[i]);
                    if (diff != minD[d][1]) {
                        throw new RuntimeException();
                    }
                }
                if (len >= 21) {
                    int minDiff = oo;
                    for (int duplicated = 0; duplicated < 20; ++duplicated) {
                        int p = 0;
                        int diff = 0;
                        for (int i = from; i < from + 21; ++i, ++p) {
                            if (p == duplicated) {
                                diff += Math.min(Integer.bitCount(input[i] ^ dictD[p]), Integer.bitCount(input[i + 1] ^ dictD[p]));
                                i++;
                            } else {
                                try {
                                    diff += Integer.bitCount(input[i] ^ dictD[p]);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        minDiff = Math.min(minDiff, diff);
                    }
                    if (minDiff != minD[d][2]) {
                        throw new RuntimeException();
                    }
                }

            }
//            Integer[] rank = (Integer[])ArrayUtils.inbox(ArrayUtils.index(nLetter));
//            Arrays.sort(rank, new Comparator<Integer>() {
//                @Override
//                public int compare(Integer o1, Integer o2) {
//                    int min1 = ArrayUtils.min(minD[o1]);
//                    int min2 = ArrayUtils.min(minD[o2]);
//                    if (min1 != min2) return min1 - min2;
//                    if (minD[o1][0].compareTo(minD[o2][0]) != 0) return minD[o1][0] - minD[o2][0];
//                    if (minD[o1][1].compareTo(minD[o2][1]) != 0) return minD[o1][1] - minD[o2][1];
//                    if (minD[o1][2].compareTo(minD[o2][2]) != 0) return minD[o1][2] - minD[o2][2];
//                    return o1 - o2;
//                }
//            });
//            int whichD = rank[0];
////            int whichO = 0;
////            try {
////                if (!Arrays.equals(minD[rank[0]], minD[rank[1]]))
////                    while (minD[rank[0]][whichO].compareTo(minD[rank[1]][whichO]) == 0)whichO++;
////            } catch (Exception e) {
////                e.printStackTrace();
////            }
////            int minValue = minD[whichD][whichO];
//            int whichO = 0;
//            while (minD[rank[0]][whichO] > 20 * 20 * 0.3) whichO++;
//            ans.append(id2Char(whichD));

            Tuple3<Integer, Integer, Integer> min = ArrayUtils.minIndex(minD);
            int whichD = min.getFirst();
            int whichO = min.getSecond();
            int minValue = min.getThird();
            whichO = -1;
            int[] O = new int[] {1, 0, 2};
            for (whichO = 0; whichO < 3; ++whichO) {
                boolean ok = true;
                for (int i = 0; i < nLetter; ++i) if (i != whichD && ArrayUtils.min(minD[i]) < minD[whichD][whichO]) {ok = false; break;}
                if (ok) break;
            }
            minValue = minD[whichD][whichO];

            if (minValue > 20  * 20 * 0.3) {
                ans.append('?');
                System.out.println(minValue);
            }
            else ans.append(id2Char(whichD));
            if (cnt == 1) {
                for (int i = from; i < to; ++i) System.out.println(Integer.toBinaryString(input[i] | 1 << 27));
                System.out.println();
                int cand1 = 'v' - 'a' + 1, cand2 = 'c' - 'a' + 1;
                for (int i = 0; i < dict[cand1].length; ++i) System.out.println(Integer.toBinaryString(dict[cand1][i] | 1 << 27));
                System.out.println();
                for (int i = 0; i < dict[cand2].length; ++i) System.out.println(Integer.toBinaryString(dict[cand2][i] | 1 << 27));
                System.out.println();
            }

            //from = to;
            if (whichO == 0) from += 19;
            if (whichO == 1) from += 20;
            if (whichO == 2) from += 21;

        }
        out.println(ans);
    }

    private char id2Char(int id) {
        if (id == 0) return ' ';
        return (char)('a' + id - 1);
    }
}
